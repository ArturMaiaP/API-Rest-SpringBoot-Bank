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
import br.com.task.bank.enumerations.TransactionMessage;
import br.com.task.bank.repository.AccountsDAOFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;


@AutoConfigureMockMvc
@SpringBootTest
public class TransferStepDefinitions extends TransactionControllerTestsCucumber implements En {
	
	private int idRequest;
	private int idDestination;
	private double amount;
	private String response;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Given("I want to transfer {double} from {int} to {int}")
	public void transferDestination(double amount, int idRequest, int idDestination) {
		this.amount = amount;
		this.idRequest = idRequest;
		this.idDestination = idDestination;
	}
	
	@When("I perform the transfer task")
	public void transferTask() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/transfers/"+idRequest+
				"/"+ idDestination +"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		response = result.getResponse().getContentAsString();
		
	}
	
	@Then("The transfer operation should return the message: {string}")
	public void validateMessage(String message) {
		assertEquals(message,response);
	}
	
	@Then("My account balance after the transfer should be {double}")
	public void validateBalanceRequest(double balance) throws Exception {
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/bank/api/v1/accounts/"+idRequest)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		
		String json = result.getResponse().getContentAsString();
		ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		assertEquals(balance, node.get("balance").asDouble());
	}
	
	@Then("The destination account balance shoulde be {double}")
	public void validateBalanceDestination(double balance) throws Exception {
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/bank/api/v1/accounts/"+idDestination)
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
