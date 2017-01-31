package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.domain.Comments;
import app.domain.Title;
import app.repositories.CommentsRepository;
/**
*
* @author Divya Ravi
* 
* Service implementation for comment
* 
*/
@Component("commentsService")
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	private CommentsRepository commentsRepository;

	@Override
	public List<Comments> listAllComments() {
		return commentsRepository.findAll();
	}

	@Override
	public Comments getCommentsById(long id) {
		return commentsRepository.findOne(id);
	}

	@Override
	public Comments saveComments(Comments comment) {
		return commentsRepository.save(comment);
	}

	@Override
	public void deleteComments(long id) {
		commentsRepository.delete(id);
	
	}

	@Override
	public List<Comments> listCommentsByTitle(Title title) {
		
		return commentsRepository.findByTitle(title);
	}
     

	
}
