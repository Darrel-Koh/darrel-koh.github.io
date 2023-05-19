package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import entity.FoodAndDrink;
import entity.FoodDrinkOrder;


@WebServlet("/updateOrder")
public class updateOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public updateOrder() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 

		
		String rawName = request.getParameter("names"); 
		String rawQuantity = request.getParameter("quantities"); 
		String searchId = request.getParameter("searchId");
		// Get the food and drinks name. The index of quantity same as index of food drink 
		 
		
		
		System.out.println(rawName); 
		System.out.println(rawQuantity); 
		
		 Gson gson = new Gson();
	     String[] name = gson.fromJson(rawName, String[].class);
	     String[] quantity = gson.fromJson(rawQuantity, String[].class);
		
		
		
		// Get date order created 
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
		
	   
	    
	    LocalDate date = LocalDate.now();
	    String formattedDate = date.toString();
	    
		
		// Create list of food and drink
		ArrayList<FoodAndDrink> FDList = new ArrayList<>(); 
		
		System.out.println("Check value of 2 arrays");

		
		for(int i =0; i<name.length; i++) {
						
			FoodAndDrink fd = new FoodAndDrink(name[i], Integer.parseInt(quantity[i])); 
			FDList.add(fd); 
						
		}
		double final_price = getPrice(FDList); 
		// Generate the order 
		FoodDrinkOrder order = new FoodDrinkOrder(searchId, "preparing", FDList, final_price, formattedDate, formattedTime);
		
		// Return this object back to JS 
		 response.getWriter().print(order.getOrderId());
		
		// Display the order details 
		//out.println(order.toString());
		
		// Save order to database 
		
		// Update order in DBS
		 
		 String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkOrder.txt";
		 
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
                    writer.write(order.getOrderId() + "\t" + "preparing" + "\t" + order.getOrderDate() + "\t" + order.getOrderTime() + "\t" + final_price + "\t");
                    
                    ArrayList<FoodAndDrink> itemList = order.getItemList();
                    
                    StringBuilder sb = new StringBuilder();
                    for (FoodAndDrink item : itemList) {
                        sb.append(item.getName() + "|" + item.getQuantity() + "\t");
                    }
                                
                    writer.write(sb.toString() + "\n");
                    System.out.println("Order written to file successfully.");
                }
            }

            // Replace the original file with the temporary file
            File originalFile = new File(filename);
            originalFile.delete();
            System.out.println("Old File Deleted"); 
            
            File newFile = new File(filename + ".tmp");
            
            System.out.println("File name changed");
            newFile.renameTo(originalFile);
            
            // Close the streams
            reader.close();
            writer.flush();
            writer.close();

            
        } catch (IOException e) {
            out.println("An error occurred: " + e.getMessage());
        }
        
		
		System.out.println("COMPLETED"); 
		
		
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
	
}