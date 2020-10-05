package br.com.task.bank.service;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.task.bank.model.Account;

public interface AccountService {
	
	 public List<Account> findAll();
	 public String save(Account acc);
	 public boolean isAccount(int id);
	 
}
