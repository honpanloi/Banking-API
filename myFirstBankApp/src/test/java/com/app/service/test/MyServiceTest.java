package com.app.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Customer;
import com.app.model.Transaction;
import com.app.service.impl.AccountCrudServiceImpl;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.service.impl.TransactionCrudServiceImpl;

public class MyServiceTest {
	
	public static AccountCrudServiceImpl AccountCrudservices;
	public static CustomerCrudServiceImpl CustomerCrudService;
	public static TransactionCrudServiceImpl TransactionCrudService;
	public static Account sample;
	
	@BeforeAll
	public static void setupAccountCrudService() {
		AccountCrudservices = new AccountCrudServiceImpl();
		CustomerCrudService = new CustomerCrudServiceImpl();
		TransactionCrudService = new TransactionCrudServiceImpl();
		sample = new Account();
	}
	
	@Test
	public void testgetAccountByAccountNum() {
		long testTargetAccountNum = 200002;
		
		sample.setAccount_type("basic_saving");
		sample.setApproved_by(0);
		sample.setCurrent_balance(35145.439999999995d);
		sample.setDate_created("2020-12-28");
		sample.setNumber(200002);
		sample.setOwner_id(10001);
		sample.setRejected_by(0);
		sample.setStatus("active");
		
		Account testAccount = null;
		try {
			testAccount = AccountCrudservices.getAccountByAccountNum(testTargetAccountNum);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(sample.toString());
		System.out.println(testAccount.toString());
		
		assertTrue(sample.getAccount_type().equals(testAccount.getAccount_type()));
		//assertTrue(sample.getCurrent_balance()==testAccount.getCurrent_balance());
		assertTrue(sample.getApproved_by()==testAccount.getApproved_by());
		assertTrue(sample.getNumber()==testAccount.getNumber());
	}
	
	@Test
	public void testGetAccountByCustomerIdAndAccountType(){
		long testTargetCustomerId = 10001;
		String testTargetType = "basic_saving";
		
		long sampleAccountNum = 200002l;
		
		try {
			assertTrue(sampleAccountNum == AccountCrudservices.getAccountByCustomerIdAndAccountType(testTargetCustomerId, testTargetType));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAccountsByCustomerId() {
		List<Account> sampleList = new ArrayList<Account>();
		Account dumpmyAccount1 = new Account();
		Account dumpmyAccount2 = new Account();
		Account dumpmyAccount3 = new Account();
		Account dumpmyAccount4 = new Account();
		sampleList.add(dumpmyAccount1);
		sampleList.add(dumpmyAccount2);
		sampleList.add(dumpmyAccount3);
		sampleList.add(dumpmyAccount4);
		
		int sampleAccountsCount = sampleList.size();
		
		List<Account> TargetList = null;
		try {
			TargetList = AccountCrudservices.getAccountsByCustomerId(10001l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(sampleAccountsCount == TargetList.size());
	}
	
	@Test
	public void testCheckIfanAccountIsActive() {
		boolean sample1 = true;
		boolean sample2 = false;
		
		long targeAccountNum1 = 200002;
		long targeAccountNum2 = 200022;
		
		try {
			assertTrue(sample1 == AccountCrudservices.checkIfanAccountIsActive(targeAccountNum1));
			assertTrue(sample2 == AccountCrudservices.checkIfanAccountIsActive(targeAccountNum2));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetCustomerByLoginUserName() {
		Customer customer = null;
		
		try {
			customer = CustomerCrudService.getCustomerByLoginUserName("111111");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(customer);
	}
	
	@Test
	public void testGetCustomerByIdAndPassword() {
		Customer customer = null;
		
		try {
			customer = CustomerCrudService.getCustomerByIdAndPassword(10002, "Aa123456");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(customer);
	}
	
	@Test
	public void testGetCustomerById() {
		
		Customer customer = null;
		
		try {
			customer = CustomerCrudService.getCustomerById(10007l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(customer);
		
	}
	
	@Test
	public void testIsLoginUserNameTaken() {
		
		boolean target = false;
		
		try {
			target = CustomerCrudService.isLoginUserNameTaken("111111");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(target);
	}
	
	@Test
	public void testIsSsnTaken() {
		boolean target = false;
		
		try {
			target = CustomerCrudService.isSsnTaken(222445555);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(target);
		
	}
	
	@Test
	public void testSearchForIncomingTransactions() {
		List<Transaction> list = null;
		
		try {
			list = TransactionCrudService.searchForIncomingTransactions(200020l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, list.size());
	}
	
}
