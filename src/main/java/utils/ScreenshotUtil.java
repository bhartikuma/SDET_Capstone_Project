package utils;

import java.io.File;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

public static String captureScreenshot(WebDriver driver, String testName) {

File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

String path = System.getProperty("user.dir")
        + "/screenshots/" + testName + ".png";

try {

FileUtils.copyFile(src, new File(path));

} catch (IOException e) {
e.printStackTrace();
}

return path;

}
}