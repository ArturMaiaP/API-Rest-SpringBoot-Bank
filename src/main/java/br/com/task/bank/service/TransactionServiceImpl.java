package br.com.task.bank.service;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.task.bank.enumerations.TransactionMessage;
import br.com.task.bank.model.Account;
import br.com.task.bank.repository.AccountsDAO;
import br.com.task.bank.repository.AccountsDAOFactory;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final double WITHDRAW_LIMIT = 500;
	private final double TRANSFER_LIMIT = 500;
	private AccountsDAO<Account> accountsDAO =  AccountsDAOFactory.getInstance();
	

	@Override
	public String deposit(int id, double amount) {
		
		Optional<Account> accCheck = accountsDAO.get(id);
		
		if(accCheck.isPresent()) {
			accountsDAO.update(addAmount(accCheck.get(), amount));
			return TransactionMessage.DEPOSIT_SUCCESS.getMessage();
		}else {
			return null;
		}
	}

	@Override
	public String withdraw(int id, double amount) {
		// TODO Auto-generated method stub
		Optional<Account> accCheck = accountsDAO.get(id);
		
		if(amount > this.WITHDRAW_LIMIT) {
			return TransactionMessage.WITHDRAW_OVER_LIMIT.getMessage();
		}
		if(accCheck.isPresent()) {
			
			if(accCheck.get().getBalance() < amount) {
				return TransactionMessage.WITHDRAW_INSUFFICIENT_BALANCE.getMessage();
			}
			
			accountsDAO.update(subAmount(accCheck.get(), amount));
			return TransactionMessage.WITHDRAW_SUCCESS.getMessage();
			
		}else {
			return null;	
		}
	}

	@Override
	public String transfer(int idRequest, int idDestination, double amount) {
		// TODO Auto-generated method stub
		Optional<Account> accRequestCheck = accountsDAO.get(idRequest);
		Optional<Account> accDestinationCheck = accountsDAO.get(idDestination);
		
		if(amount > this.TRANSFER_LIMIT) {
			return TransactionMessage.TRANSFER_OVER_LIMIT.getMessage();
		}
		if(accRequestCheck.isPresent() && accDestinationCheck.isPresent()) {
			if(accRequestCheck.get().getBalance() < amount) {
				return TransactionMessage.TRANSFER_INSUFFICIENT_BALANCE.getMessage();
			}
			accountsDAO.update(subAmount(accRequestCheck.get(), amount));
			accountsDAO.update(addAmount(accDestinationCheck.get(), amount));
			return TransactionMessage.TRANSFER_SUCCESS.getMessage();
			
		}else {
			return null;
		}
	}
	
	private Account addAmount(Account acc, double amount) {
		double newBalance = acc.getBalance() + amount;
		acc.setBalance(newBalance);
		return acc;
	}
	
	private Account subAmount(Account acc, double amount) {
		double newBalance = acc.getBalance() - amount;
		acc.setBalance(newBalance);
		return acc;
	}
	

}
