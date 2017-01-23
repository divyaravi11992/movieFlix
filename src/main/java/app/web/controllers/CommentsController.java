package app.web.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Comments;
import app.domain.Title;
import app.domain.User;
import app.services.CommentsService;
import app.services.TitleService;
import app.services.UserService;
import app.util.DateUtil;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for comments
* 
*/
@RestController
@RequestMapping(value = "/comments")
public class CommentsController {

	@Autowired
    UserService userService;

    @Autowired
    TitleService titleService;
    
    @Autowired
    CommentsService commentsService;
    
    private Logger log = Logger.getLogger(UserController.class);

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( method = RequestMethod.POST)
    public Comments saveComments(@RequestBody Map<String, String> reqPar,@RequestHeader String userId) {

		log.debug("Comment Create");
    	Long userID=Long.parseLong(userId);
        userService.validateUser(userID);
        User user=userService.getUserById(userID);
       
         Title title= titleService.getTitleById(Long.parseLong(reqPar.get("titleId")));
        
         Comments comment=new Comments();
         comment.setComment(reqPar.get("comment"));
         comment.setCommenttime(DateUtil.getTimeStamp());
         comment.setTitle(title);
         comment.setUser(user);
        
         commentsService.saveComments(comment);
    	log.debug("comment Create end");
        
      return comment;
    }
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( method = RequestMethod.PUT)
    public Comments updateComments(@RequestBody Map<String, String> reqPar,@RequestHeader String userId) {

		log.debug("Comment Create");
    	Long userID=Long.parseLong(userId);
        userService.validateUser(userID);
        User user=userService.getUserById(userID);
       
         Title title= titleService.getTitleById(Long.parseLong(reqPar.get("titleId")));
        
         Comments comment=new Comments();
         comment.setComment(reqPar.get("comment"));
         comment.setCommenttime(DateUtil.getTimeStamp());
         comment.setTitle(title);
         comment.setUser(user);
        
         commentsService.saveComments(comment);
    	log.debug("comment Create end");
        
      return comment;
    }
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping( method = RequestMethod.GET)
	public List<Comments> listComments(@RequestHeader String titleId)  {

		log.debug("Comment list");
		Title title= titleService.getTitleById(Long.parseLong(titleId));
		List<Comments> commentsList= commentsService.listCommentsByTitle(title);
		commentsList.sort(Comparator.comparing(a -> a.getCommenttime()));
		return commentsList;
 }
}
