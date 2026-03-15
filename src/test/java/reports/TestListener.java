package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // ExtentReports instance — ExtentManager se lena hai
    private static ExtentReports extent = ExtentManager.getReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        
        test.get().log(Status.FAIL, "Test FAILED: " + result.getName());
        test.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test SKIPPED: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}
}