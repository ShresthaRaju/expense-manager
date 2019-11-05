Feature: Edit Transaction Details
  User edit details of added transactions

  @viewTransaction-features
  Scenario: User can successfully edit transaction details
    Given I am on transaction details dashboard
    When I click edit button
    And i enter all the valid details
    And i click on the save button
    Then i should see updated details on the dashboard


  @viewTransaction-features
  Scenario: User can leave a field empty
    Given I am on transaction details dashboard
    When I click edit button
    And I leave a flied empty
    And i click on the save button
    Then i should see an error message

