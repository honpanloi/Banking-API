package com.app.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.model.Customer;

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
			log.info("Please choose an option below by entering the number of that option");
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

			default:log.info("Option entered not available...Please retry.");break;
			}
		} while (ch!=3);
		
		

	}
	
	private static void showCustomerLoginMenu(Scanner sc, Customer customer) {
		
		int chclm = 0;
		do {

			//options
			log.info("Welcome! Customer");
			log.info("Please choose an option below by entering the number of that option");
			log.info("1) Login");
			log.info("2) Register");
			log.info("3) Go back");
			
			try {
				chclm= Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				
			}
			
			switch (chclm) {
			
			case 1:
				
				log.info("Please enter your user name");
				
				String userName = "";
				String password = "";
				
				userName = sc.nextLine();
				
				//to be replaced by getUserNameDAOImpl();####################
				if(userName.equals("username")) {
					
					
					log.info("Please enter your password");
					
					
					password = sc.nextLine();
					
					if(password.equals("password")) {
						spaceOutTheOldMessages();
						log.info("Login Successful");
						
						//to be replaced by get customerById###################
						customer = new Customer();
						
						CustomerMainMenu(sc,customer);
					}else {
						log.info("The password you entered is incorrect. Please try again.");
					}
					
				}else {
					log.info("User name: "+userName+" is not found. Please try again");
				}
				
				
				break;
			case 2:
				log.info("Registering");
				break;
			case 3:
				log.info("Going back...");
				break;

			default:log.info("Option entered not available...Please retry");break;
			}
			
		} while (chclm!=3);
		
		
	}

	private static void CustomerMainMenu(Scanner sc, Customer customer) {
		
		//Hello! customer.name how can I help you today?#######
		log.info("-----Main Menu----");
		
		int chcmm = 0;
		do {
			
			log.info("Please choose an option below by entering the number of that option");
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

			break;

		default:log.info("Option entered not available...Please retry");break;
		}
		
		} while (chcmm!=7);
	}
	
	static void applyAccountMenu(Scanner sc, Customer customer) {
		
		int chaam = 0;
		do {
			log.info("Which ");
			
			
		} while (chaam!=5);
		
		
	}
	
	
	static void spaceOutTheOldMessages() {
		//couple of lines
		log.info("");
		log.info("");
		log.info("");
	}
}
