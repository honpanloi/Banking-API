package com.app.model;

public class AccountType {
	
	private String type;
	private double min_balance_req;
	private double overdrawn_amount;
	private double annual_service_fee;
	private double monthly_fee;
	private double monthly_interest;
	private int credit_score_req;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMin_balance_req() {
		return min_balance_req;
	}
	public void setMin_balance_req(double min_balance_req) {
		this.min_balance_req = min_balance_req;
	}
	public double getOverdrawn_amount() {
		return overdrawn_amount;
	}
	public void setOverdrawn_amount(double overdrawn_amount) {
		this.overdrawn_amount = overdrawn_amount;
	}
	public double getAnnual_service_fee() {
		return annual_service_fee;
	}
	public void setAnnual_service_fee(double annual_service_fee) {
		this.annual_service_fee = annual_service_fee;
	}
	public double getMonthly_fee() {
		return monthly_fee;
	}
	public void setMonthly_fee(double monthly_fee) {
		this.monthly_fee = monthly_fee;
	}
	public double getMonthly_interest() {
		return monthly_interest;
	}
	public void setMonthly_interest(double monthly_interest) {
		this.monthly_interest = monthly_interest;
	}
	public int getCredit_score_req() {
		return credit_score_req;
	}
	public void setCredit_score_req(int credit_score_req) {
		this.credit_score_req = credit_score_req;
	}
	
	public AccountType() {
		
	}
	
	public AccountType(String type, double min_balance_req, double overdrawn_amount, double annual_service_fee,
			double monthly_fee, double monthly_interest, int credit_score_req) {
		super();
		this.type = type;
		this.min_balance_req = min_balance_req;
		this.overdrawn_amount = overdrawn_amount;
		this.annual_service_fee = annual_service_fee;
		this.monthly_fee = monthly_fee;
		this.monthly_interest = monthly_interest;
		this.credit_score_req = credit_score_req;
	}
	
	@Override
	public String toString() {
		if(credit_score_req == 0) {
			return 	
					"Credit Score Requirement: none" + "\n"+
					"Minimum Balance Requirement: $"+ min_balance_req + "\n"+
					"Monthly Fee(if minimum balance is not maintained): $" + monthly_fee + "\n"+
					"Annual Service Fee: $" + annual_service_fee + "\n"+
					"Monthly interest rate: "+ monthly_interest + "%\n"+
					"Overdrawn allowance: $" + overdrawn_amount
					;
		}
		
		return 	
				"Credit Score Requirement: "+ credit_score_req + "\n"+
				"Minimum Balance Requirement: $"+ min_balance_req + "\n"+
				"Monthly Fee(if minimum balance is not maintained): $" + monthly_fee + "\n"+
				"Annual Service Fee: $" + annual_service_fee + "\n"+
				"Monthly interest rate: "+ monthly_interest + "%\n"+
				"Overdrawn allowance: $" + overdrawn_amount
				;
	}
	
	public String getPrintedAccountType() {
		String result = null;
		switch (type) {
		case "basic_checking":
			result = "Basic Checking";
			break;
		case "basic_saving":
			result = "Basic Saving";
			break;
		case "prem_checking":
			result = "Premium Checking";
			break;
		case "prem_saving":
			result = "Premium Saving";
			break;

		default:
			break;
		}
		return result;
	}

}
