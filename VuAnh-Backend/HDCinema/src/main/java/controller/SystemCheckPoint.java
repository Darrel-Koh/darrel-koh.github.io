package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.loyaltyPoint;


@WebServlet("/SystemCheckPoint")
public class SystemCheckPoint extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemCheckPoint() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ArrayList<loyaltyPoint> pointList = new ArrayList<>(); 
	
		// Template how to link JSON with Servlet
		String custId = request.getParameter("custId");
		
		readDbs(pointList); 
		
		for(loyaltyPoint p: pointList) {
			if(p.getCustId().equalsIgnoreCase(custId)) {
				response.getWriter().print(p.getPoint());
			}
		}

	  
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
	
	}