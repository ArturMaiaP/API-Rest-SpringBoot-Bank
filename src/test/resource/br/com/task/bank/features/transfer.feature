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
  Scenario Outline: Successful transfer
    Given I want to transfer 499.0 from <accountFrom> to <accountTo>
    When I perform the transfer task
    Then The transfer operation should return the message: "Transferencia realizada com sucesso!"
    And My account balance after the transfer should be 501.0
    And The destination account balance shoulde be 699.0
   
   Examples:
     | accountFrom  |  accountTo | myBalance | 
     | 123456 	    |  30        | 1000.0      |
     
  @TransferOverLimit
  Scenario Outline: Transfer maximum limit exceeded
  	Given I want to transfer 501.0 from <accountFrom> to <accountTo>
    When I perform the transfer task
    Then The transfer operation should return the message: "Operacao de transferencia tem um limite maximo de 500 por operacao."
    And My account balance after the transfer should be <myBalance>
    And The destination account balance shoulde be 699.0
   
   Examples:
     | accountFrom  |  accountTo | myBalance | 
     | 123456 	    |  30        | 501.0     |
     
  @TransferInsufficientBalance
  Scenario Outline: Account does not have funds for the transfer
  	Given I want to transfer 100.0 from <accountFrom> to <accountTo>
    When I perform the transfer task
    Then The transfer operation should return the message: "Saldo insuficiente para a operacao."
    And My account balance after the transfer should be <myBalance>
    And The destination account balance shoulde be 699.0
   
   Examples:
     | accountFrom  |  accountTo | myBalance | 
     | 10 	        |  30        | 60.0      |
     
 
  
     
