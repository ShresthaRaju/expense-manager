Feature: Sign Up
  User registers to the system

  @register-feature
  Scenario Outline: User can successfully sign up with valid details
    Given I am on the sign up screen
    When I input firstName <firstName>
    And I input familyName <familyName>
    And I input email <email>
    And I input password <password>
    And I input confirmPassword <confirmPassword>
    And I click on the get started button
    Then I should see the login screen

    Examples:
      | firstName | familyName | email               | password | confirmPassword |
      | New       | User       | newuser@example.com | password | password        |
