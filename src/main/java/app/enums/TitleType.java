package app.enums;

import java.util.EnumSet;

/**
*
* @author Divya Ravi
* 
* Enumeration for Title type
*/

public enum TitleType {
	MOVIE("movie"),
	SERIES("series");

	
	private final String type;
	
	
	private TitleType(String s) {
		type = s;
	}
	
	public String toString()
	{
		return type;
	}
	
	public static TitleType fromString(String text) {
	    if (text != null) {
	      for (TitleType b : TitleType.values()) {
	        if (text.equalsIgnoreCase(b.type)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
	public static final EnumSet<TitleType> ALL_USER_TYPES
		= EnumSet.allOf(TitleType.class);
}
