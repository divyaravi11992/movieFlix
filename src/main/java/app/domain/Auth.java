package app.domain;

import app.enums.UserType;
/**
*
* @author Divya Ravi
* 
*  Class for authentication
*/
public class Auth {

    private boolean auth;
    private long userID;
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}
