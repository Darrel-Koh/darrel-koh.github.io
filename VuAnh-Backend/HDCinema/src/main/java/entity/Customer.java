package entity; 

public class Customer {
    private String id;
    private String name;
    private String profileType; 
    private String username; 
    private String password; 
    private int loyaltyPoints;

    public Customer(String id, String name, String profileType, String username, String password) {
        this.id=id; 
        this.username = name; 
        this.profileType=profileType; 
        this.username = username; 
        this.password=password; 
        
    }
    
    // Operator overloading with loyalty point
    public Customer(String id, String name, String profileType, String username, String password, int loyaltyPoints) {
        this.id=id; 
        this.username = name; 
        this.profileType=profileType; 
        this.username = username; 
        this.password=password; 
        this.loyaltyPoints=loyaltyPoints;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
    	return id; 
    }
    
    public String getProfileType() {
    	return profileType; 
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
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profileType='" + profileType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
    
   
}
