package main;

import java.sql.Date;

public class TransactionHeader {
	
	Integer transactionID;
	String UserID;
	Date transactionDate;
	
	public TransactionHeader(Integer transactionID, String userID, Date transactionDate) {
		super();
		this.transactionID = transactionID;
		this.UserID = userID;
		this.transactionDate = transactionDate;
	}
	
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
