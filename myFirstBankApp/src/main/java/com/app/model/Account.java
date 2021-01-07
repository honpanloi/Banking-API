package com.app.model;

public class Account {
	private long number;
	private String account_type;
	private String date_created;
	private int owner_id;
	private String status;
	private int approved_by;
	private int rejected_by;
	private double current_balance;
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(int approved_by) {
		this.approved_by = approved_by;
	}
	public int getRejected_by() {
		return rejected_by;
	}
	public void setRejected_by(int rejected_by) {
		this.rejected_by = rejected_by;
	}
	public double getCurrent_balance() {
		return current_balance;
	}
	public void setCurrent_balance(double current_balance) {
		this.current_balance = current_balance;
	}
	
	public Account() {
		
	}
	
	public Account(long number, String account_type, String date_created, int owner_id, String status, int approved_by,
			int rejected_by, double current_balance) {
		super();
		this.number = number;
		this.account_type = account_type;
		this.date_created = date_created;
		this.owner_id = owner_id;
		this.status = status;
		this.approved_by = approved_by;
		this.rejected_by = rejected_by;
		this.current_balance = current_balance;
	}
	
	@Override
	public String toString() {
		return "Account Number: " + number + "\n" + ", account_type=" + account_type + ", date_created=" + date_created
				+ ", owner_id=" + owner_id + ", status=" + status + ", approved_by=" + approved_by + ", rejected_by="
				+ rejected_by + ", current_balance=" + current_balance + "]";
	}
	
	
}
