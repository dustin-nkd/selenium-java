package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class IframeTestCase {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01_ToiDiCodeDao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        Thread.sleep(3000);

        By iframe = By.xpath("//div[contains(@class,'fb_iframe_widget')]//iframe");
        Assert.assertTrue(driver.findElement(iframe).isDisplayed());

        driver.switchTo().frame(driver.findElement(iframe));
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/ancestor::div[@class='lfloat']//div[@class='_1drq']")).getText(), "400,753 followers");
    }

    @Test
    public void TC02_Campus() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[contains(@class,'osano-cm-accept-all')]")).click();
        driver.findElement(By.xpath("//div[@id='imageTemplateContainer']")).click();
        Thread.sleep(5000);

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='formTemplateContainer']//iframe")));

        WebElement year = driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-2']"));
        WebElement residence = driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-3']"));

        select = new Select(year);
        select.selectByVisibleText("Junior");
        Thread.sleep(3000);

        select = new Select(residence);
        select.selectByVisibleText("West Dorm");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id='RESULT_RadioButton-4_0']/parent::td")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id='FSsubmit']")).click();

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("nav[class='header header--desktop'] a.menu-item-login")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id='login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='message-error']")).getText(), "Username and password are both required.");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
