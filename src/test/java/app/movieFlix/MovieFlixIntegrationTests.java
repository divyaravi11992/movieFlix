package app.movieFlix;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import app.MovieFlixApplication;
import app.exceptions.AuthenticationException;
import app.exceptions.CountryMissingException;
import app.exceptions.UserTypeException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MovieFlixApplication.class)
@WebIntegrationTest
public class MovieFlixIntegrationTests {

	public Logger log = Logger.getLogger(MovieFlixIntegrationTests.class);
	@Value("${api_host}")
	protected String apiHost;
	String URI;
	private RestTemplate restTemplate = new RestTemplate();
	@Test
	public void integrationTest(){
 
	}

	protected Long authenticationTest(HttpEntity<String> httpEntity) throws AuthenticationException, UserTypeException, CountryMissingException {
		URI = "http://"+apiHost+"/";
		ResponseEntity<Map> response = restTemplate.exchange(URI + "user/authenticate", HttpMethod.POST, httpEntity, Map.class);

		if (!response.getBody().get("auth").equals(true)) {
			throw new AuthenticationException();
		}
		Long userId = Long.parseLong(response.getBody().get("userID").toString());

		return userId;
	}
}
