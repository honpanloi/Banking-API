package com.app.service;

import com.app.exception.BusinessException;
import com.app.model.AccountType;

public interface AccountTypeReadService {
	
	public AccountType readAccountTypeByType(String type) throws BusinessException;

}
