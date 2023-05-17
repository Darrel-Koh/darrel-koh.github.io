package entity;

public class FoodAndDrink {

	private String name; 
	private int quantity; 
	private String img; 
	private double price; 
	
	public FoodAndDrink(String name, double price) {
		this.name = name;
		this.price = price; 
	}
	
	// Constructor overloading -> create Food Drink Object without image link
	public FoodAndDrink(String name, int quantity) {
		this.name = name; 
		this.quantity = quantity; 
	}
	
	public FoodAndDrink(String name, double price, String img) {
		this.name = name; 
		this.price = price; 
		this.img = img;
	}
	
	public double getPrice() {
		return price; 
	}
	
	public String getImg() {
		return img; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FoodAndDrink [name=" + name + ", quantity=" + quantity + "]";
	}
	
}
