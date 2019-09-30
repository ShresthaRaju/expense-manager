Feature: Add Expense Transaction
  User adds a transaction for expense

  @expenseTransaction-feature
  Scenario: User can provide empty filed
    Given I am on main dashboard
    When I click on add button on dashboard
    And I select expense type
    And I choose the category type for expense
    And I enter a memo
    And I do not enter a expense amount
    And I click on add button
    Then I should see error message

  @expenseTransaction-feature
  Scenario: User can add a new expense transaction
    Given I am on main dashboard
    When I click on add button on dashboard
    And I select expense type
    And I choose the category type for expense
    And I enter a memo
    And I enter a expense amount
    And I click on add button
    Then I should see the added message

#  @expenseTransaction-feature
#  Scenario: User can provide a empty memo
#    Given I am on main dashboard
#    When I click on add button on dashboard
#    And I select expense type
#    And I choose the category type for expense
#    And I do not enter a memo
#    And I enter a expense amount
#    And I click on add button
#    Then I should see error message for memo

