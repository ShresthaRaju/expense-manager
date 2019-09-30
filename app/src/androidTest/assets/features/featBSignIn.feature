Feature: Sign In
  User Signs in into the system

  @login-feature
  Scenario Outline: User can provide an empty field
    Given I am on the sign in screen
    When I enter email <email>
    And I click on the sign in button
    Then I receive a field required message

    Examples:
      | email                |
      | hawahuri@example.com |

  @login-feature
  Scenario Outline: User can provide an invalid value
    Given I am on the sign in screen
    When I enter email <email>
    And I enter password <password>
    And I click on the sign in button
    Then I receive an invalid login message

    Examples:
      | email                | password  |
      | hawahuri@example.com | passwords |

  @login-feature
  Scenario Outline: User can successfully sign in and access the dashboard with valid details
    Given I am on the sign in screen
    When I enter email <email>
    And I enter password <password>
    And I click on the sign in button
    Then I am redirected to the dashboard

    Examples:
      | email                | password |
      | hawahuri@example.com | password |
