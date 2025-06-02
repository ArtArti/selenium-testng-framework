package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;
import utilities.WebDriverManagerUtil;


public class BaseTest {
    public WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        driver = WebDriverManagerUtil.getDriver(browser);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String baseUrl = ConfigReader.get("url");
        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        WebDriverManagerUtil.quitDriver();
    }
}
