package br.com.task.bank.controller;

import br.com.task.bank.enumerations.TransactionMessage;
import br.com.task.bank.error.AccountNotFoundException;
import br.com.task.bank.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.task.bank.service.AccountService;
import br.com.task.bank.service.AccountServiceImpl;
import br.com.task.bank.service.TransactionService;

@RestController
@RequestMapping("bank/api/v1")
public class TransactionController {
	
	private final TransactionService transactionService;
	

	public TransactionController(TransactionService transactionService) {
		// TODO Auto-generated constructor stub
		this.transactionService = transactionService;
		
	}
	@PatchMapping(value ="deposits/{id}/{value}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deposit(@PathVariable("id") int id, @PathVariable("value") double value){
		String body = transactionService.updateBalance(id,value);
		if(body==null) {
			return new ResponseEntity<String>(TransactionMessage.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}
	}

}
