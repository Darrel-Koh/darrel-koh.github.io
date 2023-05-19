package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import entity.movieTicket;



@WebServlet("/SystemUpdateTicket")
public class SystemUpdateTicket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemUpdateTicket() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String searchId = request.getParameter("idValue").trim();
		String movieTitle = null; 
		ArrayList<String> oriSeatList = new ArrayList<>(); 
		String oriCinemaRoom = null;
		double price = 0; 

		ArrayList<movieTicket> ticketList = new ArrayList<>(); 
		Gson gson = new Gson(); 
		
		loadFromDbs(ticketList); 
		
		for(movieTicket i:ticketList) {
						
			if(i.getTicketNumber().equalsIgnoreCase(searchId)) {
				movieTitle = i.getMovieTitle(); 
				price = i.getPrice(); 
				oriSeatList = i.getSeatNumber();
				oriCinemaRoom = i.getCinemaRoom(); 
				
			}
		}
		
		// Update the seat map file (remove the old seat) 
		updateSeatMap(oriCinemaRoom, oriSeatList);
		
		// Getting the new values by user
		String seat = request.getParameter("seat");
		String time = request.getParameter("time");
		String date = request.getParameter("date");
		String cinemaRoom = request.getParameter("cinemaRoom"); 
		
        ArrayList<String> seatList = new ArrayList<>(Arrays.asList(seat.split("\\s*,\\s*")));
        
        // update the new seat map 
        updateSeatMapNew(cinemaRoom, seatList); 


		// Load to DBS 
        String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/ticket.txt"; 

        
        try {
            // Open the input and output streams
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".tmp"));

            String currentLine;

            // Read each line and write it to the output file,
            // unless the ID matches the one to be deleted
            while ((currentLine = reader.readLine()) != null) {
                String id = currentLine.split("\t")[0];
                
                if (!(id.equals(searchId))) {
                    writer.write(currentLine + "\n");
                } else {
                    System.out.println("Row with ID " + searchId + " deleted successfully.");
                    // Update with the new details
                    writer.write(searchId + "\t" + movieTitle + "\t" + date + "\t" + time + "\t" + seatList +
	            		"\t" + cinemaRoom + "\t" + price + "\n");
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
            System.out.println("An error occurred: " + e.getMessage());
        }
		
		response.getWriter().print(searchId); 
		

	}	 
	
	public void updateSeatMapNew(String cinemaRoom, ArrayList<String> oriSeatList) {
		
		int[][] seatMap = readDbs(cinemaRoom); 
				
				
				for(String i: oriSeatList) {
					
					convertToSeatIndexNew(i, seatMap); // Convert from A1 -> get location of the seat 
								
				}
				saveDbs(seatMap, cinemaRoom); 
	}
	 
	 
	public void updateSeatMap(String cinemaRoom, ArrayList<String> oriSeatList) {
		
		int[][] seatMap = readDbs(cinemaRoom); 
		
		
		for(String i: oriSeatList) {
			
			convertToSeatIndex(i, seatMap); // Convert from A1 -> get location of the seat 
						
		}
		
		saveDbs(seatMap, cinemaRoom); 

		
	}
	
	public void convertToSeatIndex(String input, int [][]seatMap) {
		// Extract the letter and number using regular expressions
				String letter = input.replaceAll("[^A-Za-z]", "");
				String number = input.replaceAll("[^0-9]", "");
				
				String row = letter.toLowerCase(); 
				int column = Integer.parseInt(number); 
				
				
				int value;
				switch (row) {
				    case "a":
				        value = 0;
				        break;
				    case "b":
				        value = 1;
				        break;
				    case "c":
				        value = 2;
				        break;
				    case "d":
				        value = 3;
				        break;
				    case "e":
				        value = 4;
				        break;
				    case "f":
				        value = 5;
				        break;
				    case "g":
				        value = 6;
				        break;
				    case "h":
				        value = 7;
				        break;
				    default:
				        value = -1; 
				}
				
				seatMap[value][column-1] = 0;

	}
	
	public void convertToSeatIndexNew(String input, int[][]seatMap) {
		// Extract the letter and number using regular expressions
		String letter = input.replaceAll("[^A-Za-z]", "");
		String number = input.replaceAll("[^0-9]", "");
		
		String row = letter.toLowerCase(); 
		int column = Integer.parseInt(number); 
		
		
		int value;
		switch (row) {
		    case "a":
		        value = 0;
		        break;
		    case "b":
		        value = 1;
		        break;
		    case "c":
		        value = 2;
		        break;
		    case "d":
		        value = 3;
		        break;
		    case "e":
		        value = 4;
		        break;
		    case "f":
		        value = 5;
		        break;
		    case "g":
		        value = 6;
		        break;
		    case "h":
		        value = 7;
		        break;
		    default:
		        value = -1; 
		}
		
		seatMap[value][column-1] = 1;
	}
	
	public int[][] readDbs(String cinemaRoom) {
		
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
	
	public void saveDbs(int[][]seatMap, String cinemaRoom) {
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
	
	public static ArrayList<String> convertStringToArrayList(String input) {
        String[] elements = input.substring(1, input.length() - 1).split(", ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String element : elements) {
            arrayList.add(element);
        }
        return arrayList;
    }
}