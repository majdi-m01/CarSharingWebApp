package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRA_Application;
import dbadapter.RegisteredUserDataBase;

public class UserGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static List<RegisteredUserDataBase> userList = new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("navtype", "User");
		request.setAttribute("pagetitle", "Register");
		request.setAttribute("users", userList);
        request.getRequestDispatcher("/templates/WebpageUser.ftl").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("navtype", "User");
		
		if (request.getParameter("action").equals("register")) {
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String ageString = request.getParameter("age");
        int age = Integer.parseInt(ageString);
        
        
        new MRA_Application().registerUser(email, age, username);
        request.setAttribute("pagetitle", "Register");
		request.setAttribute("message", "New user successful stored in the database.");
		request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);
		
		}else
			doGet(request, response);
	}
	
}
