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
@CreateAccount
Feature: Create account operation

  @CreateSuccess
  Scenario: Successful open account operation
    Given I want to create an account for the user "jesus" 
    And With the cpf "01102203344" 
    And With 100.0 in the balance
    When I save the account
    Then The system should return the account number with the message: " Conta cadastrada com sucesso!"   
    
     
  @CreateWithoutCPF
  Scenario: Null CPF 
    Given I want to create an account for the user "jesus" 
    And With the cpf 
    And With 60.0 in the balance
    When I save the account
    Then The system should return the message: "E necessario informar um cpf para abertura de uma nova conta."  
   
  @CreateInvalidCPF
  Scenario: Invalid CPF
    Given I want to create an account for the user "jesus" 
    And With the cpf "0110110111111" 
    And With 60.0 in the balance
    When I save the account
    Then The system should return the message: "CPF informado para criacao de conta esta invalido."  
   
  @CreateInsufficientInitialBalance
  Scenario: Insufficient funds to open an account
    Given I want to create an account for the user "jesus" 
    And With the cpf "01101101111" 
    And With 49.0 in the balance
    When I save the account
    Then The system should return the message: "Saldo insuficiente para abertura de uma nova conta." 
   