package app.exceptions;

/**
*
* @author Divya Ravi
* 
* Exception handling class for Same user
* 
*/

public class SameUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SameUserException(){
		
	}
	public SameUserException(String message){
		super(message);
	}
}
