package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson; 

import entity.Movie; 
import controller.getMovieList; 

import entity.movieTicket;


@WebServlet("/SystemCreateTicket")
public class SystemCreateTicket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemCreateTicket() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String movieTitle = request.getParameter("movieTitle");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		double price = Double.parseDouble(request.getParameter("totalPrice"));
		String seatRaw = request.getParameter("seat");
		String cinemaRoom = request.getParameter("cinemaRoom"); 
		
		System.out.println("TEST CINEMA ROOM");
		System.out.println(cinemaRoom); 
				
		// Auto generate the next ticket ID
		String ticket_id = getNextId(); 
	 	String formattedId = String.format("%05d", Integer.parseInt(ticket_id));
		
		Gson gson = new Gson();
        String[] array = gson.fromJson(seatRaw, String[].class);
        
        ArrayList<String> finalSeat = new ArrayList<>();         
        
        // The list of seat selected -> convert to correct format and display 
        for (String element : array) {
            finalSeat.add(convertSeat(Integer.parseInt(element), cinemaRoom));  
        }
        
        // create new ticket object
        movieTicket ticket = new movieTicket(formattedId, movieTitle, date, time, finalSeat, price, cinemaRoom); 
        
        // save the ticket to DBS
        saveDbs(ticket); 
	
		
		
		PrintWriter out = response.getWriter(); 
		out.println(ticket.getTicketNumber()); 

		
	}
	
	public String convertSeat(int raw, String cinemaRoom) {
		

	        int rowNumber = divideBy8(raw);
	        
	        int finalCol = 0;
	        
	        int columnNumber = (raw % 8);
	        
	        
	        // Found this seat bought -> update in DBS
	        updateSeatMap(rowNumber, columnNumber, cinemaRoom); 
	        
	       
	        	finalCol = columnNumber +1; 
	       
	        
	        String rowLetter;
	        switch (rowNumber) {
	            case 0:
	                rowLetter = "A";
	                break;
	            case 1:
	                rowLetter = "B";
	                break;
	            case 2:
	                rowLetter = "C";
	                break;
	            case 3:
	                rowLetter = "D";
	                break;
	            case 4:
	                rowLetter = "E";
	                break;
	            case 5:
	                rowLetter = "F";
	                break;
	            case 6:
	                rowLetter = "G";
	                break;
	            case 7:
	                rowLetter = "H";
	                break;
	            default:
	                rowLetter = "Unknown";
	                break;
	        }
	        
	        String output = rowLetter+finalCol; 

		
		return output; 
		
	}
	
	public void updateSeatMap(int row, int col, String cinemaRoom) {
		
		int[][] seatMap = readSeatDbs(cinemaRoom); 
		seatMap[row][col] = 1; 
		saveSeatDbs(seatMap, cinemaRoom); 
		
	}
	
	public int[][] readSeatDbs(String cinemaRoom) {
		int[][]seatMap = null; 
		File file = new File("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/seatmaps"+cinemaRoom+".txt");
        try {
            Scanner scanner = new Scanner(file);

            // Determine the dimensions of the 2D array
            int rows = 8;
            int columns = 8;

            // Create a 2D array
            seatMap = new int[rows][columns];

            // Populate the 2D array with values from the file
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (scanner.hasNextInt()) {
                        seatMap[i][j] = scanner.nextInt();
                    }
                }
            }

            // Close the scanner
            scanner.close();

            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return seatMap;
	}
	
	// Save into the correct DBS based on the movie 
	public void saveSeatDbs(int[][]seatMap, String cinemaRoom) {
		
		try {
            FileWriter writer = new FileWriter("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/seatmaps"+cinemaRoom+".txt");

            // Iterate over the array elements and write them to the file
            for (int i = 0; i < seatMap.length; i++) {
                for (int j = 0; j < seatMap[i].length; j++) {
                    writer.write(seatMap[i][j] + " ");
                }
                writer.write("\n");
            }

            // Close the writer
            writer.close();

            System.out.println("File written successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	 public static int divideBy8(int seat) {
	        
	        double result = (double) seat / 8;
	        int rowNumber = (int) Math.floor(result);
	        return rowNumber;
	    }
	 
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
	
	 public void saveDbs(movieTicket ticket) {
		 String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt";

	        try {
	            File file = new File(filename);
	            FileWriter writer = new FileWriter(file, true);
	            writer.write(ticket.getTicketNumber() + "\t" + 
	            ticket.getMovieTitle()+ "\t" + 
        		ticket.getDate() + "\t" + 
	            ticket.getTime() + "\t" + 
        		ticket.getSeatNumber() +"\t" + 
	            ticket.getCinemaRoom() + "\t" +
	            ticket.getPrice() + "\n");
	            
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
}