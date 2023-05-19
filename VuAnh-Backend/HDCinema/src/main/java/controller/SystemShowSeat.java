package controller;

import java.io.File;
import java.io.FileNotFoundException;
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


@WebServlet("/SystemShowSeat")
public class SystemShowSeat extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemShowSeat() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 
		int[][]seatMapArr = readDbs(); 
		
		// Try to display the content of the array list
		
		for (int i = 0; i < seatMapArr.length; i++) {
		    // Iterate over each column in the current row
		    for (int j = 0; j < seatMapArr[i].length; j++) {
		        
		    }
		    System.out.println(); // Move to the next line after each row
		}
		
		// Try to pass to json 
		Gson gson = new Gson(); 
		String jsonMap = gson.toJson(seatMapArr); 
		
		System.out.println(jsonMap); 
		
		out.println(jsonMap); 
		
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
	
}