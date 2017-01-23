package app.repositories;



import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.domain.Title;
import app.enums.TitleType;

/**
*
* @author Divya Ravi
* 
* Hibernate JPA Repository for title
* 
*/

@Repository
@Transactional
public interface TitleRepository extends JpaRepository<Title, Long> {
	@Query("select t from title t where t.title LIKE CONCAT('%',:text,'%')" )
	List<Title> findBySearchText(@Param("text") String text);

	List<Title> findByGenreAndTypeAndYear(String genre, TitleType type, int year);


}
