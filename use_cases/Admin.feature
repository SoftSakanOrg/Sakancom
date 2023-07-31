Feature: Admin functions

  Scenario Outline: admin chooses a specific function
    Given admin is on admin panel
    When  admin enters "<functype>" to choose a function
    Then  a  specific function will be called

    Examples:

      | functype |
      |    A     |
      |    B     |
      |    C     |
      |    D     |
      |    E     |
      |    K     |