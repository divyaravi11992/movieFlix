package app.web.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Rating;
import app.domain.Title;
import app.domain.User;
import app.services.RatingService;
import app.services.TitleService;
import app.services.UserService;
import app.util.DateUtil;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for rating
*/
@RestController
@RequestMapping(value = "/rating")
public class RatingController {

	@Autowired
    UserService userService;

    @Autowired
    TitleService titleService;
    
    @Autowired
    RatingService ratingService;
    
    private Logger log = Logger.getLogger(UserController.class);

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( method = RequestMethod.POST)
    public Rating saveRating(@RequestBody Map<String, String> reqPar,@RequestHeader String userId) {

		log.debug("Rate Create");
    	Long userID=Long.parseLong(userId);
        userService.validateUser(userID);
        User user=userService.getUserById(userID);
       
         Title title= titleService.getTitleById(Long.parseLong(reqPar.get("titleId")));
        
         Rating rating=new Rating();
         rating.setRate(Integer.parseInt(reqPar.get("rating")));
         rating.setRatetime(DateUtil.getTimeStamp());
         rating.setTitle(title);
         rating.setUser(user);
        
         ratingService.saveRating(rating);
         title.setRating(ratingService.listAverageRatingByTitle(title));
         titleService.saveTitle(title);
    	log.debug("Rate Create end");
        
      return rating;
    }
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( method = RequestMethod.PUT)
    public Rating updateRating(@RequestBody Map<String, String> reqPar,@RequestHeader String userId) {

		log.debug("Rate Create");
    	Long userID=Long.parseLong(userId);
        userService.validateUser(userID);
        User user=userService.getUserById(userID);
       
         Title title= titleService.getTitleById(Long.parseLong(reqPar.get("titleId")));
        
         Rating rating=new Rating();
         rating.setRate(Integer.parseInt(reqPar.get("rating")));
         rating.setRatetime(DateUtil.getTimeStamp());
         rating.setTitle(title);
         rating.setUser(user);
        
         ratingService.saveRating(rating);
         title.setRating(ratingService.listAverageRatingByTitle(title));
         titleService.saveTitle(title);
    	log.debug("Rate Create end");
        
      return rating;
    }
	
}
