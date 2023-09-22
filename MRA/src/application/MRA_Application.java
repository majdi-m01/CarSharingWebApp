package application;

import interfaces.UCmds;

import java.sql.Timestamp;
import java.util.ArrayList;


import dbadapter.DBFacade;
import dbadapter.MovieDatabase;
import interfaces.RUCmds;

public class MRA_Application implements RUCmds, UCmds {
	
	private static MRA_Application instance;
	
	public static MRA_Application getInstance() {
		if (instance == null) {
			instance = new MRA_Application();
		}

		return instance;
	}

	@Override
	public void registerUser(String email, int age, String username) {
		try {
			DBFacade.getInstance().registering(email, age, username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addMovie(String title, String director, String actor, Timestamp publishedDate) {
		try {
			DBFacade.getInstance().addingNewMovie(title, director, actor, publishedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ratingMovie(int ruID, String title, int rating, String comment) {
		try {
			DBFacade.getInstance().validateRating(ruID, title, rating, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<MovieDatabase> getAvailableMovies() {
		ArrayList<MovieDatabase> result = null;
		try {
			result = DBFacade.getInstance().get_availableMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
