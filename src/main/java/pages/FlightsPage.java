package pages;

import org.openqa.selenium.*;

public class FlightsPage {

    WebDriver driver;

    public FlightsPage(WebDriver driver){
        this.driver = driver;
    }

    By chooseFlight = By.xpath("(//input[@value='Choose This Flight'])[1]");

    public void chooseFirstFlight(){
        driver.findElement(chooseFlight).click();
    }
}