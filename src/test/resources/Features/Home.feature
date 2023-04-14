Feature: Test the Functionality of Amazon Home Page

  @HomePage
  Scenario Outline: Verify the functionality of Home of Amazon.in
    #Given User Enter the amazon webpage URL
    Then User landed on the amazon website according to region of user
    Then user verify the user present on home page
    When user click on hanburger icon
    Then verify the "<categoryName>" with "<subCategoryName>" name is visible
    When user click on "<subSubCategoryName>" with "<productType>" name
    Then verify user landed on the correct "<productType>" name

    Examples:
      | categoryName       | subCategoryName             | subSubCategoryName  | productType |
      | shop by department | TV, Appliances, Electronics | tv, audio & cameras | Televisions  |