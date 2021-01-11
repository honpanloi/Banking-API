package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.AccountCrudDAO;
import com.app.exception.BusinessException;
import com.app.main.Main;
import com.app.model.Account;
import com.app.model.Customer;
import com.app.dao.dbutil.PostgresqlConnection;

public class AccountCrudDAOImpl implements AccountCrudDAO {
	
	private static Logger log = Logger.getLogger(Main.class);

	@Override
	public int creatAccountByCustomer(Customer customer, Account account, Double initialDeposit) throws BusinessException {
		int c = 0;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			
			
			String sql = "insert into my_bank_app.account(owner_id, account_type, current_balance, date_created) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, customer.getId());
			preparedStatement.setString(2, account.getAccount_type());
			preparedStatement.setDouble(3, initialDeposit);
			Date currentDate = new Date();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd EEEE z Z HH:mm:ss");
			preparedStatement.setString(4, format1.format(currentDate).toString());
			
			c += preparedStatement.executeUpdate();
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			String sql2 = "";
			
			switch (account.getAccount_type()) {
			case "basic_checking":
				sql2 = "update my_bank_app.customer set basic_checking_acc_id = ? where customer.id = ?";
				break;
			case "basic_saving":
				sql2 = "update my_bank_app.customer set basic_saving_acc_id = ? where customer.id = ?";
				break;
			case "prem_checking":
				sql2 = "update my_bank_app.customer set prem_checking_acc_id = ? where customer.id = ?";
				break;
			case "prem_saving":
				sql2 = "update my_bank_app.customer set prem_saving_acc_id = ? where customer.id = ?";
				break;

			default:
				break;
			}
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			
			try {
				long tempLong = getAccountByCustomerIdAndAccountType(customer.getId(), account.getAccount_type());
				account.setNumber(tempLong);
			} catch (BusinessException e) {
				e.getMessage();
			}
			
			preparedStatement2.setLong(1, account.getNumber());
			preparedStatement2.setLong(2, customer.getId());
			
			c += preparedStatement2.executeUpdate();
		
			
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

	@Override
	public long getAccountByCustomerIdAndAccountType(long id, String accountType) throws BusinessException {
		Account tempAccount = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select \"number\" from my_bank_app.account where owner_id = ? and account_type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			preparedStatement.setString(2,accountType);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				tempAccount = new Account();
				tempAccount.setNumber(resultSet.getLong("number"));
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			log.info(e);
			log.info("connection fail");
		} catch (SQLException e) {
			log.info(e);
			log.info("sql command fail");
		}
		
		return tempAccount.getNumber();
	}

}
