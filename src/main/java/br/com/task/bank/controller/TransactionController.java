package br.com.task.bank.controller;


/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */
import br.com.task.bank.enumerations.TransactionMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.task.bank.service.TransactionService;

@RestController
@RequestMapping("bank/api/v1")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		// TODO Auto-generated constructor stub
		this.transactionService = transactionService;
		
	}
	
	/**
	 * Deposit money operation.
	 * 
	 * @param id - account unique number
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	@PatchMapping(value ="/deposits/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deposit(@PathVariable("id") int id, @PathVariable("amount") double amount){
		String body = this.transactionService.deposit(id,amount);
		
		if(body==null) {
			return new ResponseEntity<String>(TransactionMessage.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}
	}
	
	
	/**
	 * Withdraw money operation.
	 * 
	 * @param id - account unique number
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	@PatchMapping(value = "/withdraws/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> withdraw(@PathVariable("id") int id, @PathVariable("amount") double amount){
		String body = this.transactionService.withdraw(id, amount);
		
		if(body==null) {
			return new ResponseEntity<String>(TransactionMessage.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}
		
	}
	
	/**
	 * Transfer money operation
	 * 
	 * @param idRequest 
	 * @param idDescription
	 * @param amount - value to be deposited in the specified account 
	 * @return String informing the operating result;
	 */
	
	@PatchMapping(value = "/transfers/{idRequest}/{idDestination}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> transfer(@PathVariable("idRequest") int idRequest,
			@PathVariable("idDestination") int idDestination, @PathVariable("amount") double amount){
		String body = this.transactionService.transfer(idRequest, idDestination, amount);

		if(body==null) {
			return new ResponseEntity<String>(TransactionMessage.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}
	}
}
