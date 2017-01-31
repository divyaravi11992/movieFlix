package app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.Rating;
import app.domain.Title;
import app.repositories.RatingRepository;
/**
*
* @author Divya Ravi
* 
* Service implementation for rating
*/
@Component("ratingService")
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> listAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating getRatingById(long id) {
		return ratingRepository.findOne(id);
	}

	@Override
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public void deleteRating(long id) {
		ratingRepository.delete(id);
	
	}

	@Override
	public float listAverageRatingByTitle(Title title) {
		List<Integer> rating=ratingRepository.findByTitle(title).stream()
        .map(Rating::getRate)
        .collect(Collectors.toList());
		Integer sum= rating.stream().mapToInt(value -> value).sum();
		return sum/rating.size();
	}
     

	
}
