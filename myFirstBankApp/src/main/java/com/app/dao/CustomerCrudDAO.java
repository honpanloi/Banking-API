package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerCrudDAO {
	
	public Customer getCustomerByLoginUserName(String login_user_name) throws BusinessException;
	public Customer getCustomerByIdAndPassword(long id, String password) throws BusinessException;
	public Customer getCustomerById(long id) throws BusinessException;
	
	public boolean isLoginUserNameTaken(String userName) throws BusinessException;
	public boolean isSsnTaken(int ssn) throws BusinessException;
	
	public int creatNewCustomer(Customer customer) throws BusinessException;

}
