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
	public Customer getCustomerByIdAndPassword(long id, String password) throws BusinessException {
		Customer customer = null;
		if(password.length()>1) {
			customer = customerCrudDAOdao.getCustomerByIdAndPassword(id, password);
		}else {
			throw new BusinessException("Entered password is invalid. Please try again");
		}
		return customer;
			
	}

	@Override
	public Customer getCustomerById(long id) throws BusinessException {
		Customer customer = null;
		if(id>=10000) {
			customerCrudDAOdao.getCustomerById(id);
		}
		return customer;
	}

	@Override
	public boolean isLoginUserNameTaken(String userName) throws BusinessException {
		boolean isTaken = true;
			isTaken = customerCrudDAOdao.isLoginUserNameTaken(userName);
		return isTaken;
	}

	@Override
	public boolean isSsnTaken(int ssn) throws BusinessException {
		boolean isTaken = true;
			isTaken = customerCrudDAOdao.isSsnTaken(ssn);
		return isTaken;
	}

	@Override
	public int creatNewCustomer(Customer customer) throws BusinessException {
		int c = 0;
		c = customerCrudDAOdao.creatNewCustomer(customer);
		return c;
	}

}
