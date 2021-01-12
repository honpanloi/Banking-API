package com.app.service;

import com.app.exception.BusinessException;

public interface TransactionCrudService {
	
	public int createDepositOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException;

}
