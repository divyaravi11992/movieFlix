package app.web.controllers;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.User;
import app.enums.UserType;
import app.exceptions.DuplicatesException;
import app.services.UserService;

/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for Signup
*/

@RestController
@RequestMapping(value = "/register")
public class RegisterController  {

	
	@Autowired
	UserService userService;

	
	private Logger log = Logger.getLogger(RegisterController.class);


	User user;


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean register(@RequestBody Map<String, String> reqPar)
			throws ParseException {

		return signUp(reqPar);
	}
	@RequestMapping(method = RequestMethod.POST,value = "/test")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerTest(@RequestBody Map<String, String> reqPar)
			throws ParseException, DuplicatesException {

		if(!signUp(reqPar)){
			throw new DuplicatesException();
		}
	}
	@RequestMapping(method = RequestMethod.POST,value = "/test/duplicates")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerTestDub(@RequestBody Map<String, String> reqPar)
			throws ParseException, DuplicatesException {

		if(!signUp(reqPar)){
			throw new DuplicatesException();
		}
	}

	public boolean signUp(Map<String, String> reqPar){
		String email = reqPar.get("email");
		log.info("Email : " + reqPar.get("email"));
		String password = reqPar.get("password");
		log.info("password : " + reqPar.get("password"));
		String country = reqPar.get("country");
		log.info("country : " + reqPar.get("country"));
		String userType = reqPar.get("userType");
		log.info("userType : " + reqPar.get("userType"));

		if (userService.getUserByEmail(email) != null) {
			return false;
		}
	
		
		
		user = createUser(email, password, country,UserType.fromString(userType));

	

		userService.saveUser(user);
	

;

	

		return true;
	}



	private User createUser(String email, String password, String country,UserType userType) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setCountry(country);
		user.setUserType(userType);
		return user;
	}


}
