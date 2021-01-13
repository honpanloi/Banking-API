package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Transaction;

public interface TransactionCrudService {
	
	public int createDepositOnlyTransaction(long accountNumber, double depositeAmount) throws BusinessException;
	public int createWithdrawOnlyTransaction(long accountNumber, double withdrawAmount) throws BusinessException;
	public int createTransferTransactionWhenBothAccountsBelongToTheSamePerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount)throws BusinessException;
	public int createTransferTransactionToAnotherPerson(long targetAccountNumberTransferTo, long targetAccountNumberTransferFrom, double amount) throws BusinessException;
	public List<Transaction> searchForIncomingTransactions(long depositToAccountNum)throws BusinessException;
	public int acceptAnIncomingTransfer(long trasactionNum)throws BusinessException;
	
	
}
