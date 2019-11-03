Feature: Delete Transaction
  User delete details of added transactions


  @viewTransaction-feature
  Scenario: User discard delete confirmation
    Given I am on transaction details of dashboard
    When I click delete button
    And I discard the confirmation for deletion
    Then I am shown the transaction details dashboard

  @viewTransaction-feature
  Scenario: User can delete transaction details
    Given I am on transaction details of dashboard
    When I click delete button
    And I confirm the deletion
    Then  i should see transaction deleted message
