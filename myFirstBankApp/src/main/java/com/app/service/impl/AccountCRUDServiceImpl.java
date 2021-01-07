package com.app.service.impl;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.service.AccountCRUDService;

public class AccountCRUDServiceImpl implements AccountCRUDService {
	
	private AccountCRUDService accountCRUDServiceImpl = new AccountCRUDServiceImpl();

	@Override
	public int creatAccountByCustomer(Account account, Double initialDeposit) throws BusinessException {
		int c = 0;
		
		if(initialDeposit>=200 && initialDeposit<=500000) {
			c = accountCRUDServiceImpl.creatAccountByCustomer(account, initialDeposit);
		}
		
		return c;
	}

	@Override
	public int creatAccountByEmployee(Account account, Double initialDeposit) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account getAccountByAccountNum() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByCustomerId() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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

}
