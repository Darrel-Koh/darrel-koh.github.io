package entity;
public class movieTicket {
	private String ticketNumber;
    private String movieTitle;
    private String cinema; 
    private String seatNumber;
    private String date; 
    private String time;
    private double price;

    public movieTicket(String ticketNumber, String movieTitle, String date, String time, String seatNumber, String cinema, double price) {
        this.ticketNumber = ticketNumber; 
    	this.movieTitle = movieTitle;
    	this.date = date; 
    	this.time = time;
        this.cinema = cinema;
        this.seatNumber = seatNumber;
        this.price = price;
    }
    
    public String getTicketNumber() {
    	return ticketNumber; 
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getCinema() {
        return cinema;
    }

    public String getSeatNumber() {
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
        StringBuilder sb = new StringBuilder();

     // add top border
        sb.append("+--------------------------------------------------\n");

        // add movie title and ticket number
        sb.append(String.format("| %-50s \n", movieTitle + " - Ticket " + ticketNumber));

        // add cinema, seat number, date and time
        sb.append(String.format("| %-25s %-10s %-14s \n", "Cinema: " + cinema, "Seat: " + seatNumber, "Date: " + date));
        sb.append(String.format("| %-25s %-10s %-14s \n", "", "", "Time: " + time));

        // add price
        sb.append(String.format("| %-50s \n", "Price: $" + price));

        // add bottom border
        sb.append("+--------------------------------------------------\n");


        return sb.toString();
    }



    
    
    
  
}
