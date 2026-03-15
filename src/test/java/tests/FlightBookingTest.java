package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import java.time.Duration;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.DataProviderUtil;
import utils.ScreenshotUtil;

@Listeners(reports.TestListener.class)
public class FlightBookingTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com");
    }

    @DataProvider
    public Object[][] testData() throws Exception {
        return DataProviderUtil.getAllData();
    }

    @Test(dataProvider = "testData")
    public void bookFlightTest(String name, String city, String card) {
        System.out.println("Running test with: " + name);

        // HomePage
        HomePage home = new HomePage(driver);
        home.selectCities("Boston", "London");
        home.clickFindFlights();

        // FlightsPage
        FlightsPage flights = new FlightsPage(driver);
        flights.chooseFirstFlight();

        // PurchasePage
        PurchasePage purchase = new PurchasePage(driver);
        purchase.cardDetails("11", "2027", name);
        purchase.enterDetails(name, city, card);

        // clickPurchase() 
        purchase.clickPurchase();

        // ConfirmationPage
        ConfirmationPage confirm = new ConfirmationPage(driver);
        String msg = confirm.getConfirmation();

        // Screenshot
        ScreenshotUtil.captureScreenshot(driver,
            "TestNG_" + name.replaceAll(" ", "_"));

        // Assert
        Assert.assertTrue(
            msg.contains("Thank you"),
            "Confirmation not found! Actual: " + msg
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}