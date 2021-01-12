package com.app.dao;

import com.app.exception.BusinessException;

public interface TransactionCrudDao {
	
	public int createDepositeOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException;

}
