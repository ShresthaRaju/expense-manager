Feature: Edit Transaction Details
  User edit details of added transactions

  @viewTransaction-features
  Scenario: User can successfully edit transaction details
    Given I am on transaction details dashboard
    When I click edit button
    And I should update all the details of the transaction
#    and i enter all the valid details
    And I should click on save button
#    and i click on the save button
    And I am taken to to the dashboard
#    then i should see updated details on the dashboard


 @viewTransaction-features
  Scenario: User can leave a field empty
    Given I am on transaction details dashboard
    When I click edit button
    And I leave a flied empty
    And I should click on save button
#   and i click on save button
    And I should see an  error message
#   then i should see an error message

