package app.services;

import app.domain.User;
import app.exceptions.UserNotFoundException;
/**
*
* @author Divya Ravi
* 
* Service interface for user
*/
public interface UserService {
	Iterable<User> listAllUsers();

	User getUserById(long id);

	User getUserByEmail(String email);

	User saveUser(User user);

	void deleteUser(long id);

	boolean authenticate(String email, String password);
	
	void validateUser(long id) throws UserNotFoundException;
	
	public void updateUserAccInfo(User user);
	
	public void updatePassword(User user);
	

	


}
