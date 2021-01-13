package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.app.dao.EmployeeLoginDao;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.main.Main;
import com.app.model.Employee;

public class EmployeeLoginDaoImpl implements EmployeeLoginDao {
	
	private static Logger log = Logger.getLogger(Main.class);
	
	@Override
	public Employee loginEmployeeByUserNameAndPassword(String userName, String password) throws BusinessException {
		Employee employee = null;
		
		try(Connection connection = PostgresqlConnection.getConnection()){
			
			String sql = "select * from my_bank_app.employee where login_user_name = ? and login_password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("id"));
				employee.setLogin_password(resultSet.getString("login_password"));
				employee.setLogin_user_name(resultSet.getString("login_user_name"));
				employee.setName(resultSet.getString("name"));
			}
			
		} catch (ClassNotFoundException e) {
			log.info(e);
			log.info("connection fail");
		} catch (SQLException e) {
			log.info(e);
			log.info("sql command fail");
		}
		return employee;
	}

}
