Feature: Request for Account EndPoint
  description

  Scenario: Get an Account
    Given I build a "GET" account request
    When I execute a Account endpoint request
    Then The Response status should be "OK"
