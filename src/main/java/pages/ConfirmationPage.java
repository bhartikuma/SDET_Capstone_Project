package pages;

import org.openqa.selenium.*;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
    }

    public String getConfirmation(){

        return driver.findElement(By.tagName("h1")).getText();
    }
}