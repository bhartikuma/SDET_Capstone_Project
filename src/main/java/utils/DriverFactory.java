package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver      = new ThreadLocal<>();
    private static ThreadLocal<String>    browserName = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static String getBrowserName() {
        return browserName.get();
    }

    public static void initDriver(String browser) {
        browserName.set(browser);
        WebDriver d;

        switch (browser.toLowerCase().trim()) {

            case "edge":
                System.setProperty(
                    "webdriver.edge.driver",
                    System.getProperty("user.dir")
                        + "/drivers/edgedriver_win64/msedgedriver.exe"
                );
                d = new EdgeDriver();
                log.info("Edge browser launched — manual driver");
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                d = new ChromeDriver();
                log.info("Chrome browser launched");
                break;
        }

        d.manage().window().maximize();
        driver.set(d);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            browserName.remove();
            log.info("Browser closed");
        }
    }
}