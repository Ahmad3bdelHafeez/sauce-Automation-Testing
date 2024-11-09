@smoke
Feature: F04_Search | User could search about any product
  Scenario Outline: Guest user could search using product name ("<Word>")
    Given user go to home page
    When  user enter word "<Word>"
    And   user click on search button
    Then  user get products that contains the word "<Word>" successfully
    Examples:
      | Word |
      | book |
      | laptop |
      | nike |
  Scenario Outline: Guest user could search using product sku "<sku>"
    Given user go to home page
    When  user enter sku "<sku>"
    And   user click on search button
    And   user click on the product after clicking on search button
    Then  user get the product that contains the sku "<sku>" successfully
    Examples:
      | sku |
      | SCI_FAITH |
      | APPLE_CAM |
      | SF_PRO_11 |
