package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson; 



@WebServlet("/readSeatMap")
public class readSeatMap extends HttpServlet {
	
	public readSeatMap() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		int[][] seatMap = readDbs(); 
		editSeat(seatMap); 
		saveDbs(seatMap); 
		
		Gson gson = new Gson();
		String json = gson.toJson(seatMap); 
		
		System.out.println(json); 
		
		 // Set the response content type
        response.setContentType("application/json");

        // Send the JSON string as the response
        response.getWriter().write(json);
		
		
		  
    }
	
	public int[][] readDbs() {
		
		int[][]seatMap = null; 
		File file = new File("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/seatmaps.txt");
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
	
	public void editSeat(int[][] seatMap) {
		
		String input = "A05";

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
		
		seatMap[value][column] = 1; 

		
		
	}

	public void saveDbs(int[][]seatMap) {
		try {
            FileWriter writer = new FileWriter("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/seatmaps.txt");

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
	

}