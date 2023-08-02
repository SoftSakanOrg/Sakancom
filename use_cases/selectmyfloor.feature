Feature: Owner Selecting one of his floors
Scenario: owner selecting floor
  Given the owner selected a specific building
  When owner selects the floor of one of his buildings by entering floor id like 25
  Then the data of this floor should appear to him
  When owner enters floor id that doesnt exist like 100
  Then a message should appear telliung him to enter a valid one
