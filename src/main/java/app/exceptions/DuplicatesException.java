
package app.exceptions;

/**
*
* @author Divya Ravi
* 
* Exception handling class for Duplicates
* 
*/

public class DuplicatesException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2067161087900722055L;

	public DuplicatesException() {}

      //Constructor that accepts a message
      public DuplicatesException(String message)
      {
         super(message);
      }
}
