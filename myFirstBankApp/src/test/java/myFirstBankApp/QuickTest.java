package myFirstBankApp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.Impl.TransactionCrudDaoImpl;
import com.app.exception.BusinessException;
import com.app.main.Main;
import com.app.model.Account;
import com.app.model.Transaction;
import com.app.service.AccountCrudService;
import com.app.service.TransactionCrudService;
import com.app.service.impl.AccountCrudServiceImpl;
import com.app.service.impl.TransactionCrudServiceImpl;

public class QuickTest {
	private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		
		
//		AccountCrudService accountCrudService = new AccountCrudServiceImpl();
//		Account account = new Account();
//		try {
//			account = accountCrudService.getAccountByAccountNum(200002l);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(account.toString());
//		
//		TransactionCrudService transactionCrudService = new TransactionCrudServiceImpl();
		
//		List<Transaction> listOfIncomingTransfersForAllAccount = new ArrayList<Transaction>();
//		List<Transaction> listOfIncomingTransfersFor1Account = new ArrayList<Transaction>();
//		try {
//			listOfIncomingTransfersFor1Account = transactionCrudService.searchForIncomingTransactions(200020);
//			for (Transaction t :listOfIncomingTransfersFor1Account) {
//				listOfIncomingTransfersForAllAccount.add(t);
//			}
//		} catch (BusinessException e) {
//			log.info("Unable to retrive transcations");
//		}
//		
//		for (Transaction t:listOfIncomingTransfersForAllAccount) {
//			System.out.println(t.getAmount());
//		}
		TransactionCrudDaoImpl impl = new TransactionCrudDaoImpl();
		Transaction transaction = null;
		try {
			transaction = impl.getTranscationById(10l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(transaction.toString());
		
	}

}
