package myFirstBankApp;

import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.service.AccountCrudService;
import com.app.service.impl.AccountCrudServiceImpl;

public class QuickTest {
	
	public static void main(String[] args) {
		
		
		AccountCrudService accountCrudService = new AccountCrudServiceImpl();
		Account account = new Account();
		try {
			account = accountCrudService.getAccountByAccountNum(200002l);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(account.toString());
		
	}

}
