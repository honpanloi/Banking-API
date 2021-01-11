package com.app.dao;

import com.app.exception.BusinessException;
import com.app.model.AccountType;

public interface AccountTypeReadDAO {
	
	public AccountType readAccountTypeByType(String type) throws BusinessException;

}
