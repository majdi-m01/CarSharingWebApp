package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRA_Application;
import dbadapter.MovieDatabase;

public class RegisteredUserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("navtype", "registeredUser");
		//request.setAttribute("pagetitle", "Add Movie");
		//request.getRequestDispatcher("/templates/WebpageRegisteredUser.ftl").forward(request, response);
		
		
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");

		if (action.equals("browseAvailableMovies")) {
			request.setAttribute("pagetitle", "Movies List");
			List<MovieDatabase> availableMovies = null;

			try {
				availableMovies = MRA_Application.getInstance().getAvailableMovies();
				request.setAttribute("availableMovies", availableMovies);
				request.getRequestDispatcher("/templates/moviesRepresentation.ftl").forward(request, response);
			} catch (Exception e1) {
				try {
					request.setAttribute("errormessage", "Database error: please contact the administator");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
		}else if (action.equals("makeRating")) {
			request.setAttribute("pagetitle", "Rating");
			//request.setAttribute("title", request.getParameter("title"));

			try {
				request.getRequestDispatcher("/templates/makeRating.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			request.setAttribute("pagetitle", "Add Movie");
			try {
				request.getRequestDispatcher("/templates/WebpageRegisteredUser.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("navtype", "registeredUser");


		if (request.getParameter("action").equals("browseAvailableMovies")) {
			request.setAttribute("pagetitle", "Movies List");
			List<MovieDatabase> availableMovies = null;

			try {
				availableMovies = MRA_Application.getInstance().getAvailableMovies();
				request.setAttribute("availableMovies", availableMovies);
				request.getRequestDispatcher("/templates/moviesRepresentation.ftl").forward(request, response);
			} catch (Exception e1) {
				try {
					request.setAttribute("errormessage", "Database error: please contact the administator");
					request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("errormessage", "System error: please contact the administrator");
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
		}else if (request.getParameter("action").equals("addMovie")) {

			String title = (String) request.getParameter("title");
			String actor = (String) request.getParameter("actor");
			String director = (String) request.getParameter("director");
			String publishedDate= (String) request.getParameter("publishedDate");
			try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dateFormat.parse(publishedDate);
			long time = date.getTime();
			Timestamp publishedDateSQL = new Timestamp(time);
			
			new MRA_Application().addMovie(title, director, actor, publishedDateSQL);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			

			try {
				request.setAttribute("pagetitle", "Movie Added");
				request.setAttribute("message", "New movie successful stored in the database.");
				request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("action").equals("makeRating")) {

			String title = (String) request.getParameter("title");
			String comment = (String) request.getParameter("comment");
			String Srating = (String) request.getParameter("rating");
			String SruID = (String) request.getParameter("ruID");
			int rating = Integer.parseInt(Srating);
			int ruID = Integer.parseInt(SruID);

			new MRA_Application().ratingMovie(ruID, title, rating, comment);

			try {
				request.setAttribute("pagetitle", "Rate Movie");
				request.setAttribute("message", "New movie rating successful stored in the database.");
				request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		else
			doGet(request, response);
	}
}