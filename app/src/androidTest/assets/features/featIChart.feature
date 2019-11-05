Feature: View Chart
  User views chart for expenses and income

  @ViewChart-feature
  Scenario: User can view expense chart
    Given I am on dashboard
    When I click charts tab
    And I select on expense type
    Then i should see an expense pie chart

  @ViewChart-feature
  Scenario: User can view income chart
    Given I am on dashboard
    When I click charts tab
    And I select on income type
    Then i should see an income pie chart