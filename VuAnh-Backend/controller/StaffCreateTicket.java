package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.File;
import java.io.FileWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.movieTicket;

public class StaffCreateTicket extends HttpServlet {
	 
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		 		 
		 	String ticket_id = getNextId(); 
		 	String formattedId = String.format("%05d", Integer.parseInt(ticket_id));
		 	
			String movie_title = req.getParameter("select_movie");
			String ticket_date = req.getParameter("date");
			String ticket_time = req.getParameter("time");
			String ticket_seatNumber = req.getParameter("seat_number");
			String cinema_room = req.getParameter("cinema_room"); 
			double ticket_price = Double.parseDouble(req.getParameter("price")); 
			

			
			// create new ticket 
			movieTicket ticket = new movieTicket(formattedId, movie_title, ticket_date, ticket_time, 
					ticket_seatNumber, cinema_room, ticket_price);
			
		
			
			PrintWriter out = res.getWriter(); 
	
			out.println(ticket.toString());
			out.println("Sucessfully saved .. "); 
			
			
			// Save the ticket to database

	        String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt";

	        try {
	            File file = new File(filename);
	            FileWriter writer = new FileWriter(file, true);
	            writer.write(formattedId + "\t" + movie_title + "\t" + ticket_date + "\t" + ticket_time + "\t" + ticket_seatNumber +
	            		"\t" + cinema_room + "\t" + ticket_price + "\n");
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			    
	        
	    }
	 
	 // Generate the next Id 
	 public String getNextId() {
		    String latestId = "0";
		    int nextId = 0;

		    try (BufferedReader br = new BufferedReader(new FileReader("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt"))) {
		        String line;
		        while ((line = br.readLine()) != null) {
		            String[] parts = line.split("\t");
		            if (Integer.parseInt(parts[0]) > nextId) {
		                nextId = Integer.parseInt(parts[0]);
		            }
		        }
		        nextId++;
		        latestId = String.format("%05d", nextId);
		        System.out.println("Next ID: " + latestId);
		    } catch (IOException e) {
		        System.err.println("Error reading file: " + e.getMessage());
		    }

		    return latestId;
		}



}