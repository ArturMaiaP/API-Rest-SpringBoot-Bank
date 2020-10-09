#Author: your.email@your.domain.com
#Keywords Summary :
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

@Transfer
Feature: Account transfer transaction


  @TransferSuccess
  Scenario Outline: Successful withdraw
    Given I want to transfer 499.0 from <accountFrom> to <accountTo>
    When I perform the transfer task
    Then The transfer operation should return the message: "Saque realizado com sucesso!"
    And My account balance after the transfer should be 501.0
    And the destination account balance shoulde be 
   
   Examples:
     | accountId  | balance | 
     | 123456 	  |  1000   |
     
  @TransferOverLimit
  Scenario Outline: 
  	Given I want to withdraw 501.0 from the following <accountId>
    When I perform the withdraw task
    Then The withdraw operation should return the message: "Operacao de transferencia tem um limite maximo de 500 por operacao."
    And My account balance after the withdraw should be <balance>
   
   Examples:
     | accountId  | balance | 
     |    101 	  |  2000   |

  @TransferInsufficientBalance
  Scenario Outline: 
  	Given I want to withdraw 100.0 from the following <accountId>
    When I perform the withdraw task
    Then The withdraw operation should return the message: "Saldo insuficiente para a operacao."
    And My account balance after the withdraw should be <balance>
   
   Examples:
     | accountId  | balance | 
     |     10	    |  60     |
  
     
