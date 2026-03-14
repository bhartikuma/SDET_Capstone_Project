package steps;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.*;
import utils.ApiUtils;
import utils.CSVReaderUtil;
import utils.DatabaseUtil;
import utils.DriverFactory;
import utils.ExcelReader;

public class FlightBookingSteps {

    private static final Logger log = LogManager.getLogger(FlightBookingSteps.class);

    private HomePage         homePage;
    private FlightsPage      flightsPage;
    private PurchasePage     purchasePage;
    private ConfirmationPage confirmationPage;

    // 3 sources se aaya data yahan store hoga
    private String loadedName;
    private String loadedCity;
    private String loadedCard;

    // ─────────────────────────────────────────
    // API PRE-CONDITION — RestAssured
    // ─────────────────────────────────────────

    @Given("the BlazeDemo application is reachable")
    public void apiPreConditionCheck() {
        log.info("[PRE-CONDITION] API health check starting...");
        boolean isUp = ApiUtils.isSiteReachable("https://blazedemo.com");
        Assert.assertTrue(isUp,
            "[PRE-CONDITION FAILED] BlazeDemo is DOWN. Aborting UI tests.");
        log.info("[PRE-CONDITION PASSED] Site is up.");
    }

    // ─────────────────────────────────────────
    // DATA LOADING — Excel, CSV, DB
    // ─────────────────────────────────────────

    @Given("I load passenger data from Excel")
    public void loadFromExcel() throws Exception {
        // ExcelReader.getExcelData() — tera exact method naam
        Object[][] data = ExcelReader.getExcelData();
        loadedName = data[0][0].toString();
        loadedCity = data[0][1].toString();
        loadedCard = data[0][2].toString();
        log.info("[EXCEL] Loaded — Name: " + loadedName
                + " | City: " + loadedCity);
    }

    @Given("I load passenger data from CSV")
    public void loadFromCSV() throws Exception {
        // CSVReaderUtil.getCSVData() — tera exact method naam
        Object[][] data = CSVReaderUtil.getCSVData();
        loadedName = data[0][0].toString();
        loadedCity = data[0][1].toString();
        loadedCard = data[0][2].toString();
        log.info("[CSV] Loaded — Name: " + loadedName
                + " | City: " + loadedCity);
    }

    @Given("I load passenger data from database")
    public void loadFromDatabase() throws Exception {
        // DatabaseUtil.getDBData() — tera exact method naam
        Object[][] data = DatabaseUtil.getDBData();
        loadedName = data[0][0].toString();
        loadedCity = data[0][1].toString();
        loadedCard = data[0][2].toString();
        log.info("[DB] Loaded — Name: " + loadedName
                + " | City: " + loadedCity);
    }

    // ─────────────────────────────────────────
    // HOME PAGE
    // ─────────────────────────────────────────

    @Given("I navigate to BlazeDemo homepage")
    public void navigateToHomePage() {
        DriverFactory.getDriver().get("https://blazedemo.com");
        homePage = new HomePage(DriverFactory.getDriver());
        log.info("Navigated to BlazeDemo homepage");
    }

    @When("I select {string} as departure and {string} as destination")
    public void selectCities(String from, String to) {
        // selectCities() — tera exact original method
        homePage.selectCities(from, to);
        log.info("Cities selected: " + from + " -> " + to);
    }

    @When("I click on Find Flights button")
    public void clickFindFlights() {
        homePage.clickFindFlights();
        flightsPage = new FlightsPage(DriverFactory.getDriver());
        log.info("Find Flights clicked");
    }

    // ─────────────────────────────────────────
    // FLIGHTS PAGE
    // ─────────────────────────────────────────

    @Then("flight results page should be displayed")
    public void verifyFlightsPage() {
        Assert.assertTrue(
            flightsPage.isFlightListDisplayed(),
            "Flight list not visible on page"
        );
        log.info("Flight results page verified");
    }

    @When("I select the first available flight")
    public void selectFirstFlight() {
        // chooseFirstFlight() — tera exact original method
        flightsPage.chooseFirstFlight();
        purchasePage = new PurchasePage(DriverFactory.getDriver());
        log.info("First flight selected");
    }

    // ─────────────────────────────────────────
    // PURCHASE PAGE
    // ─────────────────────────────────────────

    @Then("purchase page should be displayed with flight details")
    public void verifyPurchasePage() {
        Assert.assertTrue(
            purchasePage.isDisplayed(),
            "Purchase page not displayed"
        );
        log.info("Purchase page verified. Cost: "
                + purchasePage.getTotalCost());
    }

    // Feature file se direct data aane wala step (Scenario Outline)
    @When("I fill passenger details with name {string} city {string} card {string}")
    public void fillPassengerDetailsFromFeature(String name,
                                                String city,
                                                String card) {
        // tera exact original methods
        purchasePage.cardDetails("11", "2027", name);
        purchasePage.enterDetails(name, city, card);
        log.info("[FEATURE FILE] Details filled for: " + name);
    }

    // Excel/CSV/DB se loaded data wala step
    @When("I fill passenger details from loaded data")
    public void fillFromLoadedData() {
        // tera exact original methods — same call
        purchasePage.cardDetails("11", "2027", loadedName);
        purchasePage.enterDetails(loadedName, loadedCity, loadedCard);
        log.info("[LOADED DATA] Details filled for: " + loadedName);
    }

    @When("I click on Purchase Flight button")
    public void clickPurchase() {
        purchasePage.clickPurchase();
        confirmationPage = new ConfirmationPage(DriverFactory.getDriver());
        log.info("Purchase Flight button clicked");
    }

    // ─────────────────────────────────────────
    // CONFIRMATION PAGE
    // ─────────────────────────────────────────

    @Then("booking confirmation should be displayed for {string}")
    public void verifyConfirmationWithName(String name) {
        // getConfirmation() — tera exact original method
        String msg = confirmationPage.getConfirmation();
        log.info("Confirmation text: " + msg);
        Assert.assertTrue(
            msg.contains("Thank you"),
            "Thank you message not found! Actual: " + msg
        );
    }

    @Then("booking confirmation should be displayed")
    public void verifyConfirmation() {
        // getConfirmation() — tera exact original method
        String msg = confirmationPage.getConfirmation();
        log.info("Confirmation text: " + msg);
        Assert.assertTrue(
            msg.contains("Thank you"),
            "Thank you message not found! Actual: " + msg
        );
    }
}