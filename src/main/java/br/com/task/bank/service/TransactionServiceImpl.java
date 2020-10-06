package br.com.task.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.task.bank.enumerations.TransactionMessage;
import br.com.task.bank.error.AccountNotFoundException;
import br.com.task.bank.model.Account;
import br.com.task.bank.repository.AccountsDAO;
import br.com.task.bank.repository.AccountsDAOFactory;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

@Service
public class TransactionServiceImpl implements TransactionService {

	private AccountsDAO<Account> accountsDAO =  AccountsDAOFactory.getInstance();
	
	@Override
	public String updateBalance(int id, double value) {
		
		Optional<Account> acc = accountsDAO.get(id);
		
		if(acc.isPresent()) {
			Account newAcc = acc.get();
			double newBalance = newAcc.getBalance() + value;
			newAcc.setBalance(newBalance);
			accountsDAO.update(newAcc);
			return TransactionMessage.DEPOSIT_SUCCESS.getMessage();
		}else {
			return null;
		}
	}
	

}
