package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;


public class CustomerRedeemReward extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		// ask for customer accID and points to redeem
		String accID = request.getParameter("accID");
		String points_str = request.getParameter("points");
		int points = Integer.parseInt(points_str);
		
		// get the loyalty point list
		ArrayList<LoyaltyPoint> loyaltyList = LoyaltyPoint.getLoyaltyPointList();
		
		boolean result = LoyaltyPoint.redeemReward(accID, points, loyaltyList);
		
		if(result == true) {
			for(int i=0; i<loyaltyList.size(); i++) {
				out.println(loyaltyList.get(i).toString());
			}
			out.println("Redeem successfully");
		}
		else {
			out.println("Redeemtion fail, insufficient point");
		}
	}
}
