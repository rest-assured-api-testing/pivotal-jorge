Feature: Request for Stories end point
  description

  Scenario: Get a Story
    Given I build a "GET" story request
    When I build a story endpoint request
    Then The response should be the Ok status