package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.MobilePage;

public class DriverFactory {

    public static WebDriver driver;
    protected static HomePage homePage;
    protected static MobilePage mobilePage;


    /**
     * This method is used to initialize the thradlocal driver on the basis of given
     * browser
     *
     * @param browserName
     * @return this will return tldriver.
     */
    public static void init_driver(String browserName) {

        System.out.println("browser value is: " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/Executable_Driver/chromedriver.exe");
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--remote-allow-origins=*");
        	WebDriverManager.chromedriver().setup();
        	driver=new ChromeDriver(options);
        } else if ((browserName.equalsIgnoreCase("firefox")))
        {
        	WebDriverManager.firefoxdriver().setup();
           // System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/Executable_Driver/geckodriver.exe");
            driver=new FirefoxDriver();
        }
        else{
            System.out.println("Please Provide The Correct Browser Name: " + browserName);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(ConstantVariable.applicationURL);

        /****** Initalization Of Pages Classes ******/
        homePage=new HomePage(driver);
        mobilePage=new MobilePage(driver);

    }
}