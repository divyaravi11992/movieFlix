package app.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
/**
*
* @author Divya Ravi
* 
* Hibernate Pojo Class for Commenting the title by user
* 
*/
@Entity
public class Comments {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "title",nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Title title;
	
	@ManyToOne
	@JoinColumn(name = "user",nullable=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String comment;

	private Timestamp  commenttime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public Timestamp getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Timestamp commenttime) {
		this.commenttime = commenttime;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
