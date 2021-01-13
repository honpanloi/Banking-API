package com.app.service;

import com.app.exception.BusinessException;

public interface TransactionCrudService {
	
	public int createDepositOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException;
	public int createWithdrawOnlyTransaction(long accountNumber, double withdrawAmount) throws BusinessException;
	public int createTransferTransactionWhenBothAccountsBelongToTheSamePerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount)throws BusinessException;
	public int createTransferTransactionToAnotherPerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount) throws BusinessException;
}
