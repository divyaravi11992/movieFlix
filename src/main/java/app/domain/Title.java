package app.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import app.enums.TitleType;

/**
*
* @author Divya Ravi
* 
*  Hibernate Pojo Class for title
*/

@Entity(name="title")
public class Title {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	
	@Lob 
	@Column( length=512,nullable=true)
	private String title;
	
	@Column
	private int year;
	
	
	@Lob 
	@Column( length=512,nullable=true)
	private String imdbID;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String rated;
	
	
	
	@Column(nullable=true)
	private Date released;
	
	
	@Lob 
	@Column( length=512,nullable=true)
	private String runtime;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String genre;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String director;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String writer;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String actors;
	
	
	@Lob 
	@Column( length=512)
	private String plot;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String language;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String country;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String awards;
	
	@Lob 
	@Column( length=512,nullable=true)
	private String poster;
	
	@Column(nullable=true)
	private int metascore;
	
	@Column(nullable=true)
	private float imdbRating;
	
	@Column(nullable=true)
	private long imdbVotes;
	
	@Column(nullable=true)
	private TitleType type;
	
	@Column(nullable=true)
	private float rating;	
	
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getMetascore() {
		return metascore;
	}

	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public long getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(long imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public TitleType getType() {
		return type;
	}

	public void setType(TitleType type) {
		this.type = type;
	}


	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Title() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
