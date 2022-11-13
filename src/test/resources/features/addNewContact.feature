Feature: Add new contact

  @addContact
  Scenario Outline: Add new contact
    Given Navigate to Page Phone Book
    When Click on Login Tab
    And Enter the Valid Data
    And Click on Login button
    Then Click on Add Tab
    Then Enter new contact data
    |Name  |LastName  |Phone  |email  |Address  |description  |
    |<Name>|<LastName>|<Phone>|<email>|<Address>|<description>|
    And Click on Save button
    Then Assert contact appears
    Examples:
      |Name   |LastName|Phone     |email               |Address                   |description|
      |Janluka|Poljuka |+521562598|janlukapol@gmail.com|Turin, Italy,fibbonachi 34|goalkeeper |