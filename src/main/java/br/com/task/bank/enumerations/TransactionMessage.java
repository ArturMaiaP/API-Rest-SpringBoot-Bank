package br.com.task.bank.enumerations;

public enum TransactionMessage {
	DEPOSIT_SUCCESS("Deposito realizado com sucesso!"),
	ACCOUNT_NOT_FOUND("Conta nao encontrada.");
	private String message;

	TransactionMessage(String message ) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
