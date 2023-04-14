package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverFactory;
import Util.ElementUtil;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends DriverFactory {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    //<=====Web Elements=======>
    @FindBy(id="nav-hamburger-menu")
    WebElement allcategories;

    @FindBy(id = "glow-ingress-line1")
    WebElement homePageElement;

    @FindBy(xpath = "//div[contains(text(),'shop by department')]")
    WebElement shopByDepartment;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    //<=====Actions=======>
    public void clickonAllCategories() {
        ElementUtil.implictWait();
        wait.until(ExpectedConditions.elementToBeClickable(allcategories));
        allcategories.click();
        ElementUtil.waitForElement(shopByDepartment);
        String gettingtextshopByDepartment=shopByDepartment.getText();
        Assert.assertEquals(gettingtextshopByDepartment,"Shop By Department");
    }

    public void clickOnCategory(String categoryName, String subCategoryName) {
        WebElement sideMenuCategory = driver.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]//following::li/a/div[text()='" + subCategoryName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(sideMenuCategory));
        ElementUtil.clickElement(sideMenuCategory);
    }

    public void clickOnSection(String subSubCategoryName, String productType) {
        WebElement finalCategoryWithproductType = driver.findElement(By.xpath("//div[contains(text(),'" + subSubCategoryName + "')]/following::li/a[text()='" + productType + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(finalCategoryWithproductType));
        ElementUtil.clickElement(finalCategoryWithproductType);
    }

    public void verifyTheAmaoznIndia() {
        String currentURL = driver.getCurrentUrl();
        if (currentURL.contains("https://www.amazon.in/")) {
            System.out.println("User Landed on the Amazon India Home Page");
        } else {
            System.out.println("User Landed on Other Than Amazon India Home Page Region");
        }
    }

    public void verifyHomePage(){
        String getText=homePageElement.getText();
        if (getText.contentEquals("Hello")){
            System.out.println("User At Home Page");
        }
        else {
            System.out.println("User Not Present At Home Page");
        }
    }

}
