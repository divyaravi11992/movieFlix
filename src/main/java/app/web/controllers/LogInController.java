package app.web.controllers;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import app.domain.User;

/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for Login and Logout
*/

@RestController
public class LogInController extends BaseWebController{
    
	private static Logger log = Logger.getLogger(LogInController.class);


    
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public Map<String,Object> sessionLogin(@RequestBody Map<String, String> reqPar, HttpSession session) {
        
        log.info("Inside login page");
        String email = reqPar.get("email");
        String password = reqPar.get("password");
        Map<String,Object> loginStatus = new HashMap<>();
        if (isUserAuthenticated(email, password)) {
        	
            User user = getUserByEmail(email);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(-1);
            loginStatus.put("user",user);

            return loginStatus;
        }
        log.info("error");
        return loginStatus;
    }
    @RequestMapping(value = "/logedin", method = RequestMethod.POST)
    public boolean isLogedIn(HttpSession session)
    {
        log.info("logedin start");
        User user = (User) session.getAttribute("user");
        if(user !=null)
        {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public void logout(HttpSession session) {

        session.removeAttribute("user");
        session.invalidate();

    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean cookieLogin(@RequestBody Map<String, String> reqPar,HttpServletResponse response) {
        log.info("Inside login page");
        String email = reqPar.get("email");
        String password = reqPar.get("password");

        if (isUserAuthenticated(email, password)) {

            log.info("authenticated");
            User user = getUserByEmail(email);
            Cookie authenticated = new Cookie("authenticated","true");
            authenticated.setMaxAge(60*60*24*360);
            response.addCookie(authenticated);

            Cookie userId = new Cookie("userId",String.valueOf(user.getId()));
            authenticated.setMaxAge(60*60*24*360);
            response.addCookie(userId);

            Cookie userType = new Cookie("userType",user.getUserType().toString());
            authenticated.setMaxAge(60*60*24*360);
            response.addCookie(userType);
            return true;

        }
        response.addHeader("authenticated","false");
        return false;

    }
}
