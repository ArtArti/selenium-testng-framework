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
    public void testLoginPageLoads(String username, String password, boolean expectedToPass) {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterCredentials(username, password);
            Reporter.log("Login attempted for user: " + username, true);

            WebDriverWait wait = new WebDriverWait(driver, 5);

            String alertText = "";
            boolean alertPresent = false;
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alertText = alert.getText();
                alert.accept();
                alertPresent = true;
                Reporter.log("Alert text: " + alertText, true);
            } catch (Exception e) {
                Reporter.log("No alert appeared for user: " + username, true);
            }

            boolean loginSuccess = alertPresent && alertText.equalsIgnoreCase("login Success");

            if (expectedToPass) {
                Assert.assertTrue(loginSuccess, "Expected login to succeed but it failed.");
                DashboardPage dashboardPage = new DashboardPage(driver);
                Assert.assertTrue(dashboardPage.isdashboardvisible(), "Dashboard not visible for user: " + username);
                ScreenshotUtil.captureScreenshot(driver, "Dashboard_" + username);
            } else {
                Assert.assertFalse(loginSuccess, "Expected login to fail but it succeeded.");
                ScreenshotUtil.captureScreenshot(driver, "InvalidLogin_" + username);
            }

        } catch (Exception e) {
            Reporter.log("Exception for user: " + username + " → " + e.getMessage(), true);
            ScreenshotUtil.captureScreenshot(driver, "Exception_" + username);
            Assert.fail("Exception occurred for user: " + username + ": " + e.getMessage());
        }
    }

}
