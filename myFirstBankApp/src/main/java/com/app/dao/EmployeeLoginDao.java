package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginDao {
	
	public Employee loginEmployeeByUserNameAndPassword(String userName, String password) throws BusinessException;

}
