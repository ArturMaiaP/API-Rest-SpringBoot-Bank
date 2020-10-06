package br.com.task.bank.service;

import br.com.task.bank.model.Account;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

public interface TransactionService {
	public String deposit(int id, double amount);
	public String withdraw(int id, double amount);
	public String transfer(int idRequest, int idDestination, double amount);

}
