/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.movieFlix.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.domain.User;
import app.enums.UserType;
import app.exceptions.AuthenticationException;
import app.exceptions.CountryMissingException;
import app.exceptions.UserTypeException;
import app.movieFlix.MovieFlixIntegrationTests;
import app.services.UserService;

/**
 *
 * @author Divya Ravi
 * 
 * Integration Test for  whole web
 */
public class WholeWebsiteIntegrationTest extends MovieFlixIntegrationTests {

  
    @Autowired
    UserService userService;

    Long userId;
    Long adminId;
    Long noId;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private RestTemplate restTemplate = new RestTemplate();
    HttpHeaders requestHeaders;

    String URI;

    @Before
    public void prepareTest() {
        URI = "http://" + apiHost + "/";
    }

    public void wholeAppTest(String email, UserType userType, String url, Long userId) throws JsonProcessingException, AuthenticationException, CountryMissingException, UserTypeException, IOException {

        headerSet();

        userId = signUpAndLoginTests(email, userType, url, userId);

        requestHeaders.set("userID", Long.toString(userId));
        signUpCkecks(userId);
        
            }

    public long signUpAndLoginTests(String email, UserType userType, String url, Long userId) throws JsonProcessingException, AuthenticationException, CountryMissingException, UserTypeException, IOException {

        Map<String, Object> requestBody = requestBody(email, userType.toString());
        
        HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<Map> response = restTemplate.exchange(URI + url, HttpMethod.POST, httpEntity, Map.class);
log.info(response);
        userId = authenticationTest(httpEntity);
        return userId;
    }


 

    public void headerSet() {
        requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public Long authenticationTest(HttpEntity<String> httpEntity) throws AuthenticationException, CountryMissingException, UserTypeException {

        ResponseEntity<Map> response = restTemplate.exchange(URI + "user/authenticate", HttpMethod.POST, httpEntity, Map.class);

        if (!response.getBody().get("auth").equals(true)) {
            throw new AuthenticationException();
        }
        Long userId = Long.parseLong(response.getBody().get("userID").toString());

        return userId;
    }

    public void signUpCkecks(Long userId) throws AuthenticationException, CountryMissingException, UserTypeException {

        User user = userService.getUserById(userId);
    
        if (user.getCountry() == null) {
            throw new CountryMissingException();
        }
        if (!isUserType(user.getUserType())) {
            throw new UserTypeException();
        }
    }

    public Map<String, Object> requestBody(String email, String userType) {
        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("email", email);
        requestBody.put("password", "1234qwer");
        requestBody.put("country", "United States");
        requestBody.put("userType", userType);
        return requestBody;
    }

    public boolean isUserType(UserType userType) {
        if (userType == UserType.USER
                || userType == UserType.ADMIN
               ) {
            return true;
        }
        return false;
    }

    @Test
    public void testRegisterAdminUser() throws JsonProcessingException, AuthenticationException, CountryMissingException, UserTypeException, IOException {
        wholeAppTest("admin@uncc.com", UserType.ADMIN, "register/test", this.adminId);
    }

    @Test
    public void testRegisterUser() throws JsonProcessingException, AuthenticationException, CountryMissingException, UserTypeException, IOException {
        wholeAppTest("user@uncc.edu", UserType.USER, "register/test", this.userId);
    }



    @Test
    public void testRegisterDuplicatedUser() throws JsonProcessingException {
        headerSet();

        Map<String, Object> requestBody = requestBody("user1@uncc.edu", UserType.USER.toString());

        HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(URI + "register/test/duplicates", HttpMethod.POST, httpEntity, Map.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testRegisterWrongUserTypeUser() throws JsonProcessingException, AuthenticationException, CountryMissingException, UserTypeException {
        headerSet();

        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("email", "userType@uncc.edu");
        requestBody.put("password", "1234qwer");
        requestBody.put("country", "United States");
        requestBody.put("userType", "fss");

        HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(URI + "register/test", HttpMethod.POST, httpEntity, Map.class);
        response = restTemplate.exchange(URI + "user/authenticate", HttpMethod.POST, httpEntity, Map.class);
        Long userId;
        if (!response.getBody().get("auth").equals(true)) {
            throw new AuthenticationException();
        } else {
            userId = Long.parseLong(response.getBody().get("userID").toString());
        }
        User user = userService.getUserById(userId);
        if (isUserType(user.getUserType())) {
            throw new UserTypeException();
        }

    }

    @Test
    public void testRegisterWrongCountryUser() throws JsonProcessingException, AuthenticationException,UserTypeException, CountryMissingException {
        headerSet();
        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("email", "country@uncc.edu");
        requestBody.put("password", "1234qwer");
        requestBody.put("country", "");
        requestBody.put("userType", UserType.USER.toString());
        HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(URI + "register/test", HttpMethod.POST, httpEntity, Map.class);
        response = restTemplate.exchange(URI + "user/authenticate", HttpMethod.POST, httpEntity, Map.class);
        Long userId;
        if (!response.getBody().get("auth").equals(true)) {
            throw new AuthenticationException();
        } else {
            userId = Long.parseLong(response.getBody().get("userID").toString());
        }
        User user = userService.getUserById(userId);
        if (null!=user&&null!=user.getCountry()&&!user.getCountry().isEmpty()) {
            throw new CountryMissingException();
        }

    }

}
