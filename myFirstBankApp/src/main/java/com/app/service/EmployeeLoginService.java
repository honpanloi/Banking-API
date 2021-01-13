package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginService {
	
	public Employee loginEmployeeByUserNameAndPassword(String userName, String password) throws BusinessException;

}
