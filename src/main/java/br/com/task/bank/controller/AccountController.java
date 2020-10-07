package br.com.task.bank.controller;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.task.bank.model.Account;
import br.com.task.bank.service.AccountService;
import br.com.task.bank.service.AccountServiceImpl;

@RestController
@RequestMapping("bank/api/v1")
public class AccountController {
	
	private final AccountService accService;
	

	public AccountController(AccountServiceImpl accService) {
		this.accService = accService;
	}
	
	/**
	 *Get all accounts from the database
	 * 
	 * @return String informing the operating result
	 */
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> findAll(){
		List<Account> body = this.accService.findAll();
		ResponseEntity<List<Account>> response = new ResponseEntity<List<Account>>(body, HttpStatus.OK);
		return response;
	}
	
	/**
	 * Create an account
	 * @param acc - account to be registered
	 * @return String informing the operating result
	 */
	@PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAccount(@RequestBody Account acc) {
		String body = this.accService.save(acc);
		if(this.accService.isAccount(acc.getId())){
			return new ResponseEntity<String>(body,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<String>(body,HttpStatus.OK);
		}

	}

}
