package excercise;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTestCase {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC02_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String content = "Automation FC";
        alert.sendKeys(content);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: "+ content);
    }

    @Test
    public void TC04_Authentication_Alert() {
        String username = "admin";
        String password = "admin";
        String authUrl = "https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
        driver.get(authUrl);

        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='example'] p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}