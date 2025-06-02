package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for login fields
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void enterUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void enterCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

//    public String getErrorMessage() {
//        try {
//            // Check if alert is present
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//            String alertText = alert.getText();
//            alert.accept(); // Accepts the alert
//            return alertText;
//        } catch (TimeoutException | NoAlertPresentException e) {
//            // Fallback: check if inline error is present (only if applicable
//            return "No error message displayed.";
//        }
//    }
}
