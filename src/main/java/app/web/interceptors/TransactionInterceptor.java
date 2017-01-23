package app.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
*
* @author Divya Ravi
* 
* Interceptor class for transaction
* 
*/
public class TransactionInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		System.out.println("Got request to save data : name:"+request.getParameter("name"));
		return true;
	}
} 
