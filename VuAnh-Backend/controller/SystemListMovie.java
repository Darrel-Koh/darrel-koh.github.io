package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson; 

import entity.Movie; 
import controller.getMovieList; 


@WebServlet("/SystemListMovie")
public class SystemListMovie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemListMovie() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 

	
//		// Template how to link JSON with Servlet
//		String name = request.getParameter("name");
//		response.getWriter().print("Hello "+ name + "!!");
		
		// Retrieve the movie information from the database; 
		ArrayList<Movie> movieList = new ArrayList<>(); 
		getMovieList movie = new getMovieList();
		movieList = movie.getFromDbs(); 
		
		Gson gson = new Gson(); 
		String jsonString = gson.toJson(movieList); 
		
		
		// Pass this json string to front end
		response.getWriter().print(jsonString);
				
		
	}
	
}
