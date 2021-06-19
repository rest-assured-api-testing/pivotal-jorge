Feature: Requests for Epics endpoint
  description

  Scenario: Get a Epic
    Given I build "GET" request
    When I execute "epics/{epics_id}" request
    Then the response status code should be "OK"

  Scenario: Get an Epic with invalid ID
    Given I build a "GET" epic request with invalid ID
    When I execute an epic end point request
    Then The epic response should be "BadRequest"