package com.app.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Customer;

public interface AccountCrudService {
	
	public int creatAccountByCustomer(Customer customer, Account account, Double initialDeposit) throws BusinessException;
	public int creatAccountByEmployee(Account account, Double initialDeposit) throws BusinessException;
	public Account getAccountByAccountNum() throws BusinessException;
	public List<Account> getAccountsByCustomerId () throws BusinessException;
	public List<Account> getAccountsByCustomerIdAndUnapprovedStatus() throws BusinessException;
	public int updateAccountWithApprovedStatus() throws BusinessException;
	public int updateAccountWithRejectedStatus() throws BusinessException;
	
	public long getAccountByCustomerIdAndAccountType(long id, String accountType) throws BusinessException;

}
