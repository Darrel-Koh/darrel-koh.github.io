package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;


public class CustomerSubmitReviews extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String accID = request.getParameter("accID");
		String mName = request.getParameter("movieName");
		String mReview = request.getParameter("movieReview");
		double mRating = Double.parseDouble(request.getParameter("movieRating").toString());
				
		
		// movie list
		ArrayList<Movie> movieList = Movie.getMovieList();
		// review and rating list 
		ArrayList<ReviewAndRating> RAndRList = ReviewAndRating.getReviewAndRatingList();
		
		// check the id of movie according to the name 
		String id = "";
		for (int i=0; i<movieList.size(); i++) {
			if(mName.equals(movieList.get(i).getName())) {
				id = movieList.get(i).getID();
			}
		}
		
		//
		boolean success = ReviewAndRating.submitRaR(accID, id, mReview, mRating);
		
		if(success) {
			out.println("Submit successfully!");
		}
		else{
			out.println("ERROR!");
		}
		
	}
}
