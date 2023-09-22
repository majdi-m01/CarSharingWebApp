package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;

import dbadapter.MovieDatabase;

public interface RUCmds {
	
	public void addMovie(String title, String director, String actor, Timestamp publishedDate);
	
	public void ratingMovie(int ruID, String title, int rating, String comment);
	
	public ArrayList<MovieDatabase> getAvailableMovies();

}
