Feature: Request for project Endpoints
  description

  Scenario: Get a Pivotal project
    Given I build a "GET" request
    When I execute a "projects/{project_id}" request
    Then the response code status should be "OK"

  Scenario: Get an Epic with invalid ID
    Given I build a "GET" project request with invalid ID
    When I execute an project end point request
    Then The project response should be "NotFound"