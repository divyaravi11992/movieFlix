package app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.domain.Comments;
import app.domain.Title;

/**
*
* @author Divya Ravi
* 
* Hibernate JPA Repository for comments
*/
	@Repository
	@Transactional
	public interface CommentsRepository extends JpaRepository<Comments, Long> {

		List<Comments> findByTitle(Title title);

	}

