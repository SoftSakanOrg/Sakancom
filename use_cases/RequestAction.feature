Feature: RequestAction test

  Scenario: Admin views the Request successfully
    Given the admin is on the requests page
    When  the admin selects a request by ID like 8
    Then the request should appear to the admin


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