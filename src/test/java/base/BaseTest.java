package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = DriverFactory.getDriver();
        driver.get("https://blazedemo.com/");
    }

    @AfterMethod
    public void tearDown() {

        if(driver != null) {
            driver.quit();
            DriverFactory.driver = null;
        }
    }
}