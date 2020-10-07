package br.com.task.bank.cucumber;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@AutoConfigureMockMvc
@SpringBootTest
@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions( features = {"src/test/resources/br/com/bank/features"},
				glue = {"src/test/java/br/com/task/bank/controller/stepdefs"})
public class TransactionControllerTestsCucumber {
	
}
