package com.app.service.impl;

import com.app.dao.AccountTypeReadDAO;
import com.app.dao.Impl.AccountTypeReadDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.AccountType;
import com.app.service.AccountTypeReadService;

public class AccountTypeReadServiceImpl implements AccountTypeReadService {

	AccountTypeReadDAO accountTypeReadDAO = new AccountTypeReadDAOImpl();
	
	@Override
	public AccountType readAccountTypeByType(String type) throws BusinessException {
		AccountType accountType = null;
		accountType = accountTypeReadDAO.readAccountTypeByType(type);
		return accountType;
	}

}
