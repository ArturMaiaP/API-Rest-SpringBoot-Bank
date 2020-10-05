package br.com.task.bank.repository;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.task.bank.model.Account;
import br.com.task.bank.model.User;

public class AccountsDAOImpl implements AccountsDAO<Account> {
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public AccountsDAOImpl() {
		// TODO Auto-generated constructor stub
		User u1 = new User("Artur", "09909909900");
		Account acc1 = new Account(10, u1, 60);
		accounts.add(acc1);
		
		Account acc2 = new Account(30, u1, 200);
		accounts.add(acc2);
	}

	@Override
	public Optional<Account> get(int id) {
		
		for(Account acc: accounts){
			if(acc.getId()==id) {
				return Optional.ofNullable(this.accounts.get(accounts.indexOf(acc)));
			}
		}
		return Optional.ofNullable(null);
	}

	@Override
	public List<Account> getAll() {
		return this.accounts;
	}

	@Override
	public void save(Account acc) {
		this.accounts.add(acc);
	}

	@Override
	public void update(Account acc) {
		int index = this.accounts.indexOf(acc);
		this.accounts.set(index, acc);
		
	}

	@Override
	public void delete(Account acc) {
		this.accounts.remove(acc);
	}
	
}
