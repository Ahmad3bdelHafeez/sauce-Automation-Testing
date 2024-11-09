@smoke
Feature: F08_Wishlist | Users add items to their wishlist
  Scenario Outline: User add any item ("<Product>") to his wishlist
    Given user go to home page
    When  add any product "<Product>" to the wishlist by clicking on the love icon
    Then  success message is displayed on the top of the page
    Examples:
      | Product |
#      | Build your own computer        |
      | Apple MacBook Pro 13-inch        |
      | HTC One M8 Android L 5.0 Lollipop        |
      | $25 Virtual Gift Card                                         |
  Scenario Outline: User add any item to his wishlist and reflected to the wishlist counter
    Given user go to home page
    When  add any product "<Product>" to the wishlist by clicking on the love icon
    Then  wishlist counter is more than zero items
    Examples:
      | Product |
      | HTC One M8 Android L 5.0 Lollipop        |