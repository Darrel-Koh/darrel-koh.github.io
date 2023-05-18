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

import entity.Reward;


@WebServlet("/StaffRedeem")
public class StaffRedeem extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public StaffRedeem() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Template how to link JSON with Servlet
		// tring name = request.getParameter("name");
		// response.getWriter().print("Hello "+ name + "!!");
		
		ArrayList<Reward> rewardList = new ArrayList<>(); 
		
		readRewardDbs(rewardList); 
		
		// Pass this reward to ajax 
		Gson gson = new Gson(); 
		String jsonArr = gson.toJson(rewardList); 
		
		response.getWriter().print(jsonArr);

	}

	public void readRewardDbs(ArrayList<Reward> rewardList) {
        String rewardsFilePath = "/Users/vuanhngo/Documents/eclipse-314/HDCinema/database/rewardPoints.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(rewardsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split("\t");
                if (elements.length == 3) {
                    String name = elements[0];
                    int point = Integer.parseInt(elements[1]);
                    String img = elements[2];
                    
                    Reward rw = new Reward(name, point, img); 
                    
                    rewardList.add(rw); 

                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}