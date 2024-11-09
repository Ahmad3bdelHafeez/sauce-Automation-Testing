@smoke
Feature: F05_hoverCategories | User could click on sub categories using top menu hover

  Scenario Outline: Guest user could click on sub category ("<SubCategory>") using hover of the top menu category ("<Category>")
    Given user go to home page
    When  user hover the category "<Category>" and click on "<SubCategory>"
    Then  user get sub categories that clicked on before "<SubCategory>"
    Examples:
      | Category | SubCategory |
      | Computers    |    Computers         |
      | Computers    |    Desktops         |
      | Computers    |    Notebooks         |
      | Computers    |    Software         |
      | Electronics    |    Electronics         |
      | Electronics    |    Camera & photo         |
      | Electronics    |    Cell phones         |
      | Electronics    |    Others         |
      | Apparel    |    Apparel         |
      | Apparel    |    Shoes         |
      | Apparel    |    Clothing         |
      | Apparel    |    Accessories         |
      | Digital downloads    |    Digital downloads         |
      | Books    |    Books        |
      | Jewelry    |    Jewelry       |
      | Gift Cards    |    Gift Cards        |
