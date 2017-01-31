package app.enums;

import java.util.EnumSet;

/**
*
* @author Divya Ravi
* 
* Enumeration for User type
*/

public enum UserType {
	
	USER("user"),
	ADMIN("admin");

	
	private final String type;
	
	
	private UserType(String s) {
		type = s;
	}
	
	public String toString()
	{
		return type;
	}
	
	public static UserType fromString(String text) {
	    if (text != null) {
	      for (UserType b : UserType.values()) {
	        if (text.equalsIgnoreCase(b.type)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
	public static final EnumSet<UserType> ALL_USER_TYPES
		= EnumSet.allOf(UserType.class);
}
