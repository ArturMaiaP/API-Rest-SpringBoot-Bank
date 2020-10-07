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
Feature: Checking the account deposit transaction from our bank application
		
			
	@DepositSuccess
  Scenario Outline: Successful deposit
    Given I have the following <accountId>
    And I require a deposit of 500.0
    When I perform the deposit task
    Then I should see "Deposito realizado com sucesso!" message
    And My account balance should be 1500.0
   
   Examples:
     | accountId  | balance | 
     | 123456 	  |  1000   |
		 

