Feature: Test the Functionality of Amazon Home Page

  @MobilePage
  Scenario Outline: Verify the functionality of Television of Amazon.in
    #Given User Enter the amazon webpage URL
    Then User landed on the amazon website according to region of user
    Then user verify the user present on home page
    When Select the "<categoryName>" and click on search
    Then Select the "<subCategoryName>"
    Then Search the product "<productName>"
    Then Select the first result is "<productName>"
    And Swtich to new tab
    Then Verify the results is opened for "<productName>"

    Examples: 
      | categoryName | productName   | subCategoryName       |
      | Electronics  | OnePlus 11 5G | Mobiles & Accessories |