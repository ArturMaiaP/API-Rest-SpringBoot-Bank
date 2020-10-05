package br.com.task.bank.service;


/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */


import java.util.List;

import org.springframework.stereotype.Service;

import br.com.task.bank.enumerations.CreateAccountMessage;
import br.com.task.bank.model.Account;
import br.com.task.bank.repository.AccountsDAO;
import br.com.task.bank.repository.AccountsDAOFactory;



@Service
public class AccountServiceImpl implements AccountService {
	
	
	private AccountsDAO<Account> accountsDAO =  AccountsDAOFactory.getInstance();

	@Override
	public  String save(Account acc) {
		
		if(acc.getBalance()<50) {
			return  CreateAccountMessage.INSUFFICIENT_BALANCE.getMessage();
		}
		else if(acc.getUser().getCpf() == null) {
			return CreateAccountMessage.CPF_MISSING.getMessage();
		}
		else if(acc.getUser().getCpf().length() != 11) {
			return CreateAccountMessage.CPF_INVALID.getMessage();
		}else {
			accountsDAO.save(acc);
			return acc.getId()+CreateAccountMessage.SUCCESS.getMessage();
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
