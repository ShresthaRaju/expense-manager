Feature: Add Expense Category
  User adds a custom category for expense

  @exp-feature
  Scenario: User can leave category name empty
    Given I am on the add category dashboard
    When I select expense type
    And I click the add button
    Then I should see name required message

  @exp-feature
  Scenario: User can add a custom expense category with valid name
    Given I am on the add category dashboard
    When I select expense type
    And I enter category name
    And I click the add button
    Then I should see category added toast

  @exp-feature
  Scenario: User can add an existing category name
    Given I am on the add category dashboard
    When I select expense type
    And I enter category name
    And I click the add button
    Then I should see category exist message




