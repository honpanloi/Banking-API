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
			if(depositeAmount>=0.01d && depositeAmount<=10000.00d) {
				c = transactionCrudDao.createDepositeOnlyTransaction(accountNumber, depositeAmount);
			}else {
				throw new BusinessException("You have to deposit $0.01 or more to make it a valid deposit. \n There is also a limit of $10,000 per cash deposit. You can do as many times");
			}
		return c;
	}
	@Override
	public int createWithdrawOnlyTransaction(long accountNumber, double withdrawAmount) throws BusinessException {
		int c = 0;
		if(withdrawAmount>=0.01d && withdrawAmount<=20000.00d) {
			c = transactionCrudDao.createWithdrawOnlyTransaction(accountNumber, withdrawAmount);
		}else {
			throw new BusinessException("You have to withdraw $0.01 or more to make it a valid deposit.\n Please visit a counter if you wish to withdraw more than $20,000.");
		}
	return c;
	}
	@Override
	public int createTransferTransactionWhenBothAccountsBelongToTheSamePerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom,
			double amount) throws BusinessException {
		int c = 0;
		transactionCrudDao.createTransferTransactionWhenBothAccountsBelongToTheSamePerson(
				targetAccountNumberTransferTo, targetAccountNumberTransferFrom, amount);
		return c;
	}
	@Override
	public int createTransferTransactionToAnotherPerson(long targetAccountNumberTransferTo,
			long targetAccountNumberTransferFrom, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
