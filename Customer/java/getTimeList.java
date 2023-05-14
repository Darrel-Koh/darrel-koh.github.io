package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;
import com.google.gson.*;

public class getTimeList extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		
		ArrayList<MovieSession> movieSessionList = MovieSession.getMovieSessionList();
		
		String movie = request.getParameter("selectedOption");
		System.out.println(movie);
		
		if(!(movie.equals("00"))) {
			ArrayList <String> time_list_str = MovieSession.provideTime(movie, movieSessionList);

			
			ArrayList <Time> time_list = new ArrayList <Time>();
			for (int i=0; i<time_list_str.size(); i++) {
				Time time = new Time (time_list_str.get(i));
				time_list.add(time);
			}
			
			
	        try {

	        	out.println(new Gson().toJson(time_list));
	            System.out.println(new Gson().toJson(time_list));

	        } finally {
	            out.close();
	        }
			
		}
	}
}
