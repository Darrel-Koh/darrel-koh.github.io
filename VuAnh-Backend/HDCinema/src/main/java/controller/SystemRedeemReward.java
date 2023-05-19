package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.loyaltyPoint;


@WebServlet("/SystemRedeemReward")
public class SystemRedeemReward extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemRedeemReward() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Template how to link JSON with Servlet
		String custId = request.getParameter("custId").trim();
		int rwPoint = Integer.parseInt(request.getParameter("rwPoint").trim());
		
		
		ArrayList<loyaltyPoint> pointList = new ArrayList<>(); 

		readDbs(pointList); 
		
		redeemReward(pointList, custId, rwPoint); 
		
		
		// response.getWriter().print(custId + " " + rwPoint);

	  
	}
	
	public void readDbs(ArrayList<loyaltyPoint> loyaltyPoints) {
		String fileName = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/loyaltyPoint.txt"; // Replace with the actual file path
        

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String custId = parts[0].trim();
                    int point = Integer.parseInt(parts[1].trim());

                    loyaltyPoint loyaltyPoint = new loyaltyPoint(custId, point);
                    loyaltyPoints.add(loyaltyPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void redeemReward(ArrayList<loyaltyPoint> customerList, String searchId, int point) {
			
			boolean enough = false; 
			boolean found = false; 
			
			for(loyaltyPoint i: customerList) {
				
				if(i.getCustId().equalsIgnoreCase(searchId)) {
										
					if(i.getPoint() >= point) {
						enough = true; 
						
						int currPoint = i.getPoint() - point;
						
						//TO DO: Edit in the customer data base 
						updateInDbs(i, currPoint); 
						
					}
				}
			}
		
		}
	
	public void updateInDbs(loyaltyPoint customer, int currentPoint) {
			
			String filename = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/loyaltyPoint.txt"; 
	        boolean found = false; 
	        
	        try {
	            // Open the input and output streams
	            BufferedReader reader = new BufferedReader(new FileReader(filename));
	            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".tmp"));
	
	            String currentLine;
	
	            // Read each line and write it to the output file,
	            // unless the ID matches the one to be deleted
	            while ((currentLine = reader.readLine()) != null) {
	                String id = currentLine.split(",")[0];
	                if (!(id.equalsIgnoreCase(customer.getCustId()))) {
	                    writer.write(currentLine + "\n");
	                } else {
	                	found = true; 
	                    System.out.println("Row with ID " + customer.getPoint() + " deleted successfully.");
	                    // Update with the new details
	                    writer.write(customer.getCustId() + "," + currentPoint + "\n");
	                    
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