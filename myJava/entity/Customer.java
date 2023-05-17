package entity; 

public class Customer {
    private String name;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String username; 
    private String password; 
    private int loyaltyPoints;

    public Customer(String username, String password,
    		String name, String dateOfBirth, String email, String phoneNumber, int loyaltyPoints) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username; 
        this.password = password; 
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    
    public void setUsername(String username) {
    	this.username = username; 
    }
    
    public String getUsername() {
    	return username; 
    }
    
    public void setPassword(String password) {
    	this.password = password; 
    }
    
    public String getPassword() {
    	return password; 
    }
    
    @Override
    public String toString() {
        return "Customer Details:\n" +
                "\nUsername: " + username + 
                "\nName: " + name +
                "\nDate of Birth: " + dateOfBirth +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber +
                "\nLoyalty Points: " + loyaltyPoints;
    }
}
