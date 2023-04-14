package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import Util.ElementUtil;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MobilePage extends DriverFactory {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    @FindBy(id = "searchDropdownBox")
    WebElement selectAllHeading;
    
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;
    
    @FindBy(id="nav-search-submit-button")
    WebElement searchButton;
    
    @FindBy(xpath = "//tr[@class='a-spacing-small po-model_name']/td[2]/span")
    WebElement productName;
    
    String selectSubCategory = "//span[contains(text(),'value')]";
        
    String selectFirstProduct= "//div[@cel_widget_id='MAIN-SEARCH_RESULTS-value']/div/div/div/div/div/div/div/h2";
    
    

        public MobilePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
    }

    public void selectAllHeading(String categoryName) {
    	selectAllHeading.click();
    	Select selectCategory= new Select(selectAllHeading);
    	selectCategory.selectByVisibleText(categoryName);
    	searchButtonSelect();
    }
    
    public void selectSubCategory(String subCategoryName) {
    	String tempSubCategory=selectSubCategory.replace("value", subCategoryName);
    	driver.findElement(By.xpath(tempSubCategory)).click();
    }
    
    public void inputSearchProductName(String productName) {
    	searchInput.sendKeys(productName);
    }
    
    public void searchButtonSelect() {
    	searchButton.click();
    }
    
    
    public void selectFirstProduct(String productName) {
    	String productValue=selectFirstProduct.replace("value", "2");
    	ElementUtil.waitForElement(driver.findElement(By.xpath(productValue)));
    	String text = driver.findElement(By.xpath(productValue)).getText();
    	if (text.contains(productName)) {
			Assert.assertTrue(true, "First Product is "+productName);
			driver.findElement(By.xpath(productValue)).click();
		} else {
			Assert.assertFalse(false, "First Product is "+productValue);
		}
    }
    
    public void verifyProduct(String productName) {
    	String text = driver.findElement(By.xpath("//tr[@class='a-spacing-small po-model_name']/td[2]/span")).getText();
    	if (productName.contains(text)) {
			Assert.assertTrue(true, "Product Found");
		}
    	else {
    		Assert.assertFalse(false, "Product Not Found");
    	}
    }
}
