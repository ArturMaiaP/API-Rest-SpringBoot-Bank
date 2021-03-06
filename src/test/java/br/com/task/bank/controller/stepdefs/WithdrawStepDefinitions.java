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
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.task.bank.cucumber.TransactionControllerTestsCucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;

@AutoConfigureMockMvc
@SpringBootTest
public class WithdrawStepDefinitions  extends TransactionControllerTestsCucumber implements En {

	
	private int id;
	private double amount;
	private String response;
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Given("I want to withdraw {double} from the following {int}")
	public void withdrawDestination(double amount, int accountId) {
		this.amount = amount;
		this.id = accountId;
	}

	
	@When("I perform the withdraw task")
	public void withdrawTask() throws Exception {
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/withdraws/"+id+"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		response = result.getResponse().getContentAsString();
	}
	
	@Then("The withdraw operation should return the message: {string}")
	public void validateMessage(String message) {
		assertEquals(message,response);
	}
	
	@Then("My account balance after the withdraw should be {double}")
	public void validateBalance(double balance) throws Exception {
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/bank/api/v1/accounts/"+id)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		
		String json = result.getResponse().getContentAsString();
		ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		assertEquals(balance, node.get("balance").asDouble());
	}
}
