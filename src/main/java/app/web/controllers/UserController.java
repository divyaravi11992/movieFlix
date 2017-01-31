package app.web.controllers;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.domain.Auth;
import app.domain.User;
import app.enums.UserType;
import app.services.UserService;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for user
* 
*/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    
    private Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public User getUsersById(@RequestHeader String userID) {
        User user = (User) userService.getUserById(Long.parseLong(userID));
        return user;
    }

   

   

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllUsers() {
        List<User> user = (List<User>) userService.listAllUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        String returnString = null;
        try {
            returnString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return returnString;
    }

    @RequestMapping(value = "/retrieveUserByEmail", method = RequestMethod.GET)
    public User getAllUsersByEmail(@RequestHeader String email) {
        User user = userService.getUserByEmail(email);
        return user;
    }

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.GET)
    public boolean authenticateUser(@RequestHeader String email,
            @RequestHeader String password) {
        return userService.authenticate(email, password);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Auth authenticateUserLogin(@RequestBody Map<String, String> reqPar) {
        Auth auth = new Auth();
        String email = reqPar.get("email");
        String password = reqPar.get("password");
        if (userService.authenticate(email, password)) {
            User user = userService.getUserByEmail(email);
            auth.setAuth(true);
            auth.setUserID(user.getId());
            auth.setUserType(user.getUserType());
            return auth;
        } else {
            auth.setAuth(false);

            return auth;
        }
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(@RequestHeader String email,
            @RequestHeader String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
     
        userService.saveUser(user);
    }

   

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User user(@RequestHeader String userId) {

        User users = userService.getUserById(Long.parseLong(userId));
        User user = new User();
      
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody Map<String, String> reqPar,
            @RequestHeader String userId) {

        long userIdLong = 0L;
        userIdLong = Long.parseLong(userId);
        userService.validateUser(userIdLong);

        boolean isChangePassword = Boolean.parseBoolean(reqPar
                .get("isChangePassword"));
        boolean isChangeAccDetails = Boolean.parseBoolean(reqPar
                .get("isChangeAccDetails"));

        if (isChangeAccDetails) {
            if (reqPar.get("email").equals(reqPar.get("confirmEmail"))) {
                User user = new User();
                user.setId(userIdLong);
                user.setFirstName(reqPar.get("firstName"));
                user.setLastName(reqPar.get("lastName"));
                user.setEmail(reqPar.get("email"));
                user.setUserType(UserType.valueOf(reqPar.get("userType")));

                userService.updateUserAccInfo(user);
            } else {
                log.error("Email and confirm email does not match");
            }
        } else if (isChangePassword) {
            if (reqPar.get("newPassword").equals(reqPar.get("ReEnterPassword"))) {
                User user = new User();
                user.setId(userIdLong);
                user.setPassword(reqPar.get("newPassword"));
                userService.updatePassword(user);
            } else {
                log.error("Password and Re-enter password does not match");
            }
        }
    }

    @RequestMapping(value = "accountInformation", method = RequestMethod.PUT)
    public void updateAccountInfo(@RequestBody Map<String, String> reqPar,
            @RequestHeader String userId) {
        log.info("Start updating account information");
        long userIdLong = 0L;
        userIdLong = Long.parseLong(userId);
        userService.validateUser(userIdLong);

        //TODO: Refactor user creation to new function
        User user = new User();
        user.setId(userIdLong);
        user.setFirstName(reqPar.get("firstName"));
        user.setLastName(reqPar.get("lastName"));
        user.setEmail(reqPar.get("email"));
        user.setUserType(UserType.valueOf(reqPar.get("userType")));
        userService.updateUserAccInfo(user);
        log.info("End updating account information");
    }

    @RequestMapping(value = "password", method = RequestMethod.PUT)
    public void updatePassword(@RequestBody Map<String, String> reqPar,
            @RequestHeader String userId) {
        log.info("Start updating password");
        long userIdLong = 0L;
        userIdLong = Long.parseLong(userId);
        userService.validateUser(userIdLong);
        User user = new User();
        user.setId(userIdLong);
        user.setPassword(reqPar.get("password"));
        userService.updatePassword(user);
        log.info("End updating password");
    }

}
