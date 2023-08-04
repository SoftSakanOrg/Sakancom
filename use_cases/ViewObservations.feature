Feature: viewObservation

  Scenario: Admin views the Observation successfully
    Given the Admin is on the Admin page panel
    When there is data in Observations table
    Then the Observation should appear to the admin



  Scenario: Admin  fails to view observations
    Given the Admin is on the Admin page panel
    When there is no data in Observations table
    Then a message should appear  telling no Observation is found
