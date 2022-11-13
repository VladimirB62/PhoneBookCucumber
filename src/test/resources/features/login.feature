Feature: Login

  @validData
  Scenario: Login with the valid data
    Given Navigate to Page Phone Book
    When Click on Login Tab
    And Enter the Valid Data
    And Click on Login button
    Then Sign out button displayed

    @invalidPassword
    Scenario Outline: Login with invalid email and invalid Password
      Given Navigate to Page Phone Book
      When Click on Login Tab
      And Enter invalid  Data
      |email  |password  |
      |<email>|<password>|
      And Click on Login button
      Then Allert appeared
      Examples:
        | email              | password |
        |gushiddink@gmail.com|12345678Aa|
        |gushiddink@gmail.com|12345678a$|