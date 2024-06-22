Feature: JSONPlaceholder API Testing

  Scenario: Get all posts
    Given I call the "getTest" request
    Then I see status code 200

  Scenario: Create a new post
    Given I call the "postTest" request
    Then I see status code 200
    And I see response "title" field with value "foo"

  Scenario: Update a post
    Given I call the "putTest" request
    Then I see status code 200
    And I see response "title" field with value "foo"

  Scenario: Delete a post
    Given I call the "deleteTest" request
    Then I see status code 200