package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class BrowserTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Page_Url() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC02_Page_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC03_Page_Navigation() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC04_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        Assert.assertTrue(Objects.requireNonNull(driver.getPageSource()).contains("Login or Create an Account"));
        driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
