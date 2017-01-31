package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.User;
import app.exceptions.UserNotFoundException;
import app.repositories.UserRepository;


/**
*
* @author Divya Ravi
* 
* Service implementation for user
* 
*/
@Component("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
     

	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepository.delete(id);

	}

	@Override
	public boolean authenticate(String email, String password) {
		User users = userRepository.findUserByEmailAndPassword(email,password);
		return users != null;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
	
	@Override
	public void validateUser(long id) throws UserNotFoundException{
		User user = userRepository.findOne(id);
		if(null == user){
			 throw new UserNotFoundException(id);
		}
	}

	@Override
	public void updateUserAccInfo(User user) {
		userRepository.updateUserAccInfo(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserType(), user.getId());		
	}

	@Override
	public void updatePassword(User user) {
		userRepository.updatePassword(user.getPassword(), user.getId());		
	}


	
}
