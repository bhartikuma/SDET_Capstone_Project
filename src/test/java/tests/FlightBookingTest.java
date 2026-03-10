package tests;

import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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



@Test(dataProvider="testData")
public void bookFlightTest(String name,String city,String card){

System.out.println("Running test with "+name);

HomePage home = new HomePage(driver);

home.selectCities("Boston","London");

home.clickFindFlights();

FlightsPage flights = new FlightsPage(driver);

flights.chooseFirstFlight();

PurchasePage purchase = new PurchasePage(driver);

purchase.enterDetails(name,city,card);
System.out.println("Jenkins added   2");
}
//@Test(dataProvider="testData")
//public void bookFlightTest(String name,String city,String card){
//
//System.out.println("Running test with "+name);
//
//HomePage home = new HomePage(driver);
//home.selectCities("Boston","London");
//home.clickFindFlights();
//
//FlightsPage flights = new FlightsPage(driver);
//flights.chooseFirstFlight();
//
//PurchasePage purchase = new PurchasePage(driver);
//purchase.enterDetails(name,city,card);
//
//Assert.assertTrue(false); 
//// force failure
//}

@AfterMethod
public void tearDown(){

driver.quit();
}

}