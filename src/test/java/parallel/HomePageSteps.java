package parallel;

import driver.ConstantVariable;
import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MobilePage;

public class HomePageSteps extends DriverFactory{
    

    /*=============================================*/
    @Given("User Enter the amazon webpage URL")
    public void user_enter_the_amazon_webpage_url() {
        driver.get(ConstantVariable.applicationURL);
    }
    @Then("User landed on the amazon website according to region of user")
    public void user_landed_on_the_amazon_website_according_to_region_of_user() {
        homePage.verifyTheAmaoznIndia();
    }
    @Then("user verify the user present on home page")
    public void user_verify_the_user_present_on_home_page() {
        homePage.verifyHomePage();
    }
    @When("user click on hanburger icon")
    public void user_click_on_hanburger_icon() {
        homePage.clickonAllCategories();
    }
    @Then("verify the {string} with {string} name is visible")
    public void verify_the_shop_by_department_with_tv_appliances_electronics_name_is_visible(String categoryName,String subCategoryName) {
        homePage.clickOnCategory(categoryName,subCategoryName);
    }
    @When("user click on {string} with {string} name")
    public void user_click_on_tv_audio_cameras_with_television_name(String subSubCategoryName,String productType) {
        homePage.clickOnSection(subSubCategoryName, productType);
    }
    @Then("verify user landed on the correct {string} name")
    public void verify_user_landed_on_the_correct_television_name(String productType) {
        //mobilePage.verifyTelevisionPage(productType);
    }

}
