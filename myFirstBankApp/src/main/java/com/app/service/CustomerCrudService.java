package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Employee;

public interface CustomerCrudService {
	
	public Customer getCustomerByLoginUserName(String login_user_name) throws BusinessException;
	public Customer getCustomerByIdAndPassword(long id, String password) throws BusinessException;
	public Customer getCustomerById(long id) throws BusinessException;
	
	public boolean isLoginUserNameTaken(String userName) throws BusinessException;
	public boolean isSsnTaken(int ssn) throws BusinessException;
	
	public int creatNewCustomerByCustomer(Customer customer) throws BusinessException;
	public int creatNewCustomerByEmployee(Employee employee, Customer customer) throws BusinessException;

}
