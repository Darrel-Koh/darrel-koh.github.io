package controller; 

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.movieTicket;

import java.io.*;

import entity.movieTicket; 


public class StaffUpdateTicket extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter(); 

				
        String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt"; 
        String idToDelete = req.getParameter("ticket_id"); 
        
        String movie_title = req.getParameter("select_movie");
		String ticket_date = req.getParameter("date");
		String ticket_time = req.getParameter("time");
		String ticket_seatNumber = req.getParameter("seat_number");
		String cinema_room = req.getParameter("cinema_room"); 
		double ticket_price = Double.parseDouble(req.getParameter("price")); 
		
		boolean found = false; 

        try {
            // Open the input and output streams
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".tmp"));

            String currentLine;

            // Read each line and write it to the output file,
            // unless the ID matches the one to be deleted
            while ((currentLine = reader.readLine()) != null) {
                String id = currentLine.split("\t")[0];
                if (!(id.equals(idToDelete))) {
                    writer.write(currentLine + "\n");
                } else {
                	found = true; 
                    System.out.println("Row with ID " + idToDelete + " deleted successfully.");
                    // Update with the new details
                    writer.write(idToDelete + "\t" + movie_title + "\t" + ticket_date + "\t" + ticket_time + "\t" + ticket_seatNumber +
	            		"\t" + cinema_room + "\t" + ticket_price + "\n");
                }
            }

            // Close the streams
            reader.close();
            writer.close();

            // Replace the original file with the temporary file
            File originalFile = new File(filename);
            originalFile.delete();
            File newFile = new File(filename + ".tmp");
            newFile.renameTo(originalFile);
        } catch (IOException e) {
            out.println("An error occurred: " + e.getMessage());
        }
        
        if(!found) {
        	System.out.println("TICKET DOES NOT EXIST. PLEASE TRY AGAIN");
        }else {
        	movieTicket ticket = new movieTicket(idToDelete, movie_title,ticket_date,
        			ticket_time, ticket_seatNumber, cinema_room, ticket_price );
        	
        	out.println("Updated Ticket Details: "); 
        	out.println(ticket.toString()); 
        }
		
	}
}
