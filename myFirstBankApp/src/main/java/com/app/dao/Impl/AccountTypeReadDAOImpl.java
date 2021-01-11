package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.AccountTypeReadDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.AccountType;

public class AccountTypeReadDAOImpl implements AccountTypeReadDAO {

	@Override
	public AccountType readAccountTypeByType(String type) throws BusinessException {
		AccountType accountType = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			
			String sql = "select * from my_bank_app.account_type where type = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,type);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				accountType = new AccountType();
				accountType.setType(type);
				accountType.setMin_balance_req(resultSet.getDouble("min_balance_req"));
				accountType.setOverdrawn_amount(resultSet.getDouble("overdrawn_amount"));
				accountType.setAnnual_service_fee(resultSet.getDouble("annual_service_fee"));
				accountType.setMonthly_fee(resultSet.getDouble("monthly_fee"));
				accountType.setMonthly_interest(resultSet.getDouble("monthly_interest"));
				accountType.setCredit_score_req(resultSet.getInt("credit_score_req"));
				
				
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		
		return accountType;
	}

}
