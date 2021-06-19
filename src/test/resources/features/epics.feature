Feature: Requests for Epics endpoint
  description

  Scenario: Get a Epic
    Given I build "GET" request
    When I execute "epics/{epics_id}" request
    Then the response status code should be "OK"