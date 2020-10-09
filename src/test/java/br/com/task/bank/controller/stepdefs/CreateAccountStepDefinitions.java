package br.com.task.bank.controller.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.task.bank.cucumber.TransactionControllerTestsCucumber;
import br.com.task.bank.model.Account;
import br.com.task.bank.model.User;
import io.cucumber.java8.En;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

@AutoConfigureMockMvc
@SpringBootTest
public class CreateAccountStepDefinitions extends TransactionControllerTestsCucumber implements En{

	private String name;
	private String cpf;
	private double balance;
	private String response;
	private int idAccount = 120;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper = new ObjectMapper();
	
	@Given("I want to create an account for the user {string}")
	public void getUserName(String name) {
		this.name = name;
	}
	
	@Given("With the cpf {string}")
	public void getUserCPF(String cpf) {
		this.cpf = cpf;
	}
	
	@Given("With the cpf")
	public void userCPFNull() {
		this.cpf = null;
	}
	
	@Given("With {double} in the balance")
	public void getAccountBalance(double balance) {
		this.balance =balance;
	}
	
	@When("I save the account")
	public void saveAccount() throws Exception {
		
		User u1 = new User(name, cpf);
		Account acc1 = new Account(120, u1, balance);	
		String jsonString = mapper.writeValueAsString(acc1);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/bank/api/v1/accounts")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(jsonString)
		        .accept(MediaType.APPLICATION_JSON))
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		response = result.getResponse().getContentAsString();
		
	}
	
	@Then("The system should return the account number with the message: {string}")
	public void validateSuccessMessage(String message) {
		assertEquals(idAccount+message, response);
	}
	
	@Then("The system should return the message: {string}")
	public void validateMessage(String message) {
		assertEquals(message, response);
	}
	

}
