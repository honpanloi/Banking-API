package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Customer;

public interface AccountCrudDAO {
	
	public int creatAccountByCustomer(Customer customer, Account account, Double initialDeposit) throws BusinessException;
	public int creatAccountByEmployee(Account account, Double initialDeposit) throws BusinessException;
	public Account getAccountByAccountNum(long accountNumber) throws BusinessException;
	public List<Account> getAccountsByCustomerId (long id) throws BusinessException;
	public List<Account> getAccountsByCustomerIdAndUnapprovedStatus() throws BusinessException;
	public int updateAccountWithApprovedStatus() throws BusinessException;
	public int updateAccountWithRejectedStatus() throws BusinessException;
	
	public long getAccountByCustomerIdAndAccountType(long id, String accountType) throws BusinessException;
	
	
	public boolean checkIfanAccountIsActive(long accountNumber) throws BusinessException;

}
