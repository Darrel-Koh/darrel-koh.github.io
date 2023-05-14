package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;
import com.google.gson.*;

public class getIndexList extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("application/json; charset=UTF-8");
		
		ArrayList<SeatMap> seatMapList = SeatMap.getSeatMapList();
		ArrayList<MovieSession> movieSessionList = MovieSession.getMovieSessionList();

		String movie = request.getParameter("selectedOption");
		String time = request.getParameter("selectedTime");
		
		if(!(movie.equals("00") || time.equals("00"))) {
			String seatMapId = SeatMap.findSeatMapId(time, movie, movieSessionList);
			int row = 0;
			int col =0;
			
			for (int i=0; i<seatMapList.size(); i++) {
				if(seatMapId.equals(seatMapList.get(i).getID())) {
					row = seatMapList.get(i).getRow();
					col = seatMapList.get(i).getColumn();
				}
			}
			
			ArrayList <String> indexList_str = SeatMap.indexList(row, col);
			ArrayList <seatMapIndex> indexList = new ArrayList <seatMapIndex>();
			for (int i=0; i<indexList_str.size(); i++) {
				
				seatMapIndex index = new seatMapIndex(indexList_str.get(i));
				indexList.add(index);
			}
			System.out.println(indexList_str);
			System.out.println(indexList);
			
			try {
				out.println(new Gson().toJson(indexList));
	            System.out.println(new Gson().toJson(indexList));
			}
			finally {
				out.close();
			}
			
		}
		
		
		
	}
}
