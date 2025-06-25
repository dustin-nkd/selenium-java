package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitTestCase {
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    @Test
    public void TC01() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        Boolean isCountdownReached = fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']")).getText();
                System.out.println("Current countdown time: " + countdown);
                return countdown.equals("01:01:00");
            }
        });

        Assert.assertTrue(isCountdownReached);
    }
}
