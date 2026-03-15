package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

public class FlightsPage {

    private static final Logger log = LogManager.getLogger(FlightsPage.class);
    private WebDriver driver;

    @FindBy(xpath = "(//input[@value='Choose This Flight'])[1]")
    private WebElement firstFlightButton;

    @FindBy(xpath = "//table[@class='table']")
    private WebElement flightsTable;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOf(flightsTable));
        log.info("FlightsPage initialized");
    }

    public void chooseFirstFlight() {
        // Wait add kiya — button visible hone tak ruko
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(firstFlightButton));
        firstFlightButton.click();
        log.info("First flight selected");
    }

    public boolean isFlightListDisplayed() {
        return flightsTable.isDisplayed();
    }
}