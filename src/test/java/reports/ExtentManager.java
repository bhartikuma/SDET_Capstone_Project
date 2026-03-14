package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    static ExtentReports extent;

    // Tera existing method — bilkul same rakha
    public static ExtentReports getReport() {
        if (extent == null) {
            ExtentSparkReporter reporter =
                new ExtentSparkReporter("reports/AutomationReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    // Naya alias — Hooks.java getInstance() call karta hai
    public static ExtentReports getInstance() {
        return getReport();
    }
}