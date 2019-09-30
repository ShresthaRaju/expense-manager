Feature: Add Income Transaction
  User adds a transaction for income

  @incomeTransaction-feature
  Scenario: User can leave empty amount
    Given I am on the dashboard
    When I hit the add button
    And I select income type
    And I choose the category type
    And I input a memo
    And I do not enter an income amount
    And I hit the add button on income
    Then I receive an error message

  @incomeTransaction-feature
  Scenario: User can add a new income transaction
    Given I am on the dashboard
    When I hit the add button
    And I select income type
    And I choose the category type
    And I input a memo
    And I input an income amount
    And I hit the add button on income
    Then I should see the added income transaction in the dashboard



#  @incomeTransaction-feature
#  Scenario: User can add a new income transaction
#    Given I am on the dashboard
#    When I hit the add button
#    And I select income type
#    And I choose the category type
#    And I do not enter an memo
#    And I input an income amount
#    And I hit the add button on income
#    Then I should see the added income transaction in the dashboard



