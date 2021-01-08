package com.app.service.impl;

import com.app.dao.CustomerCrudDAO;

import com.app.dao.Impl.CustomerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.CustomerCrudService;

public class CustomerCrudServiceImpl implements CustomerCrudService {
	

	CustomerCrudDAO customerCrudDAOdao = new CustomerCrudDAOImpl();
	
	@Override
	public Customer getCustomerByLoginUserName(String login_user_name) throws BusinessException {
		
		Customer customer = null;
		//to be replaced by a user name validation
		if(login_user_name.length()>1) {
			customer = customerCrudDAOdao.getCustomerByLoginUserName(login_user_name);
		}else {
			throw new BusinessException("Entered user name is invalid. Please try again");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByPasswordAfterByLoginUserName(Customer customer, String password) throws BusinessException {
		
		if(password.length()>1) {
			customer = customerCrudDAOdao.getCustomerByPasswordAfterByLoginUserName(customer, password);
		}else {
			throw new BusinessException("Entered password is invalid. Please try again");
		}
		return customer;
			
	}

}
