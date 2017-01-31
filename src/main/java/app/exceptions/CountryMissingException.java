package app.exceptions;


/**
*
* @author Divya Ravi
* 
* Exception handling class for Missing Country data
* 
*/

public class CountryMissingException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6910090330139841716L;

	public CountryMissingException() {}

      //Constructor that accepts a message
      public CountryMissingException(String message)
      {
         super(message);
      }
}
