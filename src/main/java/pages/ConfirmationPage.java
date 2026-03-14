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

public class ConfirmationPage {

    private static final Logger log = LogManager.getLogger(ConfirmationPage.class);
    private WebDriver driver;

    @FindBy(css = "div.container h1")
    private WebElement confirmationHeader;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait add kiya — page load hone tak ruko
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOf(confirmationHeader));

        log.info("ConfirmationPage initialized");
    }

    public String getConfirmation() {
        String text = confirmationHeader.getText();
        log.info("Confirmation: " + text);
        return text;
    }

    public boolean isBookingConfirmed() {
        return confirmationHeader.getText().contains("Thank you");
    }
}