package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerCrudDAO {
	
	public Customer getCustomerByLoginUserName(String login_user_name) throws BusinessException;
	public Customer getCustomerByPasswordAfterByLoginUserName(Customer customer, String password) throws BusinessException;

}
