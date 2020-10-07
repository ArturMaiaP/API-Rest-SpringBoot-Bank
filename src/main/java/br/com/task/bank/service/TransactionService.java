package br.com.task.bank.service;


/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

public interface TransactionService {
	
	/**
	 * Deposit money operation
	 * 
	 * @param id - account unique number
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	public String deposit(int id, double amount);
	
	/**
	 * Withdraw money operation
	 * 
	 * @param id - account unique number
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	public String withdraw(int id, double amount);
	
	/**
	 * Transfer money operation
	 * 
	 * @param idRequest 
	 * @param idDescription
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	public String transfer(int idRequest, int idDestination, double amount);

}
