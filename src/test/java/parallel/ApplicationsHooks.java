package parallel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import Util.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driver.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class ApplicationsHooks extends DriverFactory{

	ConfigReader configReader;
	Properties properties;
	String screenshotName;
	String tempScenarioName;

	public ApplicationsHooks(){
		super();
	}

	/**
	 * Reading the config properties when application Starts
	 */
	@Before(order = 0)
	public void getPropert() {
		configReader = new ConfigReader();
		properties = configReader.initProperties();
	}

	/**
	 * After reading the properties file then pass the browser name to the
	 * driverFactory class
	 */
	@Before(order = 1)
	public void launchBrowser(Scenario scenario) {
		tempScenarioName = scenario.getName();
		String browserName = properties.getProperty("browser");
		System.out.println("<<<<<===== Current " + tempScenarioName + " Scenario is Running =====>>>>>");
		init_driver(browserName);
	}

	@After(order = 0)
	public void closingBrowser() {
		if (!tempScenarioName.toLowerCase().contains("api")) {
			System.out.println("Closing the Browser");
			driver.quit();
		}
	}

	/**
	 * Taking Screenshots when Scenario is failed
	 */
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (!tempScenarioName.toLowerCase().contains("api")) {
			if (scenario.isFailed())
				screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}

	@BeforeStep
	public void beforeEachStep(Scenario scenario) {
		if (!tempScenarioName.toLowerCase().contains("api")) {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String strDate = dateFormat.format(date).replaceAll(" -:", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", strDate);
		}
	}
	
	

}
