package entity;

import java.util.ArrayList;


public class Movie {
    private String id;
    private String title;
    private String category; 
    private double rating; 
    private double price;
    private String img; 
    private ArrayList<String> date; 
    private ArrayList<String> time; 
    
    public Movie(String id, String title, String category, double rating, double price, String img, ArrayList<String> date, ArrayList<String> time) {
        this.id = id;
        this.title = title;
        this.category = category; 
        this.rating = rating; 
        this.price = price; 
        this.img = img; 
        this.date = date;
        this.time = time; 
    }
    
    public String getCategory() {
    	return category; 
    }
    
    public double getRating() {
    	return rating; 
    }
    
    public double getPrice() {
    	return price; 
    }
    
    public String getImg() {
    	return img; 
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }
    
    public ArrayList<String> getTime() {
    	return time;
    }
    
    public void setTime(ArrayList<String> time) {
    	this.time = time; 
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Date: ");
        for (String d : date) {
            sb.append(d).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // remove trailing comma and space
        sb.append("\nTime: ");
        for (String t : time) {
            sb.append(t).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // remove trailing comma and space
        return sb.toString();
    }
}
