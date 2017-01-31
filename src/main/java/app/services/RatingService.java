package app.services;

import java.util.List;

import app.domain.Rating;
import app.domain.Title;
/**
*
* @author Divya Ravi
* 
* Service interface for rating
*/
public interface RatingService {

	List<Rating> listAllRating();

	Rating getRatingById(long id);



	Rating saveRating(Rating rating);

	void deleteRating(long id);

float listAverageRatingByTitle(Title title);

	
}

