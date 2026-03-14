package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // browser parameter lo, default chrome
        String browser = System.getProperty("browser", "chrome");
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        driver.get("https://blazedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        // DriverFactory.driver = null — ye ab kaam nahi karta ThreadLocal mein
        // quitDriver() use karo — ye khud null handle karta hai
        DriverFactory.quitDriver();
        driver = null;
    }
}