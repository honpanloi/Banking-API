package com.app.service.impl;

import com.app.dao.EmployeeLoginDao;
import com.app.dao.Impl.EmployeeLoginDaoImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.service.EmployeeLoginService;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {

	EmployeeLoginDao employeeLoginDao = new EmployeeLoginDaoImpl();
	@Override
	public Employee loginEmployeeByUserNameAndPassword(String userName, String password) throws BusinessException {
		Employee Employee = null;
		Employee = employeeLoginDao.loginEmployeeByUserNameAndPassword(userName, password);
		return Employee;
	}

}
