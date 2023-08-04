Feature: Owner selecting one of his Buildings

  Scenario: Owner selects one of his buildings
    Given Owner wants to select one of his buildings
    When Owner enters building id like 9
    Then all building info should appear to him


    Scenario: Owner fails to select one of his buildings
      Given Owner wants to select one of his buildings but fails to do it
      When Owner enters a invalid id like 111
      Then an invalid message should appear to him