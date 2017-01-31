
package app.exceptions;

/**
*
* @author Divya Ravi
* 
* Exception handling class for Authentication
* 
*/

public class AuthenticationException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2558517856709243546L;

	public AuthenticationException() {}

      //Constructor that accepts a message
      public AuthenticationException(String message)
      {
         super(message);
      }
    
}
