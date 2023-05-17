package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.FoodAndDrink; 
import entity.FoodDrinkOrder; 

public class StaffViewCustomerOrder extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		ArrayList<FoodDrinkOrder> FDOrderList = new ArrayList<>(); 
		PrintWriter out = res.getWriter(); 

		
		// Read in the order database 
		
		String filePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkOrder.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                
            	// Item list for that line 
            	ArrayList<FoodAndDrink> FDList = new ArrayList<>(); 
            	
            	String[] parts = line.split("\t");
                	
                // Check the number of item list 
                int itemLength = parts.length; 
                
                
                String id = parts[0];
                String status = parts[1];
                String date = parts[2];
                String time = parts[3];
                Double price = Double.parseDouble(parts[4]);
                
                for(int i = 5; i<itemLength; i++) {
                	String rawItem = parts[i]; 
                	String[] parts2 = rawItem.split("\\|"); 
                	
                	String iName = parts2[0]; 
                	int iQuantity = Integer.parseInt(parts2[1]); 
                	
                	FoodAndDrink fd = new FoodAndDrink(iName, iQuantity); 
                	FDList.add(fd); 
                	
                }
                
                // Generate the order from each line collected 
                FoodDrinkOrder fdOrder = new FoodDrinkOrder(id, status, FDList, price, date, time); 
                FDOrderList.add(fdOrder); 
                
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        

        // Order created successfully
        String searchID = req.getParameter("orderId");
        boolean found = false; 
        
        for(FoodDrinkOrder i: FDOrderList) {
        	if(i.getOrderId().equals(searchID)) {
        		out.println(i.toString()); 
        		found = true; 
        	}
        }
        
        if(!found)
        	out.println("Order ID Does Not Exist ... ");
    
		
	}

}
