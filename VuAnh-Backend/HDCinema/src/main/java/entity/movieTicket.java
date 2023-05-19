package entity;

import java.util.ArrayList;
import java.util.Arrays;

public class movieTicket {
	private String ticketNumber;
    private String movieTitle;
    private ArrayList<String> seatNumber;
    private String date; 
    private String time;
    private String cinemaRoom; 
    private double price;

    public movieTicket(String ticketNumber, String movieTitle, String date, 
    		String time, ArrayList<String> seatNumber, double price, String cinemaRoom) {
        this.ticketNumber = ticketNumber; 
    	this.movieTitle = movieTitle;
    	this.date = date; 
    	this.time = time;
        this.seatNumber = seatNumber;
        this.price = price;
        this.cinemaRoom = cinemaRoom; 
    }
    
    public String getCinemaRoom() {
    	return cinemaRoom; 
    }
    
    
    public String getTicketNumber() {
    	return ticketNumber; 
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public ArrayList<String> getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
    
    public String getDate() {
    	return date;
    }
    
    public String getTime() {
    	return time;
    }
    
    
    @Override
    public String toString() {
        return "movieTicket{" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", seatNumber=" + seatNumber +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }



    
    
    
  
}
