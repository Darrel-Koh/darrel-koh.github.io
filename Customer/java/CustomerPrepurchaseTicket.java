package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;
import com.google.gson.*;


public class CustomerPrepurchaseTicket extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		
		// send response back to client 
		String movie = request.getParameter("movie");
		String time = request.getParameter("time");
		String index = request.getParameter("index");
		
		ArrayList<MovieSession> movieSessionList = MovieSession.getMovieSessionList();
		String seatMapId = SeatMap.findSeatMapId(time, movie, movieSessionList);
		ArrayList<SeatMap> seatMapList = SeatMap.getSeatMapList();
		

		int size = index.length();
		int place = index.indexOf(",");
		String row_str = index.substring(0, place); 
		int row = Integer.parseInt(row_str);
		String col_str = index.substring(place+1, size);
		int col = Integer.parseInt(col_str);
		
		
		boolean check = SeatMap.checkSeatMap(seatMapId, row, col, seatMapList);
		if(check == true) {
			out.println("empty place and successfully booked");
			System.out.println("empty place and successfully booked");
		}
		else {
			out.println("occupied seat");
			System.out.println("occupied seat");
		}
		
		// update to txt file 
		boolean updated = SeatMap.updateSeatMap(seatMapList);
		
		if(updated == true) {
			out.println("successfully updated");
		}
		else {
			out.println("FAIL updated");
		}
		
		
		}
	}


