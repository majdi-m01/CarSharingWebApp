package dbadapter;

import java.sql.Timestamp;

public class MovieDatabase {

	private String title;
	private String actor;
	private String director;
	private Timestamp publishedDate;
	
	public MovieDatabase(String title, String actor, String director, Timestamp publishedDate) {
		this.title = title;
		this.actor = actor;
		this.director = director;
		this.publishedDate = publishedDate;
	}

	public String toString() {
		return "Movie " + title + " Actors: " + actor + " Director: " + director + " Published Date: " + publishedDate;
	}

	public String getTitle() {
		return title;
	}
	public String getActor() {
		return actor;
	}
	public String getDirector() {
		return director;
	}
	public Timestamp getPublishedDate() {
		return publishedDate;
	}
}
