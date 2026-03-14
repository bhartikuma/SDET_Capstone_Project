package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightsPage {

    private static final Logger log = LogManager.getLogger(FlightsPage.class);
    private WebDriver driver;

    // First row ka "Choose This Flight" button
    @FindBy(xpath = "(//input[@value='Choose This Flight'])[1]")
    private WebElement firstFlightButton;

    // Table visible hai ya nahi check karne ke liye
    @FindBy(xpath = "//table[@class='table']")
    private WebElement flightsTable;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("FlightsPage initialized");
    }

    // Tera original method naam same rakha
    public void chooseFirstFlight() {
        firstFlightButton.click();
        log.info("First flight selected");
    }

    public boolean isFlightListDisplayed() {
        return flightsTable.isDisplayed();
    }
}