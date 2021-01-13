package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.AccountCrudDAO;
import com.app.dao.Impl.AccountCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Customer;
import com.app.service.AccountCrudService;

public class AccountCrudServiceImpl implements AccountCrudService {
	
	private AccountCrudDAO accountCrudDAO = new AccountCrudDAOImpl();

	@Override
	public int creatAccountByCustomer(Customer customer, Account account, Double initialDeposit) throws BusinessException {
		int c = 0;
		if(initialDeposit>=200.0d && initialDeposit<=500000.0d) {
			c = accountCrudDAO.creatAccountByCustomer(customer, account, initialDeposit);
		}else {
			throw new BusinessException("A initial deposit must be between $200 and $500,000. Please try again.");
		}
		
		return c;
	}

	@Override
	public int creatAccountByEmployee(Account account, Double initialDeposit) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account getAccountByAccountNum(long accountNumber) throws BusinessException {
		Account account = null;
		if(accountNumber>200000l) {
			account = accountCrudDAO.getAccountByAccountNum(accountNumber);
		}
		return account;
	}

	@Override
	public List<Account> getAccountsByCustomerIdAndUnapprovedStatus() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateAccountWithApprovedStatus() throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccountWithRejectedStatus() throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getAccountByCustomerIdAndAccountType(long id, String accountType) throws BusinessException {
		
		long accountNumber = 0;
		accountNumber = accountCrudDAO.getAccountByCustomerIdAndAccountType(id, accountType);
		return accountNumber;
	}

	@Override
	public List<Account> getAccountsByCustomerId(long id) throws BusinessException {
		List<Account> accountsBelongtoCustomer = null;
		accountsBelongtoCustomer = accountCrudDAO.getAccountsByCustomerId(id);
		return accountsBelongtoCustomer;
	}

	@Override
	public boolean checkIfanAccountIsActive(long accountNumber) throws BusinessException {
		boolean b = false;
		b = accountCrudDAO.checkIfanAccountIsActive(accountNumber);
		return b;
	}

}
