package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {

    WebDriver driver;

    public PurchasePage(WebDriver driver){
        this.driver = driver;
    }

    public void enterDetails(String name,String city,String card){

        driver.findElement(By.id("inputName")).sendKeys(name);
        driver.findElement(By.id("city")).sendKeys(city);

        new Select(driver.findElement(By.id("cardType")))
        .selectByVisibleText("Visa");

        driver.findElement(By.id("creditCardNumber")).sendKeys(card);

        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
}
