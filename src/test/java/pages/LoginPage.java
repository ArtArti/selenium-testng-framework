package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;



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
}
