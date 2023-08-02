Feature: view Building

  Scenario: Owner views the Building successfully
    Given the owner is on the owner page
    When the owner searches for his buildings by his id like 33
    Then the owner buildings should appear to him
