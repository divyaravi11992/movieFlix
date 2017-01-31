package app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.domain.Rating;
import app.domain.Title;

/**
*
* @author Divya Ravi
* 
* Hibernate JPA Repository for rating
*/
	@Repository
	@Transactional
	public interface RatingRepository extends JpaRepository<Rating, Long> {

		List<Rating> findByTitle(Title title);

	}
