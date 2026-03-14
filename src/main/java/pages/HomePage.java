package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private static final Logger log = LogManager.getLogger(HomePage.class);
    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement departureDropdown;

    @FindBy(name = "toPort")
    private WebElement destinationDropdown;

    @FindBy(css = "input[value='Find Flights']")
    private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("HomePage initialized");
    }

    // Tera original method — SAME naam, SAME signature — kuch nahi badla
    public void selectCities(String from, String to) {
        new Select(departureDropdown).selectByVisibleText(from);
        log.info("Departure selected: " + from);
        new Select(destinationDropdown).selectByVisibleText(to);
        log.info("Destination selected: " + to);
    }

    public void clickFindFlights() {
        findFlightsButton.click();
        log.info("Find Flights button clicked");
    }
}