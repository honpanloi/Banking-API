package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CustomerCrudDAOImpl implements CustomerCrudDAO{

	@Override
	public Customer getCustomerByLoginUserName(String login_user_name) throws BusinessException{
		//This is supposed to only get the id number from the database
		Customer customer = null;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select id from my_bank_app.customer where login_user_name =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,login_user_name);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setLogin_user_name(login_user_name);
				customer.setId(resultSet.getLong("id"));
			}else {
				throw new BusinessException("Login unsuccessful");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerByPasswordAfterByLoginUserName(Customer customer, String password)
			throws BusinessException {
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select * from my_bank_app.customer where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,customer.getId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setFirst_name(resultSet.getString("first_name"));
				customer.setLast_name(resultSet.getString("last_name"));
				customer.setDob(resultSet.getDate("dob").toString());
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone1(resultSet.getLong("phone1"));
				customer.setPhone2(resultSet.getLong("phone2"));
				customer.setEmail(resultSet.getString("email"));
				customer.setBasic_checking_acc_id(resultSet.getLong("basic_checking_acc_id"));
				customer.setBasic_saving_acc_id(resultSet.getLong("basic_saving_acc_id"));
				customer.setPrem_checking_acc_id(resultSet.getLong("prem_checking_acc_id"));
				customer.setPrem_saving_acc_id(resultSet.getLong("prem_saving_acc_id"));
				customer.setSsn(resultSet.getString("ssn"));
				customer.setLogin_password(password);
				
				
			}else {
				throw new BusinessException("Login unsuccessful");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return customer;
	}

}
