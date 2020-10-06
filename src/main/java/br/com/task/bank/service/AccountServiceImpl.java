package br.com.task.bank.service;


/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */


import java.util.List;

import org.springframework.stereotype.Service;

import br.com.task.bank.enumerations.AccountMessages;
import br.com.task.bank.model.Account;
import br.com.task.bank.repository.AccountsDAO;
import br.com.task.bank.repository.AccountsDAOFactory;



@Service
public class AccountServiceImpl implements AccountService {
	
	
	private AccountsDAO<Account> accountsDAO =  AccountsDAOFactory.getInstance();

	@Override
	public  String save(Account acc) {
		
		if(acc.getBalance()<50) {
			return  AccountMessages.INSUFFICIENT_INITIAL_BALANCE.getMessage();
		}
		else if(acc.getUser().getCpf() == null) {
			return AccountMessages.CPF_MISSING.getMessage();
		}
		else if(acc.getUser().getCpf().length() != 11) {
			return AccountMessages.CPF_INVALID.getMessage();
		}else {
			accountsDAO.save(acc);
			return acc.getId()+AccountMessages.SUCCESS.getMessage();
		}
	}

	@Override
	public List<Account> findAll() {
		return accountsDAO.getAll();
	}

	public boolean isAccount(int id) {
		if(accountsDAO.get(id).isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
}
