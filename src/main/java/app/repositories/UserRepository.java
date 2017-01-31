package app.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.domain.User;
import app.enums.UserType;
/**
*
* @author Divya Ravi
* 
* Hibernate JPA Repository for user
*/
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying(clearAutomatically=true)
	@Query("Update User u Set u.firstName=:firstName, u.lastName=:lastName, u.email=:email, u.userType=:userType Where u.id=:userId")
	public void updateUserAccInfo(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("email") String email,@Param("userType") UserType userType,@Param("userId") long userId);
	
	public User findUserByEmailAndPassword(String email, String password);

	public User findUserByEmail(String email);
	
	@Query("Select u From User u Where u.userType IN :userTypes")
	public List<User> findByUserType(@Param("userTypes")List<UserType> userTypes);
	
	
	
	@Modifying(clearAutomatically=true)
	@Query("Update User u Set u.password=:password where u.id=:userId")
	public void updatePassword(@Param("password") String password,@Param("userId") long userId);
}
