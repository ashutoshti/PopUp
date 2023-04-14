package Util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import com.github.dockerjava.api.model.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class ElementUtil extends DriverFactory {
	
	static WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		ElementUtil.driver = driver;
	}

	/**
	 * @author ashutoshtiwari
	 * @Description This method will clear the text from the textbox and enter the
	 *              new Text
	 * @since
	 *
	 */
	public static void clearAndSendKeys(WebElement webElement, String newText) {
		webElement.clear();
		webElement.sendKeys(newText);
	}

	/**
	 * @author ashutoshtiwari
	 * @Description This method will clear the text from the textbox and enter the
	 *              new Text
	 *
	 */
	public static void clearAndSendKeys(By webElement, String newText) {
		driver.findElement(webElement).clear();
		driver.findElement(webElement).sendKeys(newText);
	}

	public static void waitForElement(WebElement findOfElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(findOfElement));
	}

	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(pageLoadCondition);
	}

	/**
	 * @author Ashutosh Tiwari
	 * @since March 23, 2021
	 * @Description Validate the webElement is displayed or not
	 */
	public static boolean isElementDisplayed(WebElement ele) {
		boolean elementDisplayed = false;
		try {
			ele.isDisplayed();
			elementDisplayed = true;
		} catch (Exception e) {
			elementDisplayed = false;
		}
		return elementDisplayed;
	}

	/**
	 * @author Ashutosh Tiwari
	 * @since March 23, 2021
	 * @Description Validate the webElement is Clickable or not
	 */
	public static boolean isClickable(WebElement el, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author Ashutosh
	 * @since April 19, 2021
	 * @Description This method is javascript method which is used to click on
	 *              webelement is not click by the selenium click
	 */
	public static void clickElement(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void scrollToViewWebElement(WebElement webElement) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", webElement);
		/*Actions a = new Actions(driver);
		a.moveToElement(webElement);
		a.perform();*/
	}

	public static void softWait() {
		try {
			Thread.sleep(5 * 1000);
			System.out.println("Wait For 5 Seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hardWait() {
		try {
			Thread.sleep(10 * 1000);
			System.out.println("Wait For 10 Seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mouseOverAndScroll(WebElement webElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(0,500)", "");
	}

	public static void refreshPageAndWaitToLoad() {
		driver.navigate().refresh();
		waitForLoad(driver);
		ElementUtil.softWait();
	}

	public static void ExtremeWait(int timeinSeconds) {
		try {
			Thread.sleep(timeinSeconds * 1000);
			System.out.println("Wait For " + timeinSeconds + " Seconds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Ashutosh
	 * @since May 25, 2021
	 * @Description Genate Random Number Between 0 to Upper Limit of Number
	 */
	public static long randomNumberGernator(int upperLimitNumber) {
		/*Random random = new Random();
		int int_random = random.nextInt(upperLimitNumber);
		System.out.println("Random integer value from 0 to" + (upperLimitNumber - 1) + " : " + int_random);*/
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");  
	    Date date = new Date();  
	    System.out.println("Current Date And Time: "+formatter.format(date));
	    String temp=formatter.format(date);
	    long int_random=Long.parseLong(temp);
	    System.out.println("Random Number Gerneated" + int_random);
		return int_random;
		
	}

	/**
	 * @author Ashutosh
	 * @since May 25, 2021
	 * @Description Swtich to Frame
	 */
	public static void swtichFrame(String FrameName) {
		driver.switchTo().frame(FrameName);
	}

	/**
	 * @author Ashutosh
	 * @since May 25, 2021
	 * @Description Swtich to Frame
	 */
	public static void swtichFrame(int FrameNumber) {
		driver.switchTo().frame(FrameNumber);
	}

	/**
	 * @author Ashutosh
	 * @since May 25, 2021
	 * @Description Genate Random Phone Number
	 * 
	 */
	public static String randomPhoneNumberGernator() {
		long number = (long) (Math.random() * Math.pow(10, 10));
		String randomNumber = Long.toString(number);
		System.out.println("The Random Phone Number is: " + randomNumber);
		return randomNumber;

	}

	/**
	 * @author Santosh
	 * @since July 29, 2021
	 * @Description Dimesntion of screen
	 * 
	 */
	public static void setDimensionOfScreen(int width, int height) {
		Dimension dm = new Dimension(width, height);
		driver.manage().window().setSize(dm);
	}

	/**
	 * @author Santosh
	 * @since July 29, 2021
	 * @Description element should be visible in viewport
	 * 
	 */
	public static Boolean isVisibleInViewport(WebElement element) {
		WebDriver driver = ((RemoteWebElement) element).getWrappedDriver();
		return (Boolean) ((JavascriptExecutor) driver).executeScript(
				"var elem = arguments[0],                 " + "  box = elem.getBoundingClientRect(),    "
						+ "  cx = box.left + box.width / 2,         " + "  cy = box.top + box.height / 2,         "
						+ "  e = document.elementFromPoint(cx, cy); " + "for (; e; e = e.parentElement) {         "
						+ "  if (e === elem)                        " + "    return true;                         "
						+ "}                                        " + "return false;                            ",
				element);
	}

	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Font size of element
	 * 
	 */
	public static String getFontSize(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("Font Size is:- " +element.getCssValue("font-size"));
		return element.getCssValue("font-size");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Font size of element
	 * 
	 */
	public static String getFontSize(WebElement locator) {
		System.out.println("Font Size is:- " +locator.getCssValue("font-size"));
		return locator.getCssValue("font-size");
	}

	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Font color of element
	 * 
	 */
	public static String getFontColor(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("Font Color is:- " + element.getCssValue("color"));
		 String color=element.getCssValue("color");
		 System.out.println(color);
		 String actualColor="";
		 if(color.contains("rgba")) {
			 actualColor=convertColorToHex(color);
		 }
		 else {
			 actualColor=convertColorToHexBorderColor(color);
		 }
		return actualColor;
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Font color of element
	 * 
	 */
	public static String getFontColor(WebElement element) {
		System.out.println("Font Color is:- " + element.getCssValue("color"));
		 String color=element.getCssValue("color");
		 System.out.println(color);
		 String actualColor="";
		 if(color.contains("rgba")) {
			 actualColor=convertColorToHex(color);
		 }
		 else {
			 actualColor=convertColorToHexBorderColor(color);
		 }
		return actualColor;
	}

	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description padding size of element
	 * 
	 */
	public static String getPaddingSize(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("Padding size is:- " + element.getCssValue("padding"));
		System.out.println("padding-top size is:- " + element.getCssValue("padding-left"));
		System.out.println("padding-bottom size is:- " + element.getCssValue("padding-right"));
		
		return element.getCssValue("padding");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description font-style
	 * 
	 */
	public static String getFontStyle(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("font-family size is:- " + element.getCssValue("font-family"));
		return element.getCssValue("font-family");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description font-style
	 * 
	 */
	public static String getFontStyle(WebElement element) {
		System.out.println("font-family size is:- " + element.getCssValue("font-family"));
		return element.getCssValue("font-family");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Background color of element
	 * 
	 */
	public static String getBackgroundColor(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("Font Color is:- " + element.getCssValue("background-color"));
		String color=element.getCssValue("background-color");
		System.out.println(color);
		String actualColor="";
		 if(color.contains("rgba")) {
			 actualColor=convertColorToHex(color);
		 }
		 else {
			 actualColor=convertColorToHexBorderColor(color);
		 }
		return actualColor;
		//return element.getCssValue("background-color");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Background color of element
	 * 
	 */
	public static String getBottomColorBorder(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("Font Color is:- " + element.getCssValue("border-bottom-color"));
		String color=element.getCssValue("border-bottom-color");
		System.out.println(color);
		return convertColorToHex(color);
		//return element.getCssValue("background-color");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description Margin of element
	 * 
	 */
	public static String getMargin(By locator) {
		WebElement element = driver.findElement(locator);
		System.out.println("margin is:- " + element.getCssValue("margin"));
		return element.getCssValue("margin");
	}
	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description convertColorToHex
	 * 
	 */
	public static String convertColorToHex(String color) {
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		System.out.println(actualColor);
		return actualColor;
	}

	/**
	 * @author Santosh
	 * @return
	 * @since July 29, 2021
	 * @Description convertColorToHex
	 * 
	 */
	public static String convertColorToHexBorderColor(String color) {
		String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		System.out.println(actualColor);
		return actualColor;
	}
	
	public static void swtichToTab(int swtichToTab, boolean swtichToMainTab) {
		//Get the current window handle
		String windowHandle = driver.getWindowHandle();
		//Get the list of window handles
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		System.out.println("No of tabs Open is: "+tabs.size());
		//Use the list of window handles to switch between windows
		driver.switchTo().window(tabs.get(swtichToTab));
		if (swtichToMainTab) {
			//Switch back to original window
			driver.switchTo().window(tabs.get(0));	
		}
	}

	public static void implictWait(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

}
