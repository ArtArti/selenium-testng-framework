package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManagerUtil {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions chromeOptions = new ChromeOptions();

                    // Chrome headless configuration
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-web-security");
                    chromeOptions.addArguments("--allow-running-insecure-content");
                    chromeOptions.addArguments("--ignore-certificate-errors");

                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();

                    // Use DesiredCapabilities for Edge in Selenium 3.x
                    DesiredCapabilities edgeCapabilities = new DesiredCapabilities();

                    // Basic Edge configuration
                    edgeCapabilities.setCapability("ms:edgeOptions", new java.util.HashMap<String, Object>() {{
                        put("useAutomationExtension", false);
                        put("excludeSwitches", java.util.Arrays.asList("enable-automation"));
                    }});

                    driver.set(new EdgeDriver(edgeCapabilities));
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Maximize window
            try {
                driver.get().manage().window().maximize();
            } catch (Exception e) {
                System.err.println("Could not maximize window: " + e.getMessage());
            }
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}