package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CustomCheckBoxRadioButtonTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        Thread.sleep(3000);

        By radioButton = By.cssSelector("input#id_new_user");
        By checkboxButton = By.cssSelector("input#id_accept_tos");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(radioButton));
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(radioButton).isSelected());

        js.executeScript("arguments[0].click()", driver.findElement(checkboxButton));
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(checkboxButton).isSelected());
    }

    @Test
    public void TC02_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(3000);

        By canTho = By.cssSelector("div[aria-label='Cần Thơ']");
        By myQuang = By.cssSelector("div[aria-label='Mì Quảng']");

        driver.findElement(canTho).click();
        Assert.assertEquals(driver.findElement(canTho).getDomAttribute("aria-checked"), "true");

        driver.findElement(myQuang).click();
        Assert.assertEquals(driver.findElement(myQuang).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}