package br.com.task.bank.repository;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import br.com.task.bank.model.Account;

public class AccountsDAOFactory {
	private static AccountsDAO<Account> accounts = null;
	
	public static AccountsDAO<Account> getInstance() {
		 if (AccountsDAOFactory.accounts == null) {
	            AccountsDAOFactory.accounts = new AccountsDAOImpl();
	        }

	        return accounts;
	}
}
