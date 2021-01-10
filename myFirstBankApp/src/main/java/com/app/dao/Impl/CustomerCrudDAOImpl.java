package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
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
	public Customer getCustomerByIdAndPassword(long id, String password)
			throws BusinessException {
		Customer customer = new Customer();
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select login_password from my_bank_app.customer where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next() && password.equals(resultSet.getString("login_password"))) {
				
				customer = getCustomerById(id);
				
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
	public Customer getCustomerById(long id) throws BusinessException {
		Customer customer = new Customer();
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "select first_name, last_name, dob, address, phone1, phone2, email, basic_checking_acc_id, basic_saving_acc_id, prem_checking_acc_id, prem_saving_acc_id from my_bank_app.customer where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				customer.setId(id);
				customer.setFirst_name(resultSet.getString("first_name"));
				customer.setLast_name(resultSet.getString("last_name"));
				customer.setDob(resultSet.getDate("dob"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhone1(resultSet.getLong("phone1"));
				customer.setPhone2(resultSet.getLong("phone2"));
				customer.setEmail(resultSet.getString("email"));
				customer.setBasic_checking_acc_id(resultSet.getLong("basic_checking_acc_id"));
				customer.setBasic_saving_acc_id(resultSet.getLong("basic_saving_acc_id"));
				customer.setPrem_checking_acc_id(resultSet.getLong("prem_checking_acc_id"));
				customer.setPrem_saving_acc_id(resultSet.getLong("prem_saving_acc_id"));

				
				
				
			}else {
				throw new BusinessException("Customer not found");
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return customer;
	}

	@Override
	public boolean isLoginUserNameTaken(String userName) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select login_user_name from my_bank_app.customer where login_user_name =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userName);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				throw new BusinessException("User name: "+userName+" is already taken.");
			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public boolean isSsnTaken(int ssn) throws BusinessException {
		boolean isTaken = true;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select ssn from my_bank_app.customer where ssn =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,ssn);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				throw new BusinessException("Social Security : "+ssn+" is already registered.");
			}else {
				isTaken = false;
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal error occured contact sysadmin(Class)");
		} catch (SQLException e) {
			throw new BusinessException("Internal error occured contact sysadmin(sql)");
		}
		
		return isTaken;
	}

	@Override
	public int creatNewCustomer(Customer customer) throws BusinessException {
		
		int c =0;
		try(Connection connection = PostgresqlConnection.getConnection()){
			String sql = "insert into my_bank_app.customer(first_name,last_name,salutation,dob,address,phone1,phone2,email,login_user_name,login_password,credit_score,ssn) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirst_name());
			preparedStatement.setString(2, customer.getLast_name());
			preparedStatement.setString(3, customer.getSalutation());
			preparedStatement.setDate(4, customer.getDob());
			preparedStatement.setString(5, customer.getAddress());
			preparedStatement.setLong(6, customer.getPhone1());
			preparedStatement.setLong(7, customer.getPhone2());
			preparedStatement.setString(8, customer.getEmail());
			preparedStatement.setString(9, customer.getLogin_user_name());
			preparedStatement.setString(10, customer.getLogin_password());
			preparedStatement.setInt(11, customer.getCredit_score());
			preparedStatement.setInt(12, customer.getSsn());
			
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Registration failed. Internal error occured contact sysadmin");
		}
		return c;
	}

}
