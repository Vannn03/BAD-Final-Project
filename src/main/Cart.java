package main;

public class Cart {

	String shirtID, shirtName, shirtPrice;
	Integer shirtQuantity;
	String shirtType;
	
	public Cart(String shirtID, String shirtName, String shirtPrice, Integer shirtQuantity, String shirtType) {
		super();
		this.shirtID = shirtID;
		this.shirtName = shirtName;
		this.shirtPrice = shirtPrice;
		this.shirtQuantity = shirtQuantity;
		this.shirtType = shirtType;
	}
	
	public String getShirtID() {
		return shirtID;
	}
	public void setShirtID(String shirtID) {
		this.shirtID = shirtID;
	}
	public String getShirtName() {
		return shirtName;
	}
	public void setShirtName(String shirtName) {
		this.shirtName = shirtName;
	}
	public String getShirtPrice() {
		return shirtPrice;
	}
	public void setShirtPrice(String shirtPrice) {
		this.shirtPrice = shirtPrice;
	}
	public Integer getShirtQuantity() {
		return shirtQuantity;
	}
	public void setShirtQuantity(Integer shirtQuantity) {
		this.shirtQuantity = shirtQuantity;
	}
	public String getShirtType() {
		return shirtType;
	}
	public void setShirtType(String shirtType) {
		this.shirtType = shirtType;
	}
}
