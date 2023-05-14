package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;


public class CustomerViewMovieRating extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		
		// get the name of the movie / id 
		HttpSession session = request.getSession();
		String mName = request.getParameter("movieName");
		
		// movie list
		ArrayList<Movie> movieList = Movie.getMovieList();
		// review and rating list 
		ArrayList<ReviewAndRating> RAndRList = ReviewAndRating.getReviewAndRatingList();
		
		// check the id of movie according to the name 
		String id = "";
		boolean find = false;
		for (int i=0; i<movieList.size(); i++) {
			if(mName.equals(movieList.get(i).getName())) {
				id = movieList.get(i).getID();
				find = true;
			}
		}
		
		//
		if(find == true) {
			for (int i=0; i<RAndRList.size(); i++) {
				if(id.equals(RAndRList.get(i).getMovieID())) {
					out.println(RAndRList.get(i).toString());
				}
			}
		}
		else {
			out.println("no movie is found.");
		}
		
	}
}
