Feature: Select Request

  Scenario: Admin views the Request successfully
    Given the admin is on the requests page
    When  the admin selects a request by ID like 8
    Then the request should appear to the admin
