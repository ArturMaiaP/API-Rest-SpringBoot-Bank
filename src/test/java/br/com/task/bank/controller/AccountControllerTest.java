package br.com.task.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.task.bank.enumerations.AccountMessages;
import br.com.task.bank.model.Account;
import br.com.task.bank.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void caseInsufficientBalance() throws Exception{
		User u1 = new User("Maia", "09908808800");
		Account acc1 = new Account(20, u1, 30);	
		String jsonString = mapper.writeValueAsString(acc1);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/bank/api/v1/accounts")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(jsonString)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
		        
		String response = result.getResponse().getContentAsString();
		assertEquals(response, AccountMessages.INSUFFICIENT_INITIAL_BALANCE.getMessage());
	}
	
}
