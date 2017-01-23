package app.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.domain.User;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests 
*/

public class BaseWebController {

	protected RestTemplate restTemplate = new RestTemplate();
	protected HttpHeaders requestHeaders = new HttpHeaders();
	protected HttpEntity<String> httpEntity ;
	protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	protected Logger log = Logger.getLogger(BaseWebController.class);

	@Value("${api_host}")
	protected String apiHost;
	protected String URL;
	
	
	protected StringBuilder createBaseUrl() {
		return new StringBuilder("http://").append(apiHost);
	}

	protected void saveUser(User user){

		URL = createBaseUrl()
				.append("/user/saveUser").toString();

		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("email", user.getEmail());
		requestHeaders.set("password", user.getPassword());
		httpEntity = new HttpEntity<String>(null,requestHeaders);
		
		restTemplate.exchange(URL, HttpMethod.POST, httpEntity,void.class);
	}
	
	protected User getUserById(String id){

		URL = createBaseUrl()
				.append("/user/retrieveUser/{id}").toString();
		Map<String,String> var = new HashMap<>();
		var.put("id", id);

		User user = restTemplate.getForObject(URL, User.class, var);
		return user;
	}

	protected User getUserByEmail(String email){

		URL = createBaseUrl()
				.append("/user/retrieveUserByEmail").toString();

		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("email", email);
		httpEntity = new HttpEntity<String>(null,requestHeaders);
		
		ResponseEntity<User> user = restTemplate.exchange(URL, HttpMethod.GET, httpEntity,User.class);
		return user.getBody();
	}	
	protected List<User> getAllUsers(){
		User[] userArray=null;
		URL = createBaseUrl()
				.append("/user/all").toString();
		httpEntity = new HttpEntity<String>(null,null);
		ResponseEntity<String> userResponse = restTemplate.exchange(URL,HttpMethod.GET,httpEntity, String.class);
		try{
			userArray = OBJECT_MAPPER.readValue(userResponse.getBody(), User[].class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return Arrays.asList(userArray);
	}


	
	
	
	protected boolean isUserAuthenticated(String email,String password){

		URL = createBaseUrl().append("/user/authenticateUser").toString();

		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set("email", email);
		requestHeaders.set("password", password);
		httpEntity = new HttpEntity<String>(null,requestHeaders);

		ResponseEntity<Boolean> userResponse = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, Boolean.class);
		
		return userResponse.getBody();
	}
	
	
	
	
	protected boolean isValidUserAndUserSession(User user, User userSession) {
		return ( (user != null) && (userSession != null) );
	}
}
