Feature: Add Expense Category
  User adds a custom category for expense

#  @exp-feature
#  Scenario Outline: User can add a custom expense category with valid name
#    Given I am on the add category dashboard
#    When I select expense type
#    And I enter category name <name>
#    And I click the add button
#    Then I should see category added toast
#
#    Examples:
#      | name          |
#      | Test Category |


  @exp-feature
  Scenario Outline: User can add an existing category name
    Given I am on the add category dashboard
    When I select expense type
    And I enter category name <name>
    And I click the add button
    Then I should see category exist message

    Examples:
      | name |
      | Test |

  @exp-feature
  Scenario: User can leave category name empty
    Given I am on the add category dashboard
    When I select expense type
    And I click the add button
    Then I should see name required message
