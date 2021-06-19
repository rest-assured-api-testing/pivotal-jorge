Feature: Request for Stories end point
  description

  Scenario: Get a Story
    Given I build a "GET" story request
    When I build a story endpoint request
    Then The response should be the Ok status
  Scenario: Get an Epic with invalid ID
    Given I build a "GET" story request with invalid ID
    When I execute an story end point request
    Then The story response should be "BadRequest"