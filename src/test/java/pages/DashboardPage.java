package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DashboardPage {
     private final WebDriver driver;

     private final By header = By.id("greeting");

     public DashboardPage(WebDriver driver){
         this.driver = driver;
     }

    public boolean isdashboardvisible() {
        WebDriverWait wait = new WebDriverWait(driver, 20); // For Selenium 3.x
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        return element.isDisplayed();
    }

}
