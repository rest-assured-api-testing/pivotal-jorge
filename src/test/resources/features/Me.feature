Feature: Request for Me endpoint
  description
  Scenario: Get Login status
    Given I build a "GET" Login request
    When I execute me request
    Then The response Should return OK Status