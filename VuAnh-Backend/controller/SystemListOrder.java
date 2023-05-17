package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entity.FoodAndDrink;


@WebServlet("/SystemListOrder")
public class SystemListOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemListOrder() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Retrieve the array of items available from database
		ArrayList<FoodAndDrink> FDDbs = new ArrayList<>(); 
		
		readFoodDbs(FDDbs); 
		
		Gson gson = new Gson(); 
		String jsonString = gson.toJson(FDDbs); 
		
		response.getWriter().print(jsonString);		
		
		System.out.println(jsonString); 
		
		

	}
	
	public void readFoodDbs(ArrayList<FoodAndDrink> FDDbs)  {
		
		String filePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/foodDrinkPrice.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                String name = values[0];
                Double price = Double.parseDouble(values[1]); 
                String image = values[2];

                FoodAndDrink food = new FoodAndDrink(name, price, image); 
                FDDbs.add(food); 
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}