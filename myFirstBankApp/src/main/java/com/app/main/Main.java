package com.app.main;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.AccountType;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.service.AccountCrudService;
import com.app.service.AccountTypeReadService;
import com.app.service.CustomerCrudService;
import com.app.service.TransactionCrudService;
import com.app.service.impl.AccountCrudServiceImpl;
import com.app.service.impl.AccountTypeReadServiceImpl;
import com.app.service.impl.CustomerCrudServiceImpl;
import com.app.service.impl.TransactionCrudServiceImpl;
import com.app.util.Validation;

import java.nio.file.DirectoryIteratorException;
import java.sql.Date;
import java.text.DecimalFormat;

public class Main {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Customer customer = null;
			
		
		int ch = 0;
		do {
			//print options
			log.info("MyBankApp V1.00");
			log.info("");
			log.info("Please choose an option below by entering the number associated with that option");
			log.info("1) I am a customer");
			log.info("2) I am an employee");
			log.info("3) Exit ppplication");
						
			ch = acquireUserIntInput(sc, ch);
			
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

	private static long acquireUserLongInput(Scanner sc) {
		long ch = 0;
		try {
			ch= Long.parseLong(sc.nextLine());
		}catch(NumberFormatException e) {
			
		}
		return ch;
	}
	
	private static int acquireUserIntInput(Scanner sc, int ch) {
		try {
			ch= Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			
		}
		return ch;
	}
	
	private static String acquireUserStringInput(Scanner sc, String ch) {
		try {
			ch= sc.nextLine();
		}catch(NumberFormatException e) {
			
		}
		return ch;
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
			
			chclm = acquireUserIntInput(sc, chclm);
			
			switch (chclm) {
			
			case 1:		
				loginCustomer(sc);
				break;
			case 2:
				customer = new Customer();
				customerRegistrationByCustomer(customer,sc);
				break;
			case 3:
				log.info("Going back...");
				break;

			default: printOptionNotAvailable(); break;
			}
			
		} while (chclm!=3);
		
		
	}

	private static void loginCustomer(Scanner sc) {
		Customer customer;
		CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();
		
		log.info("Please enter your user name");
		
		String userNameEntered = "";
		String passwordEntered = "";
		
		customer = new Customer();
		
		try {
			userNameEntered = sc.nextLine();
		} catch (Exception e1) {
			log.info("Please enter your user name to login.");
		}
		try {
			customer = customerCRUDService.getCustomerByLoginUserName(userNameEntered);
		} catch (BusinessException e) {
				log.info(e.getMessage());
				customer = new Customer();
		}
		
		if(customer.getLogin_user_name()!=null) {
			
			
			
			log.info("Please enter your password");
			
			
			passwordEntered = sc.nextLine();
			try {
				customer = customerCRUDService.getCustomerByIdAndPassword(customer.getId(), passwordEntered);
			} catch (BusinessException e) {
				log.info(e.getMessage());
				customer = new Customer();
			}
		
			
			if(customer.getFirst_name()!=null) {
				spaceOutTheOldMessages();
				log.info("Login Successful");
				
				CustomerMainMenu(sc,customer,customerCRUDService);
				
			}else {
				log.info("The password you entered is incorrect. Please try again.");
			}
			
		}else {
			if(userNameEntered!=null && Validation.isValidUserName(userNameEntered)) {
				log.info("User name: "+userNameEntered+" is not found. Please try again");
			}
			
		}
	}

