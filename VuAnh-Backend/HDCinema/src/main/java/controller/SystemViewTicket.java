package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import entity.movieTicket;


@WebServlet("/SystemViewTicket")
public class SystemViewTicket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemViewTicket() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Receive the movie id
		String ticketId = request.getParameter("ticketId").trim();
		
		 
		
		ArrayList<movieTicket> ticketList = new ArrayList<>(); 
		Gson gson = new Gson(); 
		
		
		loadFromDbs(ticketList); 
		
		for(movieTicket i:ticketList) {
			
			System.out.println(i.getTicketNumber());
			
			if(i.getTicketNumber().equalsIgnoreCase(ticketId)) {
				String jsonObj = gson.toJson(i); 
				response.getWriter().println(jsonObj);
				
			}
		}

		

	}
	
	public void loadFromDbs(ArrayList<movieTicket> ticketList ) {
		String file_movieTicket = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt"; 

		
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_movieTicket));
            String line;
            
            boolean exist = false; 


            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\t");
                String id = tokens[0];
                String movie_title = tokens[1];
                String date = tokens[2];
                String time = tokens[3]; 
                String seatNumber = tokens[4]; 
                String cinemaRoom = tokens[5];
                double ticket_price = Double.parseDouble(tokens[6]); 
                
                ArrayList<String> seatArr = convertStringToArrayList(seatNumber); 
             
                	// Temporary create a ticket object to use toString method
                	movieTicket ticket = new movieTicket(id, movie_title, date, time, seatArr, 
                			ticket_price, cinemaRoom); 
                	
                	ticketList.add(ticket); 
        			
        			exist = true; 
                
            }
            

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

	
	public static ArrayList<String> convertStringToArrayList(String input) {
        String[] elements = input.substring(1, input.length() - 1).split(", ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String element : elements) {
            arrayList.add(element);
        }
        return arrayList;
    }
	
}