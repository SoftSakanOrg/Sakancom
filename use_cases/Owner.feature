Feature: Owner functions

  Scenario Outline: Owner chooses a specific function
    Given Owner is on owners panel
    When  Owner enters "<functype>" to choose a function
    Then  a  specific function will be called depending on functype


    Examples:
    |functype|
    |   A    |
    |   E    |