	private static void CustomerMainMenu(Scanner sc, Customer customer, CustomerCrudService customercrudServiceImpl) {
		log.info("Hello! "+customer.getFirst_name()+". How can I help you today");
		
		int chcmm = 0;
		do {
			//log.info(customer.getBasic_checking_acc_id());
			log.info("-----Main Menu----");
			log.info("Please choose an option below by entering the number associated with that option");
			log.info("1) Apply for a new bank account");
			log.info("2) View account balance");
			log.info("3) Deposit");
			log.info("4) Withdraw");
			log.info("5) Make a transfer");
			log.info("6) Check incoming transfer");
			log.info("7) Log out");
			
			chcmm = acquireUserIntInput(sc, chcmm);
		
			switch (chcmm) {
		
			case 1:		applyAccountMenu(sc,customer,customercrudServiceImpl);
				break;
			case 2:		viewAccountsBelongToTheCustomer(customer);
				break;
			case 3:		atmDepositMenu(sc, customer);
				break;
			case 4:		atmWithdrawMenu(sc, customer);
				break;
			case 5:		makeATransfer(sc, customer);
				
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

	private static void atmDepositMenu(Scanner sc, Customer customer) {
		TransactionCrudService transactionCrudService = new TransactionCrudServiceImpl();
		List<Account> accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
		String ch = "";
		
		if(accountsBelongToCustomer!=null && accountsBelongToCustomer.size()>0) {
			do {
				accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
				log.info("Which account do you want to deposit to?");
				
				accountsBelongToCustomer = sortAccountsByType(accountsBelongToCustomer);
				printTheAccounts(accountsBelongToCustomer);
				log.info("Enter \"cancel\" to go to main menu");
				

				try {
					ch = sc.nextLine();
				} catch (Exception e2) {
					log.info("Invalid input. Please try again.");

				} 
				
				long targetAccountNumber = 0;
				targetAccountNumber = acquireTargetAccountNumber(accountsBelongToCustomer, ch, targetAccountNumber);
				
				if(targetAccountNumber!=0) {
					AccountCrudService accountCrudService = new AccountCrudServiceImpl();
					boolean isAccountActive = false;
					try {
						isAccountActive = accountCrudService.checkIfanAccountIsActive(targetAccountNumber);
					} catch (BusinessException e1) {
						log.info(e1.getMessage());
					}
					
					if(ch!=null && !ch.equals("cancel") && !isAccountActive) {
						log.info("This account is not active. Please contact customer service or visit a local Mybank.");
						
					}
					
					if(isAccountActive && targetAccountNumber != 0) {
						double depositAmount = 0;
						boolean isValidInput = false;
						try {
							log.info("How much do you want to deposite?");
							depositAmount = Double.parseDouble(sc.nextLine());
							isValidInput = true;
						} catch (NumberFormatException e) {
							log.info("Invalid input");
						}
						if(isValidInput) {
							try {
								transactionCrudService.createDepositOnlyTransaction(targetAccountNumber, depositAmount);
							} catch (BusinessException e) {
								log.info(e.getMessage());
							}
						}
						
					}
				
				}
			} while (!ch.equals("cancel"));
			log.info("Going back to main menu.");
			spaceOutTheOldMessages();
		}else {
			log.info("You don't have an account yet. You can apply for one using this app. Thank you!");
		}
	}

	private static void atmWithdrawMenu(Scanner sc, Customer customer) {
		TransactionCrudService transactionCrudService = new TransactionCrudServiceImpl();
		List<Account> accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
		String ch = "";
		
		if(accountsBelongToCustomer!=null && accountsBelongToCustomer.size()>0) {
			do {
				accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
				log.info("Which account do you want to withdraw from?");
				
				accountsBelongToCustomer = sortAccountsByType(accountsBelongToCustomer);
				printTheAccounts(accountsBelongToCustomer);
				log.info("Enter \"cancel\" to go to main menu");
				

				ch = acquireUserStringInput(sc, ch);
				
				long targetAccountNumber = 0;
				targetAccountNumber = acquireTargetAccountNumber(accountsBelongToCustomer, ch, targetAccountNumber);
				
				if(targetAccountNumber!=0) {
					AccountCrudService accountCrudService = new AccountCrudServiceImpl();
					boolean isAccountActive = false;
					try {
						isAccountActive = accountCrudService.checkIfanAccountIsActive(targetAccountNumber);
					} catch (BusinessException e1) {
						log.info(e1.getMessage());
					}
					
					if(ch!=null && !ch.equals("cancel") && !isAccountActive) {
						log.info("This account is not active. Please contact customer service or visit a local Mybank.");
					}
					
					if(isAccountActive && targetAccountNumber != 0) {
						double withdrawAmount = 0;
						boolean isValidInput = false;
						try {
							log.info("How much do you want to withdraw?");
							withdrawAmount = Double.parseDouble(sc.nextLine());
							isValidInput = true;
						} catch (NumberFormatException e) {
							log.info("Invalid input");
						}
						if(isValidInput) {
							
							try {
								transactionCrudService.createWithdrawOnlyTransaction(targetAccountNumber, withdrawAmount);
							} catch (BusinessException e) {
								log.info(e.getMessage());
							}
						}
						
					}
				
				}
			} while (!ch.equals("cancel"));
			log.info("Going back to main menu.");
			spaceOutTheOldMessages();
		}else {
			log.info("You don't have an account yet. You can apply for one using this app. Thank you!");
		}
	}

	private static void makeATransfer(Scanner sc, Customer customer) {
		TransactionCrudService transactionCrudService = new TransactionCrudServiceImpl();
		List<Account> accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
		String chFrom = "";
		
		if(accountsBelongToCustomer!=null && accountsBelongToCustomer.size()>0) {
			do {
				//printing the available options to transfer from
				accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
				log.info("Which account do you want to transfer from?");
				
				accountsBelongToCustomer = sortAccountsByType(accountsBelongToCustomer);
				printTheAccounts(accountsBelongToCustomer);
				log.info("Enter \"cancel\" to go to main menu");
				
				//acquire the user option and set the targetAccountNumberTransferFrom
				chFrom = acquireUserStringInput(sc, chFrom);
				long targetAccountNumberTransferFrom = 0;
				targetAccountNumberTransferFrom = acquireTargetAccountNumber(accountsBelongToCustomer, chFrom, targetAccountNumberTransferFrom);
				
				//check if the account selected is an active account based on the user input
				boolean isAccountActive = false;
				if(targetAccountNumberTransferFrom!=0) {
					AccountCrudService accountCrudService = new AccountCrudServiceImpl();
					
					try {
						isAccountActive = accountCrudService.checkIfanAccountIsActive(targetAccountNumberTransferFrom);
					} catch (BusinessException e1) {
						log.info(e1.getMessage());
					}
					
					if(chFrom!=null && !chFrom.equals("cancel") && !isAccountActive) {
						log.info("This account is not active. Please contact customer service or visit a local Mybank.");
						spaceOutTheOldMessages();
					}
				}
				
				//exit the method when the account select is inactive
				if(!isAccountActive) return;

				//ask for a transfer amount and check if it's valid
				double transferAmount = 0;
				boolean isValidTranactionAmount = false;
				boolean isfundSufficient = false;
				try {
					spaceOutTheOldMessages();
					log.info("How much do you want to transfer?");
					transferAmount = Double.parseDouble(sc.nextLine());
				} catch (NumberFormatException e) {
						log.info("Invalid input");
				}
				isValidTranactionAmount = Validation.isValidTransactionAmount(transferAmount);
				if(!isValidTranactionAmount) {
					log.info("You can only trasnfer up to $20,000 per transaction, and the minimun trasnfer amount is $0.01.");
					return;
				}
				AccountCrudService accountCrudService = new AccountCrudServiceImpl();
				try {
					isfundSufficient = (accountCrudService.getAccountByAccountNum(targetAccountNumberTransferFrom).getCurrent_balance()>transferAmount);
				} catch (BusinessException e2) {
					log.info("Unable to verify the account balance. Please contact customer service.");
				}
				if(!isfundSufficient) {
					log.info("The account you selected does not have enough fund to make this transfer. Please try again later.");
					return;
				}
				
				//printing the available options to transfer from to transfer to
				for (Iterator<Account> iterator = accountsBelongToCustomer.listIterator(); iterator.hasNext();) {
					Account a = iterator.next();
					if(a.getNumber()==targetAccountNumberTransferFrom) {
						iterator.remove();
					}
				}
				//if(a.getNumber()==targetAccountNumberTransferFrom)
				
				log.info("Which account do you want to transfer to?");
				printTheAccounts(accountsBelongToCustomer);
				log.info("5) Enter an external account number");
				log.info("Enter \"cancel\" to go to main menu");
				
				//acquire the user option and set the targetAccountNumberTransferTo
				String chTo = ""; 
				chTo =	acquireUserStringInput(sc, chFrom);
				long targetAccountNumberTransferTo = 0;
				targetAccountNumberTransferTo = acquireTargetAccountNumberTransterTo(sc, accountsBelongToCustomer, chTo, targetAccountNumberTransferTo);
				
				//check if the the account selected is an active account based on the user input
				boolean is2ndAccountActive = false;
				if(targetAccountNumberTransferTo!=0) {
					
					try {
						is2ndAccountActive = accountCrudService.checkIfanAccountIsActive(targetAccountNumberTransferTo);
					} catch (BusinessException e1) {
						log.info(e1.getMessage());
					}
					
					if(chTo!=null && !chTo.equals("cancel") && !is2ndAccountActive) {
						log.info("This account is not active. Please choose another account.");
						spaceOutTheOldMessages();
					}
				}
				
				//exit the method when the account select is inactive
				if(!is2ndAccountActive) return;
				
				
				//Ask for final confirmation
				boolean isConfirmed = false;
				if(isValidTranactionAmount && targetAccountNumberTransferFrom!=0 && targetAccountNumberTransferTo!=0) {
					int chFinal = 0;
					do {
						log.info("Please confirm the following information: ");
						log.info("Transfer from : "+targetAccountNumberTransferFrom);
						log.info("Transfer to : "+targetAccountNumberTransferTo);
						log.info("Transfer amount : $"+transferAmount);
						log.info("1) Confirm");
						log.info("2) Abandon transfer");
						chFinal = acquireUserIntInput(sc, chFinal);
					} while (chFinal!=1 && chFinal!=2);
					
					if(chFinal==1) {
						isConfirmed = true;
					}

				}
				
				//exit the method if the user wants to
				if(!isConfirmed) {
					log.info("You abandoned the trasnfer request.");
					return;
				}
				
				//after confirmation check if both accounts belongs to the same person
				boolean isBothAccountBelongToUser = false;
				long ownerId1 = 1;
				long ownerId2 = 2;
				try {
					ownerId1 = accountCrudService.getAccountByAccountNum(targetAccountNumberTransferTo).getOwner_id();
				} catch (BusinessException e) {

				}
				try {
					ownerId2 = accountCrudService.getAccountByAccountNum(targetAccountNumberTransferFrom).getOwner_id();
				} catch (BusinessException e) {

				}
				
				if(ownerId1==ownerId2) isBothAccountBelongToUser = true;
				
				//if both account belongs to the same person, run the first service and finish the transaction
				if(isBothAccountBelongToUser) {
					try {
						transactionCrudService.createTransferTransactionWhenBothAccountsBelongToTheSamePerson(
								targetAccountNumberTransferTo, targetAccountNumberTransferFrom, transferAmount);
					} catch (BusinessException e) {
						log.info("Unable to complete the transfer. Please try again later.");
					}
				}else {
					//if the second account belongs to someone else, create the transaction only
					try {
						transactionCrudService.createTransferTransactionToAnotherPerson(targetAccountNumberTransferTo, targetAccountNumberTransferFrom, transferAmount);
					} catch (BusinessException e) {
						log.info("Unable to complete the transfer. Please try again later.");
					}
				}
				
				
				
				
			} while (!chFrom.equals("cancel"));
			log.info("Going back to main menu.");
			spaceOutTheOldMessages();
		}else {
			log.info("You don't have an account yet. You can apply for one using this app. Thank you!");
		}
	}

	private static void printTheAccounts(List<Account> accountsBelongToCustomer) {
		int index = 1;
		for (Account a: accountsBelongToCustomer) {
			
			log.info(index+")	"+a.getPrintedAccountType()+" ("+a.getPrintedAccountStatus() +
					")\n	Current balance: $"+df2.format(a.getCurrent_balance()));
			index++;
			
			
		}
		
	}

	private static long acquireTargetAccountNumberTransterTo(Scanner sc, List<Account> accountsBelongToCustomer, String ch,
			long targetAccountNumberTransferTo) {
		
		switch (ch) {
		case "1":
			if(accountsBelongToCustomer.size()>=1) {
				targetAccountNumberTransferTo = accountsBelongToCustomer.get(0).getNumber();
			}
			break;
		case "2":
			if(accountsBelongToCustomer.size()>=2) {
				targetAccountNumberTransferTo = accountsBelongToCustomer.get(1).getNumber();
			}
			break;
		case "3":
			if(accountsBelongToCustomer.size()>=3) {
				targetAccountNumberTransferTo = accountsBelongToCustomer.get(2).getNumber();
			}
			break;
		case "4":
			if(accountsBelongToCustomer.size()>=4) {
				targetAccountNumberTransferTo = accountsBelongToCustomer.get(3).getNumber();
			}
			break;
		case "5":
			//ask for the account number
			log.info("Please enter the target account number to transfer to:");
			long targetAccountNumberTransferToToBe = acquireUserLongInput(sc);
			//Verify the account status of the target account
			AccountCrudService service = new AccountCrudServiceImpl();
			boolean isAnActiveAccount= false;
			try {
				isAnActiveAccount = service.checkIfanAccountIsActive(targetAccountNumberTransferTo);
			} catch (BusinessException e) {
				log.info("Unable to find the account.");
				targetAccountNumberTransferTo = 0;
			}
			//set the targetAccountNumberTransferTo to the user input
			if (isAnActiveAccount) {
				targetAccountNumberTransferTo = targetAccountNumberTransferToToBe;
			}
			break;
		default: 
			if (!ch.equals("cancel")) {
				log.info("The selection was invalid. Please retry.");
				
			}
			break;
		}
		
		
		return targetAccountNumberTransferTo;
	}
	
	private static List<Account> sortAccountsByType(List<Account> accountsBelongToCustomer) {
		List<Account> sortedAccountsBelongToCustomerAccounts = new ArrayList<Account>();
		
		for (Account a: accountsBelongToCustomer) {
			if (a.getAccount_type().equals("basic_checking")) {
				sortedAccountsBelongToCustomerAccounts.add(a);
			}
		}
		
		for (Account a: accountsBelongToCustomer) {
			if (a.getAccount_type().equals("basic_saving")) {
				sortedAccountsBelongToCustomerAccounts.add(a);
			}
		}
		
		for (Account a: accountsBelongToCustomer) {
			if (a.getAccount_type().equals("prem_checking")) {
				sortedAccountsBelongToCustomerAccounts.add(a);
			}
		}
		
		for (Account a: accountsBelongToCustomer) {
			if (a.getAccount_type().equals("prem_saving")) {
				sortedAccountsBelongToCustomerAccounts.add(a);
			}
		}
		
		return sortedAccountsBelongToCustomerAccounts;
	}

	private static long acquireTargetAccountNumber(List<Account> accountsBelongToCustomer, String ch,
			long targetAccountNumber) {
		switch (ch) {
		case "1":
			if(accountsBelongToCustomer.size()>=1) {
				targetAccountNumber = accountsBelongToCustomer.get(0).getNumber();
			}
			break;
		case "2":
			if(accountsBelongToCustomer.size()>=2) {
				targetAccountNumber = accountsBelongToCustomer.get(1).getNumber();
			}
			break;
		case "3":
			if(accountsBelongToCustomer.size()>=3) {
				targetAccountNumber = accountsBelongToCustomer.get(2).getNumber();
			}
			break;
		case "4":
			if(accountsBelongToCustomer.size()>=4) {
				targetAccountNumber = accountsBelongToCustomer.get(3).getNumber();
			}
			break;
		default: 
			if (!ch.equals("cancel")) {
				log.info("The selection was invalid. Please retry.");
			}
			break;
		}
		return targetAccountNumber;
	}


	private static void viewAccountsBelongToTheCustomer(Customer customer) {
		
		List<Account> accountsBelongToCustomer = getTheListOfTheAccountOwnedByCustomer(customer);
		
		if(accountsBelongToCustomer.size()>0) {
			for (Account a: accountsBelongToCustomer) {
				log.info(a.getAccountBalance());
				log.info("Account status: "+a.getPrintedAccountStatus());
				log.info("");
			}
		}else {
			log.info("You don't have any account yet. You can apply for one using this app. Thank you!");
		}
	}

	private static List<Account> getTheListOfTheAccountOwnedByCustomer(Customer customer) {
		List<Account> accountsBelongToCustomer = new ArrayList<Account>();
		AccountCrudService accountCrudService = new AccountCrudServiceImpl();
		try {
			accountsBelongToCustomer = accountCrudService.getAccountsByCustomerId(customer.getId());
		} catch (BusinessException e) {
			log.info(e);
		}
		return accountsBelongToCustomer;
	}
	
	static void applyAccountMenu(Scanner sc, Customer customer, CustomerCrudService customercrudServiceImpl) {
		
		AccountTypeReadService accountTypeReadService = new AccountTypeReadServiceImpl();
		
		int chaam = 0;
		do {
			
			printApplicableAccounts(customer);
			
			chaam = acquireUserIntInput(sc, chaam);
			
			switch (chaam) {
			case 1:
				
				AccountType atbc = null;
				try {
					atbc = accountTypeReadService.readAccountTypeByType("basic_checking");
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				if(atbc!=null) {
					applyNewAccountWithInitialBalance(customer, sc, atbc, customercrudServiceImpl);
				}
				
				
				break;
				
			case 2:
			
				AccountType atbs = null;
				try {
					atbs = accountTypeReadService.readAccountTypeByType("basic_saving");
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				if(atbs!=null) {
					applyNewAccountWithInitialBalance(customer, sc, atbs, customercrudServiceImpl);
				}
				
				break;
				
			case 3:
				
				AccountType atpc = null;
				try {
					atpc = accountTypeReadService.readAccountTypeByType("prem_checking");
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				if(atpc!=null) {
					applyNewAccountWithInitialBalance(customer, sc, atpc, customercrudServiceImpl);
				}
				
				break;
				
			case 4:
				
				AccountType atps = null;
				try {
					atps = accountTypeReadService.readAccountTypeByType("prem_saving");
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				if(atps!=null) {
					applyNewAccountWithInitialBalance(customer, sc, atps, customercrudServiceImpl);
				}

				
				break;
				
			case 5:
				log.info("Going back to main menu");
				break;

			default: printOptionNotAvailable(); break;
			}
			
		} while (chaam!=5);
		
		
	}

	private static void printApplicableAccounts(Customer customer) {
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
	}
	
	static void applyNewAccountWithInitialBalance(Customer customer, Scanner sc, AccountType at, CustomerCrudService customercrudServiceImpl) {

		String accType = at.getType();
			
		String accTypeFullName = acquireFullNameOfTheSelectedAccount(at);
			
		boolean canBeApplied = false;
			
		canBeApplied = checkIfTheAccountCanBeApplied(customer, accType, accTypeFullName);
			
			
		if(!canBeApplied) {
			//if there is already an existing account exit the current method 
			return;
		}
			
		printTheInfoOfTheSelectedAccountType(at);
			

		String initialDepositStr = null;
		int ch = 0;
		do {
			log.info("");
			log.info("Do you wish to proceed the application with an initial deposit?");
			log.info("1) Yes");
			log.info("2) Give up the application and go back");
				
			ch = acquireUserIntInput(sc, ch);
				
			switch (ch) {
			case 1:
					
				Double initialDeposit = null;
				do {
						
					log.info("How much would you like to deposit?");
					log.info("Suggested amount: $"+1500);
					log.info("A initial deposit should be between $200 to $500,000.");
					log.info("Enter \"cancel\" if you wish to give up the application");
						
					try {
						initialDepositStr = sc.nextLine();
					} catch (NumberFormatException e) {
						log.info("Please only enter numbers");
						initialDepositStr = null;
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
							//Check with the customer if he/she wants to continue	
							boolean isConfirmedToApply = false;
							isConfirmedToApply = acquireFinalConfirmationForCreatingAcc(accTypeFullName, initialDeposit,isConfirmedToApply,sc);
								
								
								
							if (isConfirmedToApply) {
								Account account = new Account();
								account.setAccount_type(at.getType());
								AccountCrudService accountCrudService = new AccountCrudServiceImpl();
									
								int c = 0;
								try {
									c = accountCrudService.creatAccountByCustomer(customer, account,initialDeposit);
								} catch (BusinessException e) {
									log.info(e.getMessage());
								}
								if (c > 0) {
									log.info("Thank you! Your application has been submitted! The application may take up to 7 days to proccess.");
									spaceOutTheOldMessages();
									try {
										customer = customercrudServiceImpl.getCustomerById(customer.getId());
									} catch (BusinessException e) {
										log.info("Failed to update player.");
									}
									
								} else {
									
									

								}
								ch = 2;
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

	private static boolean acquireFinalConfirmationForCreatingAcc(String accTypeFullName, Double initialDeposit,
			boolean isConfirmedToApply, Scanner sc) {
		int chConfirmedToApply = 0;
		do {
			log.info("You are going to deposit: $" + initialDeposit+" to your "+accTypeFullName+".");
			log.info("Do you want to confirm your application?");
			log.info("1) Confirm application");
			log.info("2) Abandon application");
			
			chConfirmedToApply = Integer.parseInt(sc.nextLine());
			
			switch (chConfirmedToApply) {
			case 1:
				isConfirmedToApply = true;
				return isConfirmedToApply;
			case 2:
				log.info("You have abandoned the application.");
				isConfirmedToApply = false;
				break;
			default:
					break;
				}
				
			} while (chConfirmedToApply!=2);
		return isConfirmedToApply;
	}

	private static String acquireFullNameOfTheSelectedAccount(AccountType at) {
		String accTypeFullName = null;
		switch (at.getType()) {
		case "basic_checking":
			accTypeFullName = "Basic Checking account";
			break;
		case "basic_saving":
			accTypeFullName = "Basic Saving account";
			break;
		case "prem_checking":
			accTypeFullName = "Premium Checking account";
			break;
		case "prem_saving":
			accTypeFullName = "Premium Saving account";
			break;

		default:
			break;
		}
		return accTypeFullName;
	}

	private static void printTheInfoOfTheSelectedAccountType(AccountType at) {
		
		log.info("Here is the basic infomation of a "+at.getPrintedAccountType()+" account:");
		log.info("");
		log.info(at.toString());
	}

	private static boolean checkIfTheAccountCanBeApplied(Customer customer, String accType, String accTypeFullName) {
		boolean canBeApplied = false;
		
		switch (accType) {
		case "basic_checking":
			if(customer.getBasic_checking_acc_id()==0) {
				log.info("Thank you for your interest of opening a "+accTypeFullName);
				canBeApplied = true;
			}else {
				log.info("You already have a Basic Checking account or an ongoing application.");
			}
			break;
		case "basic_saving":
			if(customer.getBasic_saving_acc_id()==0) {
				log.info("Thank you for your interest of opening a "+accTypeFullName);
				canBeApplied = true;
			}else {
				log.info("You already have a Basic Saving account or an ongoing application.");
			}
			break;
		case "prem_checking":
			if(customer.getPrem_checking_acc_id()==0) {
				log.info("Thank you for your interest of opening a "+accTypeFullName);
				canBeApplied = true;
			}else {
				log.info("You already have a Premium Checking or an ongoing application.");
			}
			break;
		case "prem_saving":
			if(customer.getPrem_saving_acc_id()==0) {
				log.info("Thank you for your interest of opening a "+accTypeFullName);
				canBeApplied = true;
			}else {
				System.out.println("HI"+ customer.getPrem_saving_acc_id());
				log.info("You already have a Premium Saving or an ongoing application.");
			}
			break;

		default: 
			break;
		}
		return canBeApplied;
	}
	
	static void customerRegistrationByCustomer(Customer customer, Scanner sc) {
		
		CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();
		
		log.info("Thank you for your interest of becoming a Mybank customer.");

		int ch = 0;
		do {
			log.info("In order to complete the regisration, you will be required to enter the following infomation.");
			log.info("First Name, Last Name, Date of Birth, Social Security Number, ");
			log.info("Current Address, Phone Number, Email, Credit Score, Custom Login User and Password, etc");
			log.info("Do you wish to continue?");
			log.info("1) Yes");
			log.info("2) No");
			
			ch = acquireUserIntInput(sc, ch);
			switch (ch) {
			case 1:
				//currently working on...
				
				//finished
				acquireUserName(customer, sc, customerCRUDService);
				acquirePassword(customer, sc);
				acquireSsn(customer, sc, customerCRUDService, false);
				acquireFirstName(customer, sc, false);
				acquireLastName(customer, sc, false);
				acquireSalutation(customer, sc, false);
				acquireDob(customer, sc, false);
				acquireAddress(customer, sc, false);
				acquirePhoneNumber1(customer, sc, false);
				acquirePhoneNumber2(customer, sc, false);
				acquireEmail(customer, sc, false);
				acquireCreditScore(customer, sc, false);
				
				
				try {
					int c =	customerCRUDService.creatNewCustomerByCustomer(customer);
					if (c>0) {
						spaceOutTheOldMessages();
						log.info("Registration completed.");
						spaceOutTheOldMessages();
					}else {
						log.info("Registration was not completed.");
					}
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				
				ch = 2;

				break;
				
			case 2:
				log.info("Going back");
				break;


			default: printOptionNotAvailable();	break;
			}
		} while (ch!=2);
	}
	
	static void customerRegistrationByEmployee(Employee employee,Scanner sc) {
		
		Customer customer = new Customer();
		CustomerCrudService customerCRUDService = new CustomerCrudServiceImpl();

		int ch = 0;
		do {
			log.info("Make sure the customer filled out the essential infomation such as...");
			log.info("First Name, Last Name, Date of Birth, Social Security Number, ");
			log.info("Current Address, Phone Number, Email, Credit Score.");
			log.info("Continue?");
			log.info("1) Yes");
			log.info("2) No");
			
			ch = acquireUserIntInput(sc, ch);
			switch (ch) {
			case 1:
				
				acquireSsn(customer, sc, customerCRUDService, true);
				acquireFirstName(customer, sc, true);
				acquireLastName(customer, sc, true);
				acquireSalutation(customer, sc, true);
				acquireDob(customer, sc, true);
				acquireAddress(customer, sc, true);
				acquirePhoneNumber1(customer, sc, true);
				acquirePhoneNumber2(customer, sc, true);
				acquireEmail(customer, sc, true);
				acquireCreditScore(customer, sc, true);
				
				
				try {
					int c =	customerCRUDService.creatNewCustomerByEmployee(employee, customer);
					if (c>0) {
						spaceOutTheOldMessages();
						log.info("Registration completed.");
						spaceOutTheOldMessages();
					}else {
						log.info("Registration was not completed.");
					}
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				
				ch = 2;

				break;
				
			case 2:
				log.info("Going back");
				break;


			default: printOptionNotAvailable();	break;
			}
		} while (ch!=2);
	}

	private static void acquireDob(Customer customer, Scanner sc, boolean isEmployee) {
		String dobEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the date of birth using the following format.");
			}else {
				log.info("Please enter your date of birth using the following format.");
			}
			
			log.info("yyyy-mm-dd For example: 1950-01-25");
			
			try {
				dobEntered = sc.nextLine();
			} catch (Exception e) {
				
			}
			
			if(Validation.isValidDob(dobEntered)) {
				
				LocalDate dobEnteredLd = LocalDate.parse(dobEntered, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

				if(dobEnteredLd.compareTo(LocalDate.now().minusYears(12))<=0) {
					java.sql.Date dobsql = Date.valueOf(dobEnteredLd);
					
					log.info("Date of birth entered is accepted.");
					customer.setDob(dobsql);
				}else {
					log.info("I'm sorry. A Mybank customer has to be 12 years old or older to register an account.");
					log.info("Date of birth entered is unaccepted.");
					dobEntered = null;
				}
				
				
			}else {
				log.info("Date of birth entered is unaccepted.");
				dobEntered = null;
			}
			
		} while (dobEntered==null);
	}

	private static void acquireSsn(Customer customer, Scanner sc, CustomerCrudService customerCRUDService, boolean isEmployee) {
		String socialSecurityEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the socail security number using the following format.");
			}else {
				log.info("Please enter your socail security number using the following format.");
			}
			log.info("111-11-1111");
			
			try {
				socialSecurityEntered = sc.nextLine();
			} catch (Exception e) {
				
			}
			
			socialSecurityEntered = socialSecurityEntered.replaceAll("\\D", "");
			
			if(Validation.isValidSocialSecurityNumber(socialSecurityEntered)) {
				
				try {
					if(!customerCRUDService.isSsnTaken(Integer.parseInt(socialSecurityEntered)))
					log.info("Social security number entered is accepted.");
					
					customer.setSsn(Integer.parseInt(socialSecurityEntered));
				} catch (NumberFormatException e) {
					
				} catch (BusinessException e) {
					e.getMessage();
				}
				
				
			}else {
				log.info("Social security number entered is unaccepted.");
				socialSecurityEntered = null;
			}
			
		} while (socialSecurityEntered==null);
	}

	private static void acquireCreditScore(Customer customer, Scanner sc, boolean isEmployee) {
		String creditScoreEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the credit score.");
				log.info("Range of credit score is 300 - 850.");
				log.info("Enter 299 if the customer don't know his/her credit score.");
			}else {
				log.info("Please enter your credit score.");
				log.info("Range of credit score is 300 - 850.");
				log.info("You can enter 299 if you don't know your credit score.");
			}
			
			
			
			try {
				creditScoreEntered = sc.nextLine();
			} catch (Exception e) {
				
			}
			
			if(Validation.isValidCreditScore(Integer.parseInt(creditScoreEntered))) {
				
				log.info("Credit score entered is accepted.");
				customer.setCredit_score(Integer.parseInt(creditScoreEntered));
				
			}else {
				log.info("Credit score entered is unaccepted.");
				creditScoreEntered = null;
			}
			
		} while (creditScoreEntered==null);
	}

	private static void acquireEmail(Customer customer, Scanner sc, boolean isEmployee) {
		String emailEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the email using the following format.");
			}else {
				log.info("Please enter your email using the following format.");
			}
			log.info("111@111.com");
			
			try {
				emailEntered = sc.nextLine();
			} catch (Exception e) {
				
			}
			
			if(Validation.isValidEmail(emailEntered)) {
				
				
				log.info("Email entered is accepted.");
				customer.setEmail(emailEntered);
				
			}else {
				log.info("Email entered is unaccepted.");
				emailEntered = null;
			}
			
		} while (emailEntered==null);
	}

	private static void acquirePhoneNumber2(Customer customer, Scanner sc, boolean isEmployee) {
		String enteredPhone = null;
		if(isEmployee) {
			log.info("Is there a second phone number? If yes, enter it using the following format.");
			log.info("111-111-1111");
			log.info("If not, you can hit \"enter\" to skip");
		}else {
			log.info("Do you whis to enter a second phone number? If yes, enter it using the following format.");
			log.info("111-111-1111");
			log.info("If not, you can hit \"enter\" to skip");
		}
		
		try {
			enteredPhone = sc.nextLine();
		} catch (Exception e) {
				
		}
			
		if(Validation.isValidPhone(enteredPhone)) {
				
			enteredPhone = enteredPhone.replaceAll("\\D", "");
			log.info("Phone number is accepted.");
			customer.setPhone2(Long.parseLong(enteredPhone));
				
		}else {
			log.info("Phone number is unaccepted.");
			enteredPhone = null;
		}
		
	}

	private static void acquirePhoneNumber1(Customer customer, Scanner sc, boolean isEmployee) {
		String enteredPhone = null;
		do {
			if(isEmployee) {
				log.info("Please enter the primary contact phone number using the following format.");
				log.info("111-111-1111");
			}else {
				log.info("Please enter your primary contact phone number using the following format.");
				log.info("111-111-1111");
			}
			
			try {
				enteredPhone = sc.nextLine();
			} catch (Exception e) {
				
			}
			
			if(Validation.isValidPhone(enteredPhone)) {
				
				enteredPhone = enteredPhone.replaceAll("\\D", "");
				log.info("Phone number is accepted.");
				customer.setPhone1(Long.parseLong(enteredPhone));
				
			}else {
				log.info("Phone number is unaccepted.");
				enteredPhone = null;
			}
			
		} while (enteredPhone==null);
		
	}

	private static void acquireAddress(Customer customer, Scanner sc, boolean isEmployee) {
		String addressEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the address using the following format.");
			}else {
				log.info("Please enter your address using the following format.");
			}
			log.info("1111 East Street Name street, City, State, 5 didgit zip");
			
			try {
				addressEntered = sc.nextLine();
			} catch (NumberFormatException e) {
				
			}
			
			if(Validation.isValidAddress(addressEntered)) {
				
				log.info("Address entered is accepted.");
				customer.setAddress(addressEntered);;
				
			}else {
				log.info("Address entered is unaccepted.");
				addressEntered = null;
			}
			
		} while (addressEntered==null);
	}

	private static void acquireSalutation(Customer customer, Scanner sc, boolean isEmployee) {
		if(isEmployee) {
			log.info("Did the customer enter a salutation?");
		}else {
			log.info("Do you wish to enter your salutation?");
		}
		
		log.info("1) Mr.");
		log.info("2) Mrs.");
		log.info("3) Ms.");
		log.info("4) Dr.");
		log.info("Press \"enter\" to skip");
		
		int chsalu = 0;
		
		try {
			chsalu = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			log.info("You chose to skip.");
		}
		
		
		switch (chsalu) {
		case 1:
			customer.setSalutation("Mr.");
			log.info("You chose Mr. as your salutation");
			break;
		case 2:
			customer.setSalutation("Mrs.");
			log.info("You chose Mrs. as your salutation");
			break;
		case 3:
			customer.setSalutation("Ms.");
			log.info("You chose Ms. as your salutation");
			break;
		case 4:
			customer.setSalutation("Dr.");
			log.info("You chose Dr. as your salutation");
			break;

		default: 
			break;
		}
	}

	private static void acquireLastName(Customer customer, Scanner sc, boolean isEmployee) {
		String lastNameEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the customer's last name.");
			}else {
				log.info("Please enter your last name.");
			}
			
			log.info("It has to be at least 2 characters and at most 20.");
			log.info("It cannot contain special characters.");
			try {
				lastNameEntered = sc.nextLine();
			} catch (NumberFormatException e) {
				
			}
			
			if(Validation.isValidName(lastNameEntered)) {
				
				log.info("Name entered is accepted.");
				customer.setLast_name(lastNameEntered);
				
			}else {
				log.info("Name entered is unaccepted.");
				lastNameEntered = null;
			}
			
		} while (lastNameEntered==null);
	}

	private static void acquireFirstName(Customer customer, Scanner sc, boolean isEmployee) {
		String firstNameEntered = null;
		do {
			if(isEmployee) {
				log.info("Please enter the customer's first name.");
			}else {
				log.info("Please enter your first name.");
			}
			log.info("It has to be at least 2 characters and at most 20.");
			log.info("It cannot contain special characters.");
			try {
				firstNameEntered = sc.nextLine();
			} catch (NumberFormatException e) {
				
			}
			
			if(Validation.isValidName(firstNameEntered)) {
				
				log.info("Name entered is accepted.");
				customer.setFirst_name(firstNameEntered);
				
			}else {
				log.info("Name entered is unaccepted.");
				firstNameEntered = null;
			}
			
		} while (firstNameEntered==null);
	}

	private static void acquirePassword(Customer customer, Scanner sc) {
		String enteredPassword = null;
		do {
			log.info("Please create your user password. It has to be at least 8 characters and at most 20.");
			log.info("It also needs to contain at least 1 number, 1 upper case letter, and 1 lower case letter.");
			try {
				enteredPassword = sc.nextLine();
			} catch (NumberFormatException e) {
				
			}
			
			if(Validation.isValidPassword(enteredPassword)) {
				
				log.info("Password accepted.");
				customer.setLogin_password(enteredPassword);
				
			}else {
				log.info("Password entered is unaccepted.");
				enteredPassword = null;
			}
			
		} while (enteredPassword==null);
	}

	private static void acquireUserName(Customer customer, Scanner sc, CustomerCrudService customerCRUDService) {
		String enteredUserName = null;
		do {
			log.info("Please create your user name. It has to be at least 6 characters and at most 20.");
			log.info("User name cannot contain spaces or special characters. It's also not case sensitive.");
			
			try {
				enteredUserName = sc.nextLine();
			} catch (NumberFormatException e) {
				
			}
			
			
			if(Validation.isValidUserName(enteredUserName)) {
				enteredUserName.toLowerCase();
				
				try {
					if(!customerCRUDService.isLoginUserNameTaken(enteredUserName)) {
						customer.setLogin_user_name(enteredUserName);
						log.info("User name accepted");
					}
				} catch (BusinessException e) {
					log.info(e.getMessage());
					enteredUserName = null;
				}
				
			}else {
				log.info("User name entered is unaccepted.");
				enteredUserName = null;
			}
			
		} while (enteredUserName==null);
	}
	
 	public static void spaceOutTheOldMessages() {
		//couple of lines
		log.info("");
		log.info("");
		log.info("");
	}
	
	static void printOptionNotAvailable() {
		log.info("Option entered is not available.");
		log.info("Please enter a number to select an option to move forward.");
	}
	
}
