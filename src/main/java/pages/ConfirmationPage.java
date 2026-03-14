package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmationPage {

    private static final Logger log = LogManager.getLogger(ConfirmationPage.class);
    private WebDriver driver;

    @FindBy(css = "div.container h1")
    private WebElement confirmationHeader;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("ConfirmationPage initialized");
    }

    // Tera original method — SAME naam
    public String getConfirmation() {
        String text = confirmationHeader.getText();
        log.info("Confirmation message: " + text);
        return text;
    }

    public boolean isBookingConfirmed() {
        return confirmationHeader.getText().contains("Thank you");
    }
}