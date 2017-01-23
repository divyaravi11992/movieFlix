package app.web.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.LastModified;

import app.domain.User;
import app.services.UserService;
/**
*
* @author Divya Ravi
* 
* Home Controller class to handle HTTP requests 
*/
@Controller
public class HomeController implements LastModified {

	private static Logger log = Logger.getLogger(HomeController.class);
	
    @Autowired
    UserService userService;



    @RequestMapping(value = "/Error", method = RequestMethod.GET)
    public String Error() {
        log.info("Inside show error Page");
        return "error";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password() {
        log.info("Inside show password Page");
        return "password";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgot() {
    		log.info("Inside show forgot Page");
    		log.info("Forgot password");
        return "forgot";
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        // TODO Auto-generated method stub
        // LocalDateTime timePoint = LocalDateTime.now();
        return new Date().getTime();
    }
}
