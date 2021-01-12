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
			}else {
				throw new BusinessException("You have to deposit $0.01 or more to make it a valid deposit.");
			}
		return c;
	}
	@Override
	public int createWithdrawOnlyTransaction(long accountNumber, double withdrawAmount) throws BusinessException {
		int c = 0;
		if(withdrawAmount>=0.01d && withdrawAmount<=20000.00d) {
			c = transactionCrudDao.createWithdrawOnlyTransaction(accountNumber, withdrawAmount);
		}else {
			throw new BusinessException("You have to withdraw $0.01 or more to make it a valid deposit.\n If you wish to withdraw more than $20,000, please visit a counter.");
		}
	return c;
	}

}
