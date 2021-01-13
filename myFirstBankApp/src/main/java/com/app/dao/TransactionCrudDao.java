package com.app.dao;

import com.app.exception.BusinessException;

public interface TransactionCrudDao {
	
	public int createDepositeOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException;
	public int createWithdrawOnlyTransaction(long accountNumber, double withdrawAmount) throws BusinessException;
	public int createTransferTransactionWhenBothAccountsBelongToTheSamePerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount)throws BusinessException;
	public int createTransferTransactionToAnotherPerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount) throws BusinessException;
}
