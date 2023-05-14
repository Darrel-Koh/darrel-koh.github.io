package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;



public class CustomerPrepurchaseFoodDrink extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String food1 = request.getParameter("F001");
		String food2 = request.getParameter("F002");
		String food3 = request.getParameter("F003");
		
		// store the input value to String arrayList
		ArrayList<String> purchasedFoodDrinkList = new ArrayList<String>();
		purchasedFoodDrinkList.add(food1);
		purchasedFoodDrinkList.add(food2);
		purchasedFoodDrinkList.add(food3);
		
		// get the food and drink list
		ArrayList<FoodDrink> FDList = FoodDrink.getFoodDrinkList();
		
		// calculate the price of purchased food drink
		double amount = FoodDrink.purchaseFoodDrink(purchasedFoodDrinkList, FDList);
		
		out.println("Total amount is: "+ amount);
		
		
		
	}
}
