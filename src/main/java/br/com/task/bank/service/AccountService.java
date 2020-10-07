package br.com.task.bank.service;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import java.util.List;

import br.com.task.bank.model.Account;

public interface AccountService {
	
	/**
	 * Get all accounts. 
	 * 
	 * @return List with all accounts registered.
	 */ 	
	public List<Account> findAll();
	
	/**
	 * Save an account in the database
	 * 
	 * @param acc - Account to be saved in the database
	 * @return  String informing the operating result
	 */
	public String save(Account acc);
	
	/**
	 * Verify if a specific account exists
	 * 
	 * @param id - account number
	 * @return  true if the account exists false otherwise
	 */
	public boolean isAccount(int id);

	/**
	 *Get the specified account by id
	 * @param id - account id
	 * @return Account
	 */
	public Account findById(int id);
	 
}
