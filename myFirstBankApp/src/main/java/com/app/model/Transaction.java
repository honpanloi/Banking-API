package com.app.model;

public class Transaction {
	private long Trans_number;
	private String type;
	private long withdraw_from;
	private long deposit_to;
	private String status;
	private String time_requested;
	private String time_completed;
	private double amount;
	private double balance_after_withdraw;
	private double balance_after_deposit;
	private long initiator_acc;
	
	public long getTrans_number() {
		return Trans_number;
	}
	public void setTrans_number(long trans_number) {
		Trans_number = trans_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getWithdraw_from() {
		return withdraw_from;
	}
	public void setWithdraw_from(long withdraw_from) {
		this.withdraw_from = withdraw_from;
	}
	public long getDeposit_to() {
		return deposit_to;
	}
	public void setDeposit_to(long deposit_to) {
		this.deposit_to = deposit_to;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime_requested() {
		return time_requested;
	}
	public void setTime_requested(String time_requested) {
		this.time_requested = time_requested;
	}
	public String getTime_completed() {
		return time_completed;
	}
	public void setTime_completed(String time_completed) {
		this.time_completed = time_completed;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance_after_withdraw() {
		return balance_after_withdraw;
	}
	public void setBalance_after_withdraw(double balance_after_withdraw) {
		this.balance_after_withdraw = balance_after_withdraw;
	}
	public double getBalance_after_deposit() {
		return balance_after_deposit;
	}
	public void setBalance_after_deposit(double balance_after_deposit) {
		this.balance_after_deposit = balance_after_deposit;
	}
	public long getInitiator_acc() {
		return initiator_acc;
	}
	public void setInitiator_acc(long initiator_acc) {
		this.initiator_acc = initiator_acc;
	}
	
	public Transaction() {
		
	}
	
	public Transaction(long trans_number, String type, long withdraw_from, long deposit_to, String status,
			String time_requested, String time_completed, double amount, double balance_after_withdraw,
			double balance_after_deposit, long initiator_acc) {
		super();
		Trans_number = trans_number;
		this.type = type;
		this.withdraw_from = withdraw_from;
		this.deposit_to = deposit_to;
		this.status = status;
		this.time_requested = time_requested;
		this.time_completed = time_completed;
		this.amount = amount;
		this.balance_after_withdraw = balance_after_withdraw;
		this.balance_after_deposit = balance_after_deposit;
		this.initiator_acc = initiator_acc;
	}
	
	@Override
	public String toString() {
		return "Transcation [Trans_number=" + Trans_number + ", type=" + type + ", withdraw_from=" + withdraw_from
				+ ", deposit_to=" + deposit_to + ", status=" + status + ", time_requested=" + time_requested
				+ ", time_completed=" + time_completed + ", amount=" + amount + ", balance_after_withdraw="
				+ balance_after_withdraw + ", balance_after_deposit=" + balance_after_deposit + ", initiator_acc="
				+ initiator_acc + "]";
	}
	
	
	
	
}
