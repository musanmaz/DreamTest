Feature: Google Feature

  Scenario: Google Search
    Given I open the "google" page
    When I fill:
      | searchText | Cucumber |
    Then I click on "searchButton"