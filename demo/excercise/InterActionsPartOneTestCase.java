package excercise;

import com.beust.ah.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class InterActionsPartOneTestCase {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/nguye/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--profile-directory=Profile 2");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        actions = new Actions(driver);
    }

    @Test
    public void TC01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        Thread.sleep(3000);

        actions.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC02_Myntra() throws InterruptedException {
        driver.get("http://www.myntra.com/");
        Thread.sleep(3000);

        actions.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
        Thread.sleep(3000);

        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(3000);

        actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();

        actions.moveToElement(driver.findElement(By.xpath("//div//span[@class='menu-title'][text()='Đồ Chơi']"))).perform();
        actions.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//ul[@class='nav-links']//a[text()='Board Game']"))).perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong")).getText(), "BOARD GAME");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}