package br.com.task.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;


import br.com.task.bank.enumerations.TransactionMessage;
import br.com.task.bank.repository.AccountsDAOFactory;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void caseDepositSuccess() throws Exception{
		int id = 10;
		double value = 200;
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/deposits/"+id+"/"+value)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, TransactionMessage.DEPOSIT_SUCCESS.getMessage());
		
		assertEquals(260, AccountsDAOFactory.getInstance().get(id).get().getBalance());
	}
	
	@Test
	void caseDepositAccountNotFound() throws Exception{
		int id = 12;
		double amount = 500;
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/deposits/"+id+"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isNotFound())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, TransactionMessage.ACCOUNT_NOT_FOUND.getMessage());
		
	}
	
	@Test
	void caseWithdrawOverLimit() throws Exception{
		int id = 10;
		double amount = 600;
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/withdraws/"+id+"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, TransactionMessage.WITHDRAW_OVER_LIMIT.getMessage());
		
	}
	@Test
	void caseWithdrawSuccess() throws Exception{
		int id = 30;
		double amount = 100;
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/withdraws/"+id+"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, TransactionMessage.WITHDRAW_SUCCESS.getMessage());
		
		assertEquals(100, AccountsDAOFactory.getInstance().get(id).get().getBalance());
		
		
	}
	
	@Test
	void caseWithdrawInsufficientBalance() throws Exception{
		int id = 30;
		double amount = 400;
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/bank/api/v1/withdraws/"+id+"/"+amount)
		        .contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, TransactionMessage.WITHDRAW_INSUFFICIENT_BALANCE.getMessage());
		
	}
}
