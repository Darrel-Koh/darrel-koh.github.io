package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import fiveBtwoG.entity.*;


public class CustomerViewMovieList extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		
		ArrayList<Movie> movieList = Movie.getMovieList();
		for(int i=0;i<movieList.size();i++) {
			out.println(movieList.get(i).toString());
		}
	}
}
