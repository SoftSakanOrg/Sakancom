Feature: Delete Observations

  Scenario: Observations deleted sucessfully
    Given the admin is on the main admin page
    When the admin deletes the observations
    Then the observations table should be empty