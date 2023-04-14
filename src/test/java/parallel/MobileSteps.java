package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Util.ElementUtil;
import driver.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MobilePage;

public class MobileSteps extends DriverFactory {

	@When("Select the {string} and click on search")
	public void select_the_and_click_on_search(String categoryName) {
	    mobilePage.selectAllHeading(categoryName);
	}

	@Then("Select the {string}")
	public void select_the(String subCategoryName) {
	    mobilePage.selectSubCategory(subCategoryName);
	}

	@Then("Search the product {string}")
	public void search_the_product(String productName) {
	    mobilePage.inputSearchProductName(productName);
	    mobilePage.searchButtonSelect();
	}

	@Then("Select the first result is {string}")
	public void select_the_first_result(String productName) {
	    mobilePage.selectFirstProduct(productName);
	}

	@Then("Swtich to new tab")
	public void swtich_to_new_tab() {
	    ElementUtil.swtichToTab(1, false);
	}

	@Then("Verify the results is opened for {string}")
	public void verify_the_results_is_opened_for(String productName) {
	    
	}
}
