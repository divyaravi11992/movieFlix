
package app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
*
* @author Divya Ravi
* 
* Exception handling class for Not found user
*/


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8929274352642764489L;

	public UserNotFoundException(Long userId){
		super("could not find user "+userId);
	}
}
