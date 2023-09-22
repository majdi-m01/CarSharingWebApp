package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import interfaces.IMovieDatabase;
import interfaces.IRegisteredUserDataBase;

/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns. Exceptions thrown in this class will be
 * caught with a 500 error page.
 * 
 * @author swe.uni-due.de
 *
 */
public class DBFacade implements IMovieDatabase, IRegisteredUserDataBase {
	private static DBFacade instance;

	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	public static void setInstance(DBFacade dbfacade) {
		instance = dbfacade;
	}

	@Override
	public ArrayList<RegisteredUserDataBase> get_Registered(String email, int age, String username) {
		ArrayList<RegisteredUserDataBase> result = new ArrayList<RegisteredUserDataBase>();
		String sqlSelect = "SELECT * FROM RegisteredUserDataBase";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						RegisteredUserDataBase temp = new RegisteredUserDataBase(rs.getString(1), rs.getInt(2), rs.getString(3));
						result.add(temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void registering(String email, int age, String username) {
		String sqlInsert = "INSERT INTO RegisteredUserDataBase (email,age,username) VALUES (?,?,?)";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, email);
				ps.setInt(2, age);
				ps.setString(3, username);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addingNewMovie(String title, String director, String actor, Timestamp publishedDate) {
		String sqlInsert = "INSERT INTO MovieDatabase (title,actor,director,publishedDate) VALUES (?,?,?,?)";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, title);
				ps.setString(2, actor);
				ps.setString(3, director);
				ps.setTimestamp(4, publishedDate);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Rating> get_Rated(int ruID, String title, int rating, String comment) {
		ArrayList<Rating> result = new ArrayList<Rating>();
		String sqlSelect = "SELECT * FROM Rating";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Rating temp = new Rating(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
						result.add(temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void validateRating(int ruID, String title, int rating, String comment) {
		String sqlInsert = "INSERT INTO Rating (rating,comment) VALUES (?,?) WHERE title=? AND ruID<>?";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setInt(1, rating);
				ps.setString(2, comment);
				ps.setString(3, title);
				ps.setInt(4, ruID);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<MovieDatabase> get_availableMovies() {
		ArrayList<MovieDatabase> result = new ArrayList<MovieDatabase>();
		String sqlSelect = "SELECT * FROM MovieDatabase";

		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						MovieDatabase temp = new MovieDatabase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
						result.add(temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
}
