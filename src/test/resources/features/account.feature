Feature: Request for Account EndPoint
  description

  Scenario: Get an Account
    Given I build a "GET" account request
    When I execute a Account endpoint request
    Then The Response status should be "OK"

  Scenario: Get an account with invalid ID
    Given I build a "GET" account request with invalid ID
    When I execute an account end point request
    Then The response should be "NotFound"
