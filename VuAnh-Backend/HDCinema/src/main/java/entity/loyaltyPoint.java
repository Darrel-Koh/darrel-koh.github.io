package entity; 

public class loyaltyPoint {
	
	private String custId;
	private int point;

	public loyaltyPoint(String custId, int point) {
		this.custId = custId;
		this.point = point;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Customer ID: " + custId + ", Loyalty Point: " + point;
	}
}