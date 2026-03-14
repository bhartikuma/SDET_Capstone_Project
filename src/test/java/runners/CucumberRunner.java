package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
    features   = "src/test/resources/features",
    glue       = {"steps", "hooks"},
    plugin     = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setBrowser(String browser) {
        // ThreadLocal mein directly set karo
        System.setProperty("browser." + Thread.currentThread().getId(), browser);
        System.out.println(">>> BROWSER SET TO: " + browser.toUpperCase()
                + " | Thread: " + Thread.currentThread().getId());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}