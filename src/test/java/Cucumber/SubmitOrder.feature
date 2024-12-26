
@tag
Feature: Purchase the order from Ecommerce Website

 Background:
 Given I landed on Ecommerce Page
 

  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password> 
    When I add the product <productName> to cart
    Then "Thankyou for the order" message is displayed on the Confirmation page.

    Examples: 
      | name  | password | productName  |
      | name1 |     5 | success | 
      | name2 |     7 | Fail    |
