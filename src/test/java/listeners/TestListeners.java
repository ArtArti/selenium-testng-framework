package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utils.ScreenshotUtil;
import utilities.WebDriverManagerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListeners implements ITestListener {

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    private WebDriver getDriver() {
        return WebDriverManagerUtil.getDriver(); // Should be thread-safe now
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("📘=== Test Suite started: " + context.getName() + " ===", true);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("📕=== Test Suite finished: " + context.getName() + " ===", true);
    }

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("🚀 Test started: " + result.getMethod().getMethodName(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("✅ Test passed: " + result.getMethod().getMethodName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("❌ Test failed: " + result.getMethod().getMethodName(), true);
        Reporter.log("🔎 Reason: " + result.getThrowable(), true);

        WebDriver driver = getDriver();
        if (driver != null) {
            String fileName = "Failed_" + result.getMethod().getMethodName() + "_" + getTimeStamp();
            ScreenshotUtil.captureScreenshot(driver, fileName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("⚠️ Test skipped: " + result.getMethod().getMethodName(), true);
        Reporter.log("🔎 Reason: " + result.getThrowable(), true);

        WebDriver driver = getDriver();
        if (driver != null) {
            String fileName = "Skipped_" + result.getMethod().getMethodName() + "_" + getTimeStamp();
            ScreenshotUtil.captureScreenshot(driver, fileName);
        }
    }
}
