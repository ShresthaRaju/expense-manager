Feature: Add Income Category
  User adds a custom category for income

  @inc-feature
  Scenario: User can leave income category name empty
    Given I am on the add custom category dashboard
    When I select income type
    And I click the add category button
    Then I should see category name required message

  @inc-feature
  Scenario: User can add a custom income category with valid name
    Given I am on the add custom category dashboard
    When I select income type
    And I enter income category name
    And I click the add category button
    Then I should see income category added toast

  @inc-feature
  Scenario: User can add an existing income category name
    Given I am on the add custom category dashboard
    When I select income type
    And I enter income category name
    And I click the add category button
    Then I should see income category exist message




