@smoke
Feature: F06_homeSlider | User could click on each item in the slider

  Scenario Outline: Guest user could click on item ("<Item>") in the slider
    Given user go to home page
    When  user go to the slider section and click on item "<ItemNumber>" "<Item>"
    Then  user should redirect to the item page URL "<URL>"
    Examples:
      | ItemNumber | Item | URL |
      | 1          | nokia-lumia-1020    |    https://demo.nopcommerce.com/nokia-lumia-1020         |
      | 2          |iphone-6    |    https://demo.nopcommerce.com/iphone-6         |
