package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class MultipleBrowserTestCase {
    WebDriver driver;
    String environmentURL;

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void initialBrowser(String browser, @Optional("PRODUCTION") String environment) {
        switch (browser.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported");
        }

        switch (environment.toUpperCase()) {
            case "DEV":
                environmentURL = "https://dev.google.com";
                break;
            case "QA":
                environmentURL = "https://qa.google.com";
                break;
            case "STAGING":
                environmentURL = "https://staging.google.com";
                break;
            case "PRODUCTION":
                environmentURL = "https://www.google.com";
                break;
            default:
                throw new RuntimeException("Environment is not supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(environmentURL);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    @Test
    public void navigateToGoogle() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).sendKeys("test");
        Thread.sleep(3000);
    }
}
