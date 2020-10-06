package br.com.task.bank.enumerations;

public enum TransactionMessage {
	DEPOSIT_SUCCESS("Deposito realizado com sucesso!"),
	ACCOUNT_NOT_FOUND("Conta nao encontrada."),
	WITHDRAW_OVER_LIMIT("Operacao de transferencia tem um limite maximo de 500 por operacao."),
	WITHDRAW_SUCCESS("Saque realizado com sucesso!"),
	WITHDRAW_INSUFFICIENT_BALANCE("Limite indisponivel para saque.");
	
	private String message;

	TransactionMessage(String message ) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
