Feature: Sign Up
  User registers to the system

  @register-feature
  Scenario Outline: User can provide empty value
    Given I am on the sign up screen
    When I input familyName <familyName>
    And I input email <email>
    And I input password <password>
    And I input confirmPassword <confirmPassword>
    And I click on the get started button
    Then I should see field required message

    Examples:
      | familyName | email                | password | confirmPassword |
      | Huri       | hawahuri@example.com | password | password        |

  @register-feature
  Scenario Outline: User can provide invalid detail
    Given I am on the sign up screen
    When I input firstName <firstName>
    And I input familyName <familyName>
    And I input email <email>
    And I input password <password>
    And I input confirmPassword <confirmPassword>
    And I click on the get started button
    Then I should see invalid detail error message

    Examples:
      | firstName | familyName | email                | password | confirmPassword |
      | H         | Huri       | hawahuri@example.com | password | password        |

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
      | firstName | familyName | email                | password | confirmPassword |
      | Hawa      | Huri       | hawahuri@example.com | password | password        |

  @register-feature
  Scenario Outline: User can provide existing email
    Given I am on the sign up screen
    When I input firstName <firstName>
    And I input familyName <familyName>
    And I input email <email>
    And I input password <password>
    And I input confirmPassword <confirmPassword>
    And I click on the get started button
    Then I should see email exists message

    Examples:
      | firstName | familyName | email                | password | confirmPassword |
      | Hawa      | Huri       | hawahuri@example.com | password | password        |




