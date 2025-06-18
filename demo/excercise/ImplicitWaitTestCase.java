package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ImplicitWaitTestCase {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_ImplicitWait_ZeroSeconds() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC02_ImplicitWait_ThreeSeconds() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC03_ImplicitWait_FiveSeconds() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC04_ImplicitWait_FifteenSeconds() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}