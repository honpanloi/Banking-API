package com.app.service.impl;

import com.app.dao.TransactionCrudDao;
import com.app.dao.Impl.TransactionCrudDaoImpl;
import com.app.exception.BusinessException;
import com.app.service.TransactionCrudService;

public class TransactionCrudServiceImpl implements TransactionCrudService {

	TransactionCrudDao transactionCrudDao = new TransactionCrudDaoImpl();
	@Override
	public int createDepositOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException {
		int c = 0;
			if(depositeAmount>=0.01d) {
				c = transactionCrudDao.createDepositeOnlyTransaction(accountNumber, depositeAmount);
			}
		return c;
	}

}
