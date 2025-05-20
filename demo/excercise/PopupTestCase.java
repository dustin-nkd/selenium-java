package excercise;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class PopupTestCase {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        actions = new Actions(driver);
    }

    @Test
    public void TC01_NgoaiNgu24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='custom-dialog']//div[@role='dialog']")).isDisplayed());

        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("automation");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("automation");
        driver.findElement(By.xpath("//div[@role='dialog']//button[contains(.,'Đăng nhập')]")).click();

        Assert.assertEquals(driver.findElement(By.id("notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.xpath("//h2[contains(.,'Đăng nhập')]//button")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElements(By.xpath("//div[@id='custom-dialog']//div[@role='dialog']")).size(), 0);
    }

    @Test
    public void TC02_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");

        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());

        driver.findElement(By.xpath("//div[@role='dialog']//button[@class='btn-close']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']")).size(), 0);
    }

    @Test
    public void TC03_KMP() throws InterruptedException {
        driver.get("http://www.kmplayer.com/");
        Thread.sleep(3000);

        By popup = By.xpath("//div[@class='pop-conts']");

        if (driver.findElement(popup).isDisplayed())
        {
            driver.findElement(By.xpath("//div[@class='pop-container']//div[@class='close']")).click();
        }

        actions.moveToElement(driver.findElement(By.xpath("//ul//li[@class='pc pc64x']//a"))).perform();
        driver.findElement(By.xpath("//li[@class='pc']//a[text()='KMPlayer']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='sub']//h1[contains(text(),'KMPlayer - Video Player for PC')]")).getText(), "KMPlayer - Video Player for PC");
    }

    @Test
    public void TC04_Java_Code_Geeks() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(3000);

        By popup = By.xpath("//div[starts-with(@data-title, 'Newsletter-Books') and not(contains(@style, 'display:none'))]");

        if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
            driver.findElement(By.xpath("//div[@class='lepopup-element-html-content']//a[.='×']")).click();
        }

        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("selenium");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='page-title']//span")).getText(), "Selenium");
    }

    @Test
    public void TC05_DeHieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(3000);

        By popup = By.xpath("//div[contains(@class,'modal-content')]");

        if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
            driver.findElement(By.xpath("//div[contains(@class,'modal-content')]//button[@class='close']")).click();
        }

        driver.findElement(By.xpath("//a[starts-with(text(),' Đăng nhập')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='b-login']")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}