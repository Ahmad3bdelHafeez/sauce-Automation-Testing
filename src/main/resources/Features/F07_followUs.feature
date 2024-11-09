@smoke
Feature: F07_followUs | Users could open followUs links
  Scenario Outline: User opens ("<Channel>") link
  Given user go to home page
  When  user opens "<Channel>" link
  Then  "<Link>" is opened in new tab
    Examples:
      | Channel | Link |
      | facebook|  https://www.facebook.com/nopCommerce    |
      | twitter |  https://x.com/nopCommerce   |
      | rss     |  https://demo.nopcommerce.com/new-online-store-is-open  |
      | youtube |  https://www.youtube.com/user/nopCommerce  |
