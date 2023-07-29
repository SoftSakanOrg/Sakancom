Feature: Admin Login


  Scenario: Admin logs in successfully
    Given the Admin is on the login page
    When the Admin enters "a@a.a" and "999"
    Then the Admin should be redirected to their dashboard


  Scenario: Admin logs in failed
    Given the Admin is on the login page
    When the Admin enters "abd@a.com" and "999"
    Then an invalid message should be printed