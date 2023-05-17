package entity;

import java.util.ArrayList;

public class FoodDrinkOrder {

	private String orderId;
	private String status; 
	private ArrayList<FoodAndDrink> itemList;
	private double orderPrice;
	private String orderDate;
	private String orderTime;


	// Without ticket ID 
	public FoodDrinkOrder(String orderId, String status, ArrayList<FoodAndDrink> itemList,
			double orderPrice, String orderDate, String orderTime) {
		this.orderId = orderId;
		this.status = status; 
		this.itemList = itemList;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public ArrayList<FoodAndDrink> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<FoodAndDrink> itemList) {
		this.itemList = itemList;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public void setStatus(String status){
		this.status = status; 
	}
	
	public String getStatus() {
		return status; 
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("=============================\n");
	    sb.append("          ORDER RECEIPT      \n");
	    sb.append("=============================\n");
	    sb.append("Order ID: ").append(orderId).append("\n");
	    sb.append("Date: ").append(orderDate).append("\n");
	    sb.append("Time: ").append(orderTime).append("\n");
	    sb.append("Items:\n");

	    for (FoodAndDrink item : itemList) {
	        sb.append(String.format("%-20s %-20s\n", item.getName(), item.getQuantity()));
	    }

	    sb.append("\n");
	    sb.append("Status: ").append(status).append("\n");
	    sb.append("Total price: $").append(String.format("%.2f", orderPrice)).append("\n");
	    sb.append("=============================\n");

	    return sb.toString();
	}


}
