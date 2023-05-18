package controller;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.FoodAndDrink; 
import entity.FoodDrinkOrder; 

@WebServlet("/StaffCreateCustomerOrder")
public class StaffCreateCustomerOrder extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	
	public StaffCreateCustomerOrder() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter(); 

		
		String[] name = req.getParameterValues("name"); 
		String[] quantity = req.getParameterValues("quantitie"); 
		// Get the food and drinks name. The index of quantity same as index of food drink 
		
		// Get date order created 
		LocalTime time = LocalTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
	    String formattedTime = time.format(formatter);
	    
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	    String formattedDate = date.format(dateFormatter);
		
		// Create list of food and drink
		ArrayList<FoodAndDrink> FDList = new ArrayList<>(); 
		
		System.out.println("Check value of 2 arrays");

		
		for(int i =0; i<name.length; i++) {
						
			FoodAndDrink fd = new FoodAndDrink(name[i], Integer.parseInt(quantity[i])); 
			FDList.add(fd); 
						
		}
		double final_price = getPrice(FDList); 
		// Generate the order 
		FoodDrinkOrder order = new FoodDrinkOrder(getNextId(), "preparing", FDList, final_price, formattedDate, formattedTime);
		
		// Display the order details 
		out.println(order.toString());
		
		// Save order to database 
		orderSave(order, final_price ); 
		
		System.out.println("COMPLETED"); 
		
		
	}
	
	// Function to auto generate ID
	 public String getNextId() {
		    String latestId = "0";
		    int nextId = 0;

		    try (BufferedReader br = new BufferedReader(new FileReader("/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkOrder.txt"))) {
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
	
	// Get the price of items from the database 
	public double getPrice(ArrayList<FoodAndDrink> FDList) {
		
		double total_price = 0; 
		
		// Hash map to store the item and its price
	    Map<String, Double> map = new HashMap<>();

		String priceFile = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkPrice.txt"; 
		
		try (BufferedReader br = new BufferedReader(new FileReader(priceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                
                map.put(name, price); 
                
                // System.out.println(map.get(FDList.get(0).getName()));
                
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
		
		// Get the price of each item
		for(FoodAndDrink fd: FDList) {
		    try {
		    	double item_price = map.get(fd.getName().toUpperCase()); 
		    	int item_quantity = fd.getQuantity(); 
		    	
		        total_price += item_price * item_quantity; 
		        
		    } catch (NullPointerException e) {
		        // System.out.println("Error: " + e.getMessage());
		        // Handle the error case here, such as setting a default price
		        // total_price += 0.0; // Default price of 0
		    }
		}
		
		return total_price; 
		
	}
	
	// Save order to database 
	public void orderSave(FoodDrinkOrder order, double final_price) {
		
		String filePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkOrder.txt"; 
		
		try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(order.getOrderId() + "\t" + "preparing" + "\t" + order.getOrderDate() + "\t" + order.getOrderTime() + "\t" + final_price + "\t");
            
            ArrayList<FoodAndDrink> itemList = order.getItemList();
            StringBuilder sb = new StringBuilder();
            for (FoodAndDrink item : itemList) {
                sb.append(item.getName() + "|" + item.getQuantity() + "\t");
            }
                        
            writer.write(sb.toString() + "\n");
            writer.close();
            System.out.println("Order written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
		
	}


}
