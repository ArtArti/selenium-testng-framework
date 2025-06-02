package tests;

import base.BaseTest;
import data.LoginData;
import listeners.TestListeners;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ReRunScript;
import utils.ScreenshotUtil;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginCredential", dataProviderClass = LoginData.class, retryAnalyzer = ReRunScript.class)
    public void testLoginPageLoads(String username, String password) {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterCredentials(username, password);
            Reporter.log("Login attempted for user: " + username, true);

            // Handle alert
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            Reporter.log("Alert Text: " + alertText, true);
            alert.accept();

            if (alertText.equalsIgnoreCase("login Success")) {
                Reporter.log("Login successful for user: " + username, true);

                // Proceed to DashboardPage test
                String currentUrl = driver.getCurrentUrl();
                Reporter.log("Current URL after login: " + currentUrl, true);

                DashboardPage dashboardPage = new DashboardPage(driver);

                // Check if Dashboard is visible
                Assert.assertTrue(dashboardPage.isdashboardvisible(), "Dashboard not visible for user: " + username);
                Reporter.log("Dashboard is visible for user: " + username, true);

                // Additional textbox check (if you have any specific method for it)
//                Assert.assertTrue(dashboardPage.isSearchBoxVisible(), "Search box not visible on dashboard for user: " + username);

                // Screenshot
                ScreenshotUtil.captureScreenshot(driver, "Dashboard_" + username);

            } else {
                Reporter.log("❌ Invalid login for user: " + username, true);
                ScreenshotUtil.captureScreenshot(driver, "InvalidLogin_" + username);
                Assert.fail("Invalid login attempt should not proceed for user: " + username);
            }


        } catch (Exception e) {
            Reporter.log("Exception in testLoginPageLoads for user: " + username, true);
            Reporter.log(e.getMessage(), true);
            ScreenshotUtil.captureScreenshot(driver, "Exception_" + username);
            Assert.fail("Test failed due to exception for user: " + username + " - " + e.getMessage());
        }
    }
}
