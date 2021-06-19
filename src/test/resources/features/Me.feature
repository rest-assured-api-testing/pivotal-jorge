Feature: Request for Me endpoint
  description
  Scenario: Get Login status
    Given I build a "GET" Login request
    When I execute me request
    Then The response Should return OK Status

  Scenario: Get an Epic with invalid password
    Given I build a "GET" login request with invalid password
    When I execute a login end point request
    Then The login response should be "Forbidden"