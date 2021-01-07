package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.AccountCrudDAO;
import com.app.exception.BusinessException;
import com.app.main.Main;
import com.app.model.Account;
import com.app.dao.dbutil.PostgresqlConnection;

public class AccountCrudDAOImpl implements AccountCrudDAO {
	
	private static Logger log = Logger.getLogger(Main.class);

	@Override
	public int creatAccountByCustomer(Account account, Double initialDeposit) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			
			
		} catch (ClassNotFoundException e) {
			log.info(e);
			log.info("connection fail");
		} catch (SQLException e) {
			log.info(e);
			log.info("sql command fail");
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
