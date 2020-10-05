package br.com.task.bank.model;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

public class User {
	
	private String name;
	private String cpf;
	
	public User(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (cpf == null) {
			if (other.cpf != null) {
				return false;
			}
		} else if (!cpf.equals(other.cpf)) {
			return false;
		}
		return true;
	}
	
	
}
