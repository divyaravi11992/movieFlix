
package app.exceptions;
/**
*
* @author Divya Ravi
* 
* Exception handling class for user type
* 
*/
public class UserTypeException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1354771547701904978L;

	public UserTypeException() {}

      //Constructor that accepts a message
      public UserTypeException(String message)
      {
         super(message);
      }
}
