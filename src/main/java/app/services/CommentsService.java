package app.services;

import java.util.List;

import app.domain.Comments;
import app.domain.Title;
/**
*
* @author Divya Ravi
* 
* Service interface for comments
* 
*/
public interface CommentsService {

	List<Comments> listAllComments();

	Comments getCommentsById(long id);



	Comments saveComments(Comments comment);

	void deleteComments(long id);

	List<Comments> listCommentsByTitle(Title title);

	
}

