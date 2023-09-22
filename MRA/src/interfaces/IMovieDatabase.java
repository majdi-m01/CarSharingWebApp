package interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;

import dbadapter.MovieDatabase;
import dbadapter.Rating;

public interface IMovieDatabase {

	public void addingNewMovie(String title, String director, String actor, Timestamp publishedDate);

	public ArrayList<Rating> get_Rated(int ruID, String title, int rating, String comment);
	
	public void validateRating(int ruID, String title, int rating, String comment);
	
	public ArrayList<MovieDatabase> get_availableMovies();
}
