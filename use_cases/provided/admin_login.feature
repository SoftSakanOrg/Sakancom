Feature: Admin login
Description: the administrator logs into the building system
Actor: Administrator

Scenario: Administrator can login
Given that the administrator is not logged in
And the password is "123"

Then the administrator login succeeds
And the administrator is logged in
