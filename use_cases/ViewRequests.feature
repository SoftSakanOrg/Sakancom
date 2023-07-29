Feature: viewRequest

  Scenario: Admin views the requests successfully
    Given the Admin is on the Admin page
    When there is data in requests table
    Then the requests should appear to the admin



  Scenario: Admin views the fails to view requests
    Given the Admin is on the Admin page
    When there is no data in requests table
    Then a message should appear "No requests currently" telling no data is found
