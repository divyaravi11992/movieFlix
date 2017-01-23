package app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.Title;
import app.enums.TitleType;
import app.repositories.TitleRepository;
/**
*
* @author Divya Ravi
* Service implementation for title
*/
@Component("titleService")
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleRepository titleRepository;
     

	@Override
	public List<Title> listAllTitle() {
		return titleRepository.findAll();
	}

	@Override
	public Title getTitleById(long id) {
		return titleRepository.findOne(id);
	}

	@Override
	public Title saveTitle(Title title) {
		return titleRepository.save(title);
	}

	@Override
	public void deleteTitle(long id) {
		titleRepository.delete(id);

	}

	@Override
	public List<Title> findTitleWithFilters(String genre, TitleType type, int year) {
		return titleRepository.findByGenreAndTypeAndYear(genre,type,year);
	}

	@Override
	public List<Title> findTitleWithSearch(String text) {
		return titleRepository.findBySearchText(text);
	}

	@Override
	public List<Integer> findDistinctYear() {
	
		
		return		titleRepository.findAll().stream()
			              .map(Title::getYear)
			              .collect(Collectors.toList());
	}

	@Override
	public List<String> findDistinctGenre() {
		
		
		return		titleRepository.findAll().stream()
			              .map(Title::getGenre)
			              .collect(Collectors.toList());
	}
	
}
