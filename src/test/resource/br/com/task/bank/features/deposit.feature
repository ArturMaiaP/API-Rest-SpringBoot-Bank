#Author: artur.maiap@gmail.com
#Keywords Summary : Transaction API REST
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@Deposit
Feature: Account deposit transaction
		
			
	@DepositSuccess
  Scenario Outline: Successful deposit
    Given I want to deposit 500.0 in the following <accountId>
    When I perform the deposit task
    Then The deposit operation should return the message: "Deposito realizado com sucesso!"
    And My account balance after the deposit should be 1500.0
   
   Examples:
     | accountId  | balance | 
     | 123456 	  |  1000   |
		 

