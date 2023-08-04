Feature: owner wants to add a new building

  Scenario: getting contact info
    Given selecting contact info by owner id
    When selecting  contact info by owner id like 33
    Then contact info should appear

    Scenario: owner adding a new building
      Given owner wants a new building to be added
      When owner inserts all info to building
      Then new building should appear to the table

      Scenario: inserting this observation to observation table
        Given the Observation should appear to admin
        When owner adds the building
        Then the adding observation will appear to admin