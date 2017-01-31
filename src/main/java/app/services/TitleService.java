package app.services;

import java.util.List;

import app.domain.Title;
import app.enums.TitleType;
/**
*
* @author Divya Ravi
* 
* Service interface for title
*/
public interface TitleService {
	List<Title> listAllTitle();

	Title getTitleById(long id);



	Title saveTitle(Title title);

	void deleteTitle(long id);

	List<Title> findTitleWithFilters(String genre, TitleType type, int year);

	List<Title> findTitleWithSearch(String text);

	List<Integer> findDistinctYear();

	List<String> findDistinctGenre();



	List<Title> listAllMovies(TitleType movie);

	List<Title> listAllSeries(TitleType series);
}
