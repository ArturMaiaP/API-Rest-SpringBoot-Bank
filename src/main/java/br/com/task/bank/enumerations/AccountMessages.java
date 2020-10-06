package br.com.task.bank.enumerations;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

public enum AccountMessages {

	INSUFFICIENT_BALANCE("Saldo insuficiente para abertura de uma nova conta."),
	CPF_MISSING("É necessário informar um cpf para abertura de uma nova conta."),
	CPF_INVALID("CPF informado para criação de conta está inválido."),
	SUCCESS(" Conta cadastrada com sucesso!");
	
	private String message;
	
	AccountMessages(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
