package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.movieTicket; 


public class StaffViewTicket extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
		String file_movieTicket = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt"; 
        PrintWriter out = res.getWriter(); 

		
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
                String cinema_room = tokens[5];
                double ticket_price = Double.parseDouble(tokens[6]); 
                
                
                if(id.equals(req.getParameter("ticket_id"))) {
                	// Temporary create a ticket object to use toString method
                	movieTicket ticket = new movieTicket(id, movie_title, date, time, seatNumber, 
                			cinema_room, ticket_price); 
                	
                	
        			out.println(ticket.toString());
        			
        			exist = true; 
       
                } 
                
            }
            
            if(!exist)
            	out.println("Ticket does not exist"); 

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
