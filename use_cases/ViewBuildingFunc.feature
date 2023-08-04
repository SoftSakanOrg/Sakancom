Feature: Viewing floor of own Building
  Scenario: Owner wants to view a floor of his Building
    Given the owner wants to view a specific floor of his building
    When owner enters "A" and building with id 9
    Then floor should appear to him from the specific building

  Scenario: Owner fails to view a floor of his Building
    Given the owner failed to view his floor
    When owner enters "A" and building id that doesn`t exit like 111
    Then a message should appear telling no floors available


  Scenario: Owner wants to go back to main menu
    Given the owner tries to go back to main menu
    When owner enters "E"
    Then owner should transfer back to main menu