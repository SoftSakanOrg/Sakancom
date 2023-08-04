Feature: Owner adding floor

  Scenario: Owner adding floors to a specific building
    Given Owner wants to add floor
    When Owner adds floor to a building with id 9
    Then floor should be added to this building
    And this observation should be added to Observations table
    And it will be added as a request to advertisment_requests waiting for admin to accept it
    And floors number should be incremented in the specific building

    Scenario: Selecting last floor
      Given selects last floor to get the id
      When selecting the floor ordered by desc limit by one
      Then last floor should appear from table