@smoke
Feature: F02_Login | Users could use login functionality to use their accounts
  Scenario: User could login with valid email and password
  Given user go to login page
  When  user login with "valid" email and password
  And   user press on login button
  Then  user login to the system successfully

  Scenario: User could login with invalid email and password
  Given user go to login page
  When  user login with "invalid" email and password
  And   user press on login button
  Then  user could not login to the system
