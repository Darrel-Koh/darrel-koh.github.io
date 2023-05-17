package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Movie;
import entity.movieTicket;

public class getMovieList {
	
	public  ArrayList<Movie> getFromDbs() {
		
		ArrayList<Movie> movieList = new ArrayList<>(); 
		
		String file_movieTicket = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/movieBasic.txt"; 
		
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_movieTicket));
            String line;
            


            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\t");
                String id = tokens[0];
                String movie_title = tokens[1];
                String category = tokens[2]; 
                double rating = Double.parseDouble(tokens[3]); 
                double price = Double.parseDouble(tokens[4]); 
                String img = tokens[5];
                
                // Have movie id, from id get session 
                ArrayList<String> dateList = new ArrayList<>(); 
                ArrayList<String> timeList = new ArrayList<>(); 

                getDateTime(dateList, timeList, id); 
                
                Movie movie = new Movie(id, movie_title, category, rating, price, img, dateList, timeList); 
                
                movieList.add(movie); // Generate the list of movie
      
                
            }
           
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return movieList;
		
	}
	
	public  void getDateTime(ArrayList<String> dateList, ArrayList<String> timeList, String movieId) {
		
		String file_movieTicket = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/movieSessions.txt"; 
		
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_movieTicket));
            String line;
            


            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\t");
                String id = tokens[0];
                String date = tokens[1];
                String time = tokens[2]; 
                
                if(movieId.equalsIgnoreCase(id)) {
                	
                	dateList.add(date); 
                	timeList.add(time); 
                	
                }
                
            }
           
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	

}
