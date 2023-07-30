Feature: RequestAction test

  Scenario: Admin gets the floorID successfully
    Given the admin is on the requests section
    When  the admin selects a requestID like 8
    Then the floorID should be received by the admin


  Scenario Outline: After selecting a request
    Given the admin selected a request
    When the admin enters "<answer>"
    Then the request will be accepted or rejected or admin will be redirected to main menu

    Examples:
      | answer |
      | A      |
      | B      |
      | C      |

#  Scenario: After selecting a request
#    Given the admin selected a request
#    When  the admin enters "B"
#    Then the request will be rejected
#
#  Scenario: After selecting a request
#    Given the admin selected a request
#    When  the admin enters "C"
#    Then the admin will be redirected to main menu