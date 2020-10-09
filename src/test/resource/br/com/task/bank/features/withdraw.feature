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

@Withdraw
Feature: Account withdraw transaction


  @WithdrawSuccess
  Scenario Outline: Successful withdraw
    Given I want to withdraw 499.0 from the following <accountId>
    When I perform the withdraw task
    Then The withdraw operation should return the message: "Saque realizado com sucesso!"
    And My account balance after the withdraw should be 501.0
   
   Examples:
     | accountId  | balance | 
     | 123456 	  |  1000   |
     
  @WithdrawOverLimit
  Scenario Outline: Withdraw maximum limit exceeded
  	Given I want to withdraw 501.0 from the following <accountId>
    When I perform the withdraw task
    Then The withdraw operation should return the message: "Operacao de transferencia tem um limite maximo de 500 por operacao."
    And My account balance after the withdraw should be <balance>
   
   Examples:
     | accountId  | balance | 
     |    101 	  |  2000   |

  @WithdrawInsufficientBalance
  Scenario Outline: Account does not have funds for the withdraw
  	Given I want to withdraw 100.0 from the following <accountId>
    When I perform the withdraw task
    Then The withdraw operation should return the message: "Saldo insuficiente para a operacao."
    And My account balance after the withdraw should be <balance>
   
   Examples:
     | accountId  | balance | 
     |     10	    |  60     |

  
     