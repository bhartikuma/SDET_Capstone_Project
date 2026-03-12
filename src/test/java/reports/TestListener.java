package reports;

import org.testng.*;
import com.aventstack.extentreports.*;
import tests.FlightBookingTest;

import utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

ExtentReports extent = ExtentManager.getReport();
ThreadLocal<ExtentTest> test = new ThreadLocal<>();

@Override
public void onTestStart(ITestResult result) {

ExtentTest extentTest =
extent.createTest(result.getMethod().getMethodName());

test.set(extentTest);

}

@Override
public void onTestFailure(ITestResult result) {

test.get().pass(result.getThrowable());

Object currentClass = result.getInstance();
WebDriver driver = ((tests.FlightBookingTest) currentClass).driver;

String path = ScreenshotUtil.captureScreenshot(driver,
result.getMethod().getMethodName());

test.get().addScreenCaptureFromPath(path);

}

@Override
public void onFinish(ITestContext context) {

extent.flush();

}

}