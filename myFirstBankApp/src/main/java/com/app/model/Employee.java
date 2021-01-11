package com.app.model;

public class Employee {
	
	private long id;
	private String login_user_name;
	private String login_password;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Employee() {
		
	}
	
	public Employee(long id, String login_user_name, String login_password, String name) {
		super();
		this.id = id;
		this.login_user_name = login_user_name;
		this.login_password = login_password;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", login_user_name=" + login_user_name + ", login_password=" + login_password
				+ ", name=" + name + "]";
	}
	
	

}
