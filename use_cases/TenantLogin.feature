Feature: Tenant Login

Scenario: Tenant logs in successfully
Given the tenant is on the login page
When the tenant enters "Abdallah@gmail.com" and "123"


Then the tenant should be redirected to their dashboard
And the tenant should see a welcome message

Scenario: Tenant enters invalid credentials
Given the tenant is on the login page
When the tenant enters invalid credentials
Then an error message should be displayed
And the tenant should remain on the login page