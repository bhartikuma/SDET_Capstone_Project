package tests;

import org.testng.Assert;
import java.time.Duration;

import org.testng.annotations.*;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.*;
import utils.DataProviderUtil;

@Listeners(reports.TestListener.class)


public class FlightBookingTest  {

public WebDriver driver;

@BeforeMethod
public void setup(){

driver = new ChromeDriver();

driver.manage().window().maximize();

driver.get("https://blazedemo.com");
}

@DataProvider
public Object[][] testData() throws Exception{

return DataProviderUtil.getAllData();
}



//@Test(dataProvider="testData")
//public void bookFlightTest(String name,String city,String card){
//
//System.out.println("Running test with "+name);
//
//HomePage home = new HomePage(driver);
//
//home.selectCities("Boston","London");
//
//home.clickFindFlights();
//
//FlightsPage flights = new FlightsPage(driver);
//
//flights.chooseFirstFlight();
//
//PurchasePage purchase = new PurchasePage(driver);
//
//
//purchase.enterDetails(name,city,card);
//purchase.cardDetails("11", "2017", "bharti");
//System.out.println("Jenkins added   2");
//
//}
@Test(dataProvider="testData")
public void bookFlightTest(String name, String city, String card) {
    System.out.println("Running test with " + name);

    HomePage home = new HomePage(driver);
    home.selectCities("Boston", "London");
    home.clickFindFlights();

   
    FlightsPage flights = new FlightsPage(driver);
    flights.chooseFirstFlight();

  
    PurchasePage purchase = new PurchasePage(driver);
    
    purchase.cardDetails("11", "2017", "bharti");
    purchase.enterDetails(name, city, card);
    
    ConfirmationPage com = new  ConfirmationPage(driver);
    System.out.println(com.getConfirmation());

   
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    
    WebElement successHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.container h1"))); 
    
    String actualMsg = successHeader.getText();
    Assert.assertTrue(actualMsg.contains("Thank you"), 
            "Expected success message not found! Actual: " + actualMsg);
}


@AfterMethod
public void tearDown(){

driver.quit();
}

}