Feature: Users Login


  Scenario: Users logs in successfully
    Given the Users is on the login page
    When the Users enters "a@a.a" and "999"
    Then the Users should be redirected to their dashboard


  Scenario: Users logs in failed
    Given the Users is on the login page
    When the Users enters  "abd@a.com" and "999"
    Then an invalid message should be printed