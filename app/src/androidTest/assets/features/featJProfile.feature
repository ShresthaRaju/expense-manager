Feature: View Profile
  User views profile

  @ViewProfile-feature
  Scenario: User can view their profile
    Given I am on the homepage
    When I click profile tab
    Then i should see my details