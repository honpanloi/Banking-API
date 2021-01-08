package com.app.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.AccountType;
import com.app.model.Customer;
import com.app.service.CustomerCrudService;
import com.app.service.impl.CustomerCrudServiceImpl;

public class Main {
	
	
	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Customer customer = null;
			
		
		int ch = 0;
		do {
			//options
			log.info("MyBankApp V1.10");
			log.info("");
			log.info("Please choose an option below by entering the number associated with that option");
			log.info("1) I am a customer");
			log.info("2) I am an employee");
			log.info("3) Exit ppplication");
						
			try {
				ch= Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				
			}
			
			switch (ch) {
			
			case 1:
				showCustomerLoginMenu(sc,customer);
				break;
			case 2:
				log.info("Hi employee");
				break;
			case 3:
				log.info("Thank you for using MyBankApp.");
				sc.close();
				break;

			default: printOptionNotAvailable(); break;
			}
		} while (ch!=3);
		
		

	}
	
	private static void showCustomerLoginMenu(Scanner sc, Customer customer) {
		
		int chclm = 0;
		do {

			//options
			log.info("Welcome!");
			log.info("Please choose an option below by entering the number associated with that option");
			log.info("1) Login");
			log.info("2) Register");
			log.info("3) Go back");
			
			try {
				chclm= Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				
			}
			
			switch (chclm) {
			
			case 1:
				
				CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();
				
				log.info("Please enter your user name");
				
				String userNameEntered = "";
				String passwordEntered = "";
				
				customer = new Customer();
				
				userNameEntered = sc.nextLine();
				try {
					customer = customerCRUDService.getCustomerByLoginUserName(userNameEntered);
				} catch (BusinessException e) {
						log.info(e.getMessage());
				}
				
				if(userNameEntered.equals(customer.getLogin_user_name())) {
					
					
					
					log.info("Please enter your password");
					
					
					passwordEntered = sc.nextLine();
					try {
						customer = customerCRUDService.getCustomerByPasswordAfterByLoginUserName(customer, passwordEntered);
					} catch (BusinessException e) {
						log.info(e.getMessage());
					}
				
					
					if(passwordEntered.equals(customer.getLogin_password())) {
						spaceOutTheOldMessages();
						log.info("Login Successful");
						
						CustomerMainMenu(sc,customer);
						
					}else {
						log.info("The password you entered is incorrect. Please try again.");
					}
					
				}else {
					log.info("User name: "+userNameEntered+" is not found. Please try again");
				}
				
				
				break;
			case 2:
				log.info("Registering");
				break;
			case 3:
				log.info("Going back...");
				break;

			default: printOptionNotAvailable(); break;
			}
			
		} while (chclm!=3);
		
		
	}

	private static void CustomerMainMenu(Scanner sc, Customer customer) {
		log.info("Hello! "+customer.getFirst_name()+". How can I help you today");
		log.info("-----Main Menu----");
		
		int chcmm = 0;
		do {
			//log.info(customer.getBasic_checking_acc_id());
			log.info("Please choose an option below by entering the number associated with that option");
			log.info("1) Apply for a new bank account");
			log.info("2) View account balance");
			log.info("3) Deposit");
			log.info("4) Withdraw");
			log.info("5) Make a transfer");
			log.info("6) Check incoming transfer");
			log.info("7) Log out");
			try {
				chcmm= Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
			
			}
		
		switch (chcmm) {
		
		case 1:
			
			applyAccountMenu(sc,customer);
			
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:
			log.info("Thank your for using our service!");
			log.info("logging out...");
			customer = null;
			break;

		default: printOptionNotAvailable(); break;
		}
		
		} while (chcmm!=7);
	}
	
	static void applyAccountMenu(Scanner sc, Customer customer) {
		
		int chaam = 0;
		do {
			//Check if the customer already has those accounts
			log.info("Which account do you want to apply for?");
			
			if(customer.getBasic_checking_acc_id()==0) {
				log.info("1) Basic Checking");
			}else {
				log.info("You already have a Basic Checking account");
			}
			if(customer.getBasic_saving_acc_id()==0) {
				log.info("2) Basic Saving");
			}else {
				log.info("You already have a Basic Saving account");
			}
			if(customer.getPrem_checking_acc_id()==0) {
				log.info("3) Premium Checking");
			}else {
				log.info("You already have a Premium Checking account");
			}
			if(customer.getPrem_saving_acc_id()==0) {
				log.info("4) Premium Saving");
			}else {
				log.info("You already have a Premium Saving account");
			}
		
			log.info("5) Go back to Main Menu");
			
			chaam = Integer.parseInt(sc.nextLine());
			
			switch (chaam) {
			case 1:
				
				//to be replaced with AccountTypeDAO#####################
				AccountType atbc = new AccountType();
				atbc.setType("basic_checking");
				atbc.setMin_balance_req(1500d);
				atbc.setAnnual_service_fee(0);
				atbc.setCredit_score_req(0);
				atbc.setMonthly_fee(15);
				atbc.setOverdrawn_amount(500);
				
				//----------------------------------
				
				openNewAccountWithInitialBalance(customer, sc, atbc);
				
				break;
				
			case 2:
			
				//to be replaced with AccountTypeDAO#####################
				AccountType atbs = new AccountType();
				atbs.setType("basic_saving");
				atbs.setMin_balance_req(1500d);
				atbs.setAnnual_service_fee(0);
				atbs.setCredit_score_req(0);
				atbs.setMonthly_fee(15);
				atbs.setOverdrawn_amount(500);
				
				//----------------------------------
				
				openNewAccountWithInitialBalance(customer, sc, atbs);
				
				break;
				
			case 3:
				
				//to be replaced with AccountTypeDAO#####################
				AccountType atpc = new AccountType();
				atpc.setType("prem_checking");
				atpc.setMin_balance_req(1500d);
				atpc.setAnnual_service_fee(0);
				atpc.setCredit_score_req(0);
				atpc.setMonthly_fee(15);
				atpc.setOverdrawn_amount(500);
				
				//----------------------------------
				
				openNewAccountWithInitialBalance(customer, sc, atpc);
				
				break;
				
			case 4:
				
				//to be replaced with AccountTypeDAO#####################
				AccountType atps = new AccountType();
				atps.setType("prem_saving");
				atps.setMin_balance_req(1500d);
				atps.setAnnual_service_fee(0);
				atps.setCredit_score_req(0);
				atps.setMonthly_fee(15);
				atps.setOverdrawn_amount(500);
				
				//----------------------------------
				
				openNewAccountWithInitialBalance(customer, sc, atps);
				
				
				break;
				
			case 5:
				log.info("Going back to main menu");
				break;

			default: printOptionNotAvailable(); break;
			}
			
		} while (chaam!=5);
		
		
	}
	
	static void openNewAccountWithInitialBalance(Customer customer, Scanner sc, AccountType at) {
		
		
			
			String accType = at.getType();
			
			switch (accType) {
			case "basic_checking":
				if(customer.getBasic_checking_acc_id()==0) {
					log.info("Thank you for your interest of opening a Basic Checking account.");
				}else return;
				break;
			case "basic_saving":
				if(customer.getBasic_saving_acc_id()==0) {
					log.info("Thank you for your interest of opening a Basic Saving account.");
				}else return;
				break;
			case "prem_checking":
				if(customer.getPrem_checking_acc_id()==0) {
					log.info("Thank you for your interest of opening a Premium Checking account.");
				}else return;
				break;
			case "prem_saving":
				if(customer.getPrem_saving_acc_id()==0) {
					log.info("Thank you for your interest of opening a Premium Saving account.");
				}else return;
				break;

			default: 
				break;
			}
			
			
			log.info("Here is the basic infomation of a Basic Checking account:");
			log.info("");
			log.info(at.toString());
			

			String initialDepositStr = null;
			int ch = 0;
			do {
				log.info("");
				log.info("Do you wish to proceed the application with an initial deposit?");
				
				
				log.info("1) Yes");
				log.info("2) Give up the application and go back");
				
				try {
					ch = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					// continue to the default case
				}
				
				switch (ch) {
				case 1:
					
					Double initialDeposit = null;
					do {
						
						log.info("How much would you like to deposit?");
						log.info("Suggested amount: $"+at.getMin_balance_req());
						log.info("A initial deposit should be between $100 to $500,000.");
						log.info("Enter \"cancel\" if you wish to give up the application");
						
						try {
							initialDepositStr = sc.nextLine();
						} catch (NumberFormatException e) {
							log.info("Please only enter numbers");
						}
						
						if(initialDepositStr.equals("cancel")) {
							log.info("Going back to the previous menu");
							ch=2;
							break;
						}else if(initialDepositStr != null) {
								
							try {
								initialDeposit = Double.parseDouble(initialDepositStr);
							} catch (NumberFormatException e) {
								log.info("Amount entered is invalid, please try again");
							}
							
							if(initialDeposit != null) {
								log.info("You are going to deposit: $" + initialDeposit);
								//To be replaced by a AccountCRUDImpl ###############
								log.info("connecting to database");
								log.info("Thank you! Your application has been submitted! The application may take up to 7 days to proccess.");
								spaceOutTheOldMessages();
								ch=2;
								
								switch (accType) {
								case "basic_checking":
									customer.setBasic_checking_acc_id(1249876);
									break;
								case "basic_saving":
									customer.setBasic_saving_acc_id(12456231);
									break;
								case "prem_checking":
									customer.setPrem_checking_acc_id(12435622);;
									break;
								case "prem_saving":
									customer.setPrem_saving_acc_id(1238476);
									break;

								default: 
									break;
								}
							}
						}		
					} while (initialDeposit==null && initialDepositStr!="cancel");
					
					
					break;
				case 2:
					log.info("Going back to the previous menu");
					break;

				default: printOptionNotAvailable();	break;
				}
				
			} while (ch!=2);
			
	}
	
 	static void spaceOutTheOldMessages() {
		//couple of lines
		log.info("");
		log.info("");
		log.info("");
	}
	
	static void printOptionNotAvailable() {
		log.info("Option entered is not available...Please retry");
	}
}
