package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By fromCity = By.name("fromPort");
    By toCity = By.name("toPort");
    By findFlights = By.cssSelector("input[type='submit']");

    public void selectCities(String from,String to){

        new Select(driver.findElement(fromCity)).selectByVisibleText(from);
        new Select(driver.findElement(toCity)).selectByVisibleText(to);
    }

    public void clickFindFlights(){
        driver.findElement(findFlights).click();
    }
}