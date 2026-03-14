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

public class PurchasePage {

    private static final Logger log = LogManager.getLogger(PurchasePage.class);
    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement nameField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "zipCode")
    private WebElement zipField;

    @FindBy(id = "creditCardNumber")
    private WebElement cardNumberField;

    @FindBy(id = "creditCardMonth")
    private WebElement cardMonthField;

    @FindBy(id = "creditCardYear")
    private WebElement cardYearField;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardField;

    @FindBy(css = "input[value='Purchase Flight']")
    private WebElement purchaseButton;

    @FindBy(css = "div.container h2")
    private WebElement totalCostLabel;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // Wait karo jab tak page load ho
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(nameField));
        log.info("PurchasePage initialized");
    }

    // ── Tera original method — bilkul same ──
    public void enterDetails(String name, String city, String card) {
        nameField.clear();
        nameField.sendKeys(name);
        cityField.clear();
        cityField.sendKeys(city);
        cardNumberField.clear();
        cardNumberField.sendKeys(card);
        log.info("enterDetails() called for: " + name);
    }

    // ── Tera original method — bilkul same ──
    public void cardDetails(String month, String year, String nameOnCard) {
        cardMonthField.clear();
        cardMonthField.sendKeys(month);
        cardYearField.clear();
        cardYearField.sendKeys(year);
        nameOnCardField.clear();
        nameOnCardField.sendKeys(nameOnCard);
        log.info("cardDetails() called");
    }

    // ── Naye methods jo Steps ko chahiye ──
    public void clickPurchase() {
        purchaseButton.click();
        log.info("Purchase Flight button clicked");
    }

    public boolean isDisplayed() {
        return purchaseButton.isDisplayed();
    }

    public String getTotalCost() {
        try {
            return totalCostLabel.getText();
        } catch (Exception e) {
            return "Cost label not found";
        }
    }
}