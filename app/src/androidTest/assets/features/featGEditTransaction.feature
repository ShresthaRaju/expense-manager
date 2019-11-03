Feature: Edit Transaction Details
  User edit details of added transactions

  @viewTransaction-features
  Scenario: User can edit transaction details
    Given I am on transaction details dashboard
    When I click edit button
    And I should update all the details of the transaction
    And I should click on save button
    And I am taken to to the dashboard


 @viewTransaction-features
  Scenario: User can edit transaction details
    Given I am on transaction details dashboard
    When I click edit button
    And I leave a flied empty
    And I should click on save button
    And I should see an  error message

