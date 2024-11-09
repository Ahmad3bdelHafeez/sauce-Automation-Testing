@smoke
Feature: F03_currencies | Users could change the currency to get the new price by the changed currency

  Scenario Outline: Guest user could change the currency ("<Currency>") successfully
    Given user go to home page
    When  user should select "<Currency>" currency from the dropdown list
    Then  user get the new price by the changed currency "<Currency>" successfully
    Examples:
      | Currency  |
      | Euro      |
      | US Dollar |
