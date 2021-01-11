package com.app.model;

import java.sql.Date;

public class Customer {
	
	private long id;
	private String first_name;
	private String last_name;
	private String salutation;
	private int ssn;
	private Date dob;
	private String address;
	private long phone1;
	private long phone2;
	private String email;
	private long basic_checking_acc_id;
	private long basic_saving_acc_id;
	private long prem_checking_acc_id;
	private long prem_saving_acc_id;
	private String login_user_name;
	private String login_password;
	private int credit_score;
	private long register_by_employee;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone1() {
		return phone1;
	}
	public void setPhone1(long phone1) {
		this.phone1 = phone1;
	}
	public long getPhone2() {
		return phone2;
	}
	public void setPhone2(long phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getBasic_checking_acc_id() {
		return basic_checking_acc_id;
	}
	public void setBasic_checking_acc_id(long basic_checking_acc_id) {
		this.basic_checking_acc_id = basic_checking_acc_id;
	}
	public long getBasic_saving_acc_id() {
		return basic_saving_acc_id;
	}
	public void setBasic_saving_acc_id(long basic_saving_acc_id) {
		this.basic_saving_acc_id = basic_saving_acc_id;
	}
	public long getPrem_checking_acc_id() {
		return prem_checking_acc_id;
	}
	public void setPrem_checking_acc_id(long prem_checking_acc_id) {
		this.prem_checking_acc_id = prem_checking_acc_id;
	}
	public long getPrem_saving_acc_id() {
		return prem_saving_acc_id;
	}
	public void setPrem_saving_acc_id(long prem_saving_acc_id) {
		this.prem_saving_acc_id = prem_saving_acc_id;
	}
	public String getLogin_user_name() {
		return login_user_name;
	}
	public void setLogin_user_name(String login_user_name) {
		this.login_user_name = login_user_name;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	public int getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(int credit_score) {
		this.credit_score = credit_score;
	}
	public long getRegister_by_employee() {
		return register_by_employee;
	}
	public void setRegister_by_employee(long register_by_employee) {
		this.register_by_employee = register_by_employee;
	}
	
	public Customer() {
		
	}
	
	public Customer(int id, String first_name, String last_name, String salutation, int ssn, Date dob,
			String address, long phone1, long phone2, String email, String login_user_name, String login_password,
			int credit_score) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.salutation = salutation;
		this.ssn = ssn;
		this.dob = dob;
		this.address = address;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email = email;
		this.login_user_name = login_user_name;
		this.login_password = login_password;
		this.credit_score = credit_score;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", salutation="
				+ salutation + ", ssn=" + ssn + ", dob=" + dob + ", address=" + address + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", email=" + email + ", basic_checking_acc_id=" + basic_checking_acc_id
				+ ", basic_saving_acc_id=" + basic_saving_acc_id + ", prem_checking_acc_id=" + prem_checking_acc_id
				+ ", prem_saving_acc_id=" + prem_saving_acc_id + ", login_user_name=" + login_user_name
				+ ", login_password=" + login_password + ", credit_score=" + credit_score + "]";
	}
	
	
	

}
