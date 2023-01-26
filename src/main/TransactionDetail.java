package main;

public class TransactionDetail {

	Integer transactionID;
	String productID;
	Integer transactionQuantity;
	
	public TransactionDetail(Integer transactionID, String productID, Integer transactionQuantity) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.transactionQuantity = transactionQuantity;
	}
	
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public Integer getTransactionQuantity() {
		return transactionQuantity;
	}
	public void setTransactionQuantity(Integer transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}
	
	
}
