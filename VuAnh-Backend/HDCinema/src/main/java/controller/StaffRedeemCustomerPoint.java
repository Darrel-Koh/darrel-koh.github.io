package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Customer;
import entity.FoodAndDrink;
import entity.FoodDrinkOrder; 

public class StaffRedeemCustomerPoint extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter(); 
		// Read in customer database 
		ArrayList<Customer> customerList = new ArrayList<>(); 
		
		readCustomerDbs(customerList, out); 
		
		String searchId = req.getParameter("customerId"); 
		
		String reward = req.getParameter("reward"); 
		
		redeemReward(customerList, searchId, reward, out); 
		
		
		
		
		
		
	}
	
	
	public void readCustomerDbs(ArrayList<Customer> customerList, PrintWriter out) {
		
		String filePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/customer.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
            	
            	String[] parts = line.split("\t");
                
                String username = parts[0];
                String name = parts[1];
                String DOB = parts[2];
                String email = parts[3];
                String phoneNum = parts[4]; 
                String password = parts[5]; 
                int loyaltiPoints = Integer.parseInt(parts[6]); 
                
                // Generate the order from each line collected 
                Customer newCus = new Customer(username, password, name, DOB, email, phoneNum, loyaltiPoints); 
                customerList.add(newCus); 
                
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
	}

	public int getRequiredPoint(String selectedReward) {
		
		String filePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/rewardPoints.txt"; 
		
		HashMap<String, Integer> rewards = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
            	
            	String[] parts = line.split("\t");
                
                String name = parts[0];
                int point = Integer.parseInt(parts[1]);
                
                rewards.put(name.toLowerCase(), point); 
                
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

		int pointNeeded = rewards.get(selectedReward); 
		
		return pointNeeded; 
	}
	
	public void redeemReward(ArrayList<Customer> customerList, String searchId, String reward, PrintWriter out) {
		
		boolean enough = false; 
		boolean found = false; 
		
		for(Customer i: customerList) {
			
			if(i.getUsername().equalsIgnoreCase(searchId)) {
				
				out.println(i.toString()); 
				found = true; 
				
				if(i.getLoyaltyPoints() >= getRequiredPoint(reward)) {
					enough = true; 
					out.println("==============================");
					out.println(" Redeem successfully \n"); 
					// For redeem of point, can generate voucher key -> allow enter key for payment
					int currPoint = i.getLoyaltyPoints() - getRequiredPoint(reward);
					out.println("Current points: " + currPoint); 
					
					//TO DO: Edit in the customer data base 
					updateInDbs(i, currPoint); 
					
				}
			}
		}
		
		if(!found) {
			
			out.println("Username not exist"); 
		}
		
		if(!enough) {

			out.println("==============================");
			out.println("Current Point Not Enough"); 
			out.println("=============================="); 
		}
		
	}
	
	public void updateInDbs(Customer customer, int currentPoint) {
		
		String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/customer.txt"; 
        boolean found = false; 
        
        try {
            // Open the input and output streams
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".tmp"));

            String currentLine;

            // Read each line and write it to the output file,
            // unless the ID matches the one to be deleted
            while ((currentLine = reader.readLine()) != null) {
                String id = currentLine.split("\t")[0];
                if (!(id.equals(customer.getUsername()))) {
                    writer.write(currentLine + "\n");
                } else {
                	found = true; 
                    System.out.println("Row with ID " + customer.getUsername() + " deleted successfully.");
                    // Update with the new details
                    writer.write(customer.getUsername() + "\t" + customer.getName() + "\t" +customer.getDateOfBirth() + "\t" + customer.getEmail() + "\t" + customer.getPhoneNumber()
                    + "\t" + customer.getPassword() + "\t" + currentPoint + "\t" + "\n");
                    
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
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        if(!found) {
        	System.out.println("ORDER DOES NOT EXIST. PLEASE TRY AGAIN");
        }else {
        	System.out.println("Updated Order Details: "); 
        }
	}
	
}
