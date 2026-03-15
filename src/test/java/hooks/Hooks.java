package hooks;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.ScreenshotUtil;

public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);
    private ExtentTest extentTest;


    @Before(order = 1)
    public void initDriver(Scenario scenario) {

        String threadId = String.valueOf(Thread.currentThread().getId());
        String browser  = System.getProperty("browser." + threadId, "chrome");

        DriverFactory.initDriver(browser);

        System.out.println("====================================");
        System.out.println("BROWSER : " + browser.toUpperCase());
        System.out.println("THREAD  : " + threadId);
        System.out.println("SCENARIO: " + scenario.getName());
        System.out.println("====================================");

        log.info("Browser: " + browser + " | Scenario: " + scenario.getName());
    }

    @Before(order = 2)
    public void initReport(Scenario scenario) {
        extentTest = ExtentManager.getInstance().createTest(
            scenario.getName()
            + " [" + DriverFactory.getBrowserName().toUpperCase() + "]"
        );
        extentTest.info("Browser: " + DriverFactory.getBrowserName());
    }

    @After(order = 1)
    public void captureResultAndQuit(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("FAILED: " + scenario.getName());

            
            if (DriverFactory.getDriver() != null) {
                try {
                    String path = ScreenshotUtil.captureScreenshot(
                        DriverFactory.getDriver(),
                        scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                    );
                    byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                                             .getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot on Failure");
                    extentTest.fail("FAILED — screenshot: " + path);
                } catch (Exception e) {
                    log.error("Screenshot can't be taken: " + e.getMessage());
                    extentTest.fail("FAILED — screenshot error: " + e.getMessage());
                }
            } else {
                log.error("Driver is null — browser does not launch ");
                extentTest.fail("FAILED — browser launch failed: "
                        + scenario.getName());
            }

        } else {
            log.info("PASSED: " + scenario.getName());
            extentTest.pass("Scenario passed");
        }

        ExtentManager.getInstance().flush();
        DriverFactory.quitDriver();
        log.info("=== Scenario Ended: " + scenario.getName() + " ===\n");
    }
}