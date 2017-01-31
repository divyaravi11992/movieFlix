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
*  Hibernate Pojo Class for Rating the title by user
*/
@Entity
public class Rating {
	@Id
	@Column(name = "USER_ID")
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
	
	private int rate;

	private Timestamp  ratetime;
	
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	

	public Timestamp getRatetime() {
		return ratetime;
	}

	public void setRatetime(Timestamp ratetime) {
		this.ratetime = ratetime;
	}

	public  Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
