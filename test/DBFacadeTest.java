package test;

import dbadapter.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DBFacadeTest extends TestCase {
    private MovieDatabase mdb;
    private Rating r;
    private RegisteredUserDataBase rudb;

    @Before
    public void setUp(){
        mdb = new MovieDatabase("title", "actor", "director", Timestamp.valueOf("2021-12-31 00:00:00"));
        r = new Rating(1, "title", 2, "comment");
        rudb = new RegisteredUserDataBase("username", 20, "qwerty@qwerty.com");
        ArrayList<Rating> testRatings = new ArrayList<Rating>();
        testRatings.add(r);
        mdb.setRatings(testRatings);

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS registeredUserDatabase,movieDatabase,rating";
        String sqlCreateTableMovieDatabase = "CREATE TABLE movieDatabase (title varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, publishedDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, director varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, actor varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, PRIMARY KEY(title));";
        String sqlCreateTableRegisteredUserDatabase = "CREATE TABLE registeredUserDataBase (id smallint(6) NOT NULL AUTO_INCREMENT, username varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL UNIQUE, email varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL UNIQUE, age smallint(6) NOT NULL, PRIMARY KEY (id));";
        String sqlCreateTableRating = "CREATE TABLE Rating (ruid smallint(6) NOT NULL AUTO_INCREMENT, title varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, comment varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, rating smallint(6) NOT NULL, PRIMARY KEY (ruid)), FOREIGN KEY (title) REFERENCES movieDatabase(title);";
        String sqlInsertMovie = "INSERT INTO movieDatabase (title, publishedDate, actor, director) VALUES (?,?,?,?)";
        String sqlInsertUser = "INSERT INTO registeredUserDatabase (username, email, age) VALUES (?,?,?)";
        String sqlInsertRating = "INSERT INTO rating (title, rating, comment) VALUES  (?,?,?)";

        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
                                + Configuration.getPort() + "/" + Configuration.getDatabase(),
                        Configuration.getUser(), Configuration.getPassword())) {

            try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
                psClean.executeUpdate();
            }
            try (PreparedStatement psCreateBooking = connection.prepareStatement(sqlCreateTableMovieDatabase)) {
                psCreateBooking.executeUpdate();
            }
            try (PreparedStatement psCreateHolidayOffer = connection.prepareStatement(sqlCreateTableRegisteredUserDatabase)) {
                psCreateHolidayOffer.executeUpdate();
            }
            try (PreparedStatement psCreateHolidayOffer = connection.prepareStatement(sqlCreateTableRating)) {
                psCreateHolidayOffer.executeUpdate();
            }
            try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
                psInsertMovie.setString(1, mdb.getTitle());
                psInsertMovie.setTimestamp(2, mdb.getPublishedDate());
                psInsertMovie.setString(3, mdb.getActor());
                psInsertMovie.setString(4, mdb.getDirector());
                psInsertMovie.executeUpdate();
            }
            try (PreparedStatement psInsertUser = connection.prepareStatement(sqlInsertUser)) {
                psInsertUser.setString(1, rudb.getUsername());
                psInsertUser.setString(2, rudb.getEmail());
                psInsertUser.setInt(3, rudb.getAge());
                psInsertUser.executeUpdate();
            }
            try (PreparedStatement psInsertRating = connection.prepareStatement(sqlInsertRating)) {
                psInsertRating.setString(1, r.getTitle());
                psInsertRating.setInt(2, r.getRating());
                psInsertRating.setString(3, r.getComment());
                psInsertRating.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRegistered() {

        String email = "qwerty@qwerty.com";
        int age = 20;
        String username = "username";
        ArrayList<RegisteredUserDataBase> rudbs = DBFacade.getInstance().get_Registered(email, age, username);

        assertTrue(rudbs.size() == 1);
        assertTrue(rudbs.get(0).getEmail().equals(email));
        assertTrue(rudbs.get(0).getAge() == age);
        assertTrue(rudbs.get(0).getUsername().equals(username));
    }

    @Test
    public void testGetRegisteredEmpty() {

        String email = "azerty@qwerty.com";
        int age = 18;
        String username = "user";
        ArrayList<RegisteredUserDataBase> rudbs = DBFacade.getInstance().get_Registered(email, age, username);

        assertTrue(rudbs.size() == 0);
    }

    @Test
    public void testGetRated() {
        int ruID = 1;
        String title = "title";
        int rating = 2;
        String comment = "comment";
        ArrayList<Rating> ratings = DBFacade.getInstance().get_Rated(ruID, title, rating, comment);

        assertTrue(ratings.size() == 1);
        assertTrue(ratings.get(0).getRuid() == ruID);
        assertTrue(ratings.get(0).getTitle().equals(title));
        assertTrue(ratings.get(0).getRating() == rating);
        assertTrue(ratings.get(0).getComment().equals(comment));
    }

    @Test
    public void testGetRatedEmpty() {
        int ruID = 3;
        String title = "titleV2";
        int rating = 1;
        String comment = "bad";
        ArrayList<Rating> ratings = DBFacade.getInstance().get_Rated(ruID, title, rating, comment);

        assertTrue(ratings.size() == 0);
    }

    @Test
    public void testGetAvaialbleMovies() {

        ArrayList<MovieDatabase> mdbs = DBFacade.getInstance().get_availableMovies();
        assertTrue(mdbs.size() == 1);
    }

    @Test
    public void testGetAvaialbleMoviesNOTEMPTY() {

        ArrayList<MovieDatabase> mdbs = DBFacade.getInstance().get_availableMovies();
        assertFalse(mdbs.size() == 0);
    }


}
