package app.web.controllers;



import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.domain.User;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for signup
*/
@Controller
public class SignUpController extends BaseWebController{
	

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(@RequestBody Map<String,String> reqPar, Model model) {

		System.out.println("This is the signUp page");

		String email = reqPar.get("email");
		System.out.println("Email : "+ reqPar.get("email"));
		String password = reqPar.get("password");
		System.out.println("password : "+ reqPar.get("password"));
		System.out.println("Pass Parameters + if "+(getUserByEmail(email) == null));
		if (getUserByEmail(email) == null) {

			System.out.println("Inside if");
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);

			saveUser(user);
		

			return "login";

		}
		model.addAttribute("status", "error");
		model.addAttribute("msg", "Email already exist, please sign in");
		return "login";

	}

	
}
