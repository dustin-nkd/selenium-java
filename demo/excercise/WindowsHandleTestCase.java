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

public class WindowsHandleTestCase {
    WebDriver driver;
    Alert alert;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }

    @Test
    public void TC01_GitHub() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(3000);

        String windowID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[normalize-space()='GOOGLE']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Google");
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Google");

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[normalize-space()='FACEBOOK']")).click();
        Thread.sleep(3000);
        switchToWindowByTitle("Facebook – log in or sign up");
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[normalize-space()='TIKI']")).click();
        Thread.sleep(3000);
        switchToWindowByTitle("Tiki");
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[normalize-space()='LAZADA']")).click();
        Thread.sleep(3000);
        switchToWindowByTitle("Lazada");
        System.out.println(driver.getTitle());

        Assert.assertEquals(driver.getTitle(), "Lazada Việt Nam | 6.6 Sale Rẻ Kinh Hoàng - Siêu Khuyến Mãi Đã Về!");

        switchToWindowByTitle("Selenium WebDriver");

        closeAllWindowsExceptMain(windowID);
        Thread.sleep(3000);
    }

    @Test
    public void TC02_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        Thread.sleep(3000);

        String mainWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Sony Xperia has been added to comparison list.");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        closeAllWindowsExceptMain(mainWindow);

        driver.findElement(By.xpath("//a[normalize-space()='Clear All']")).click();

        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC03_Cambridge() {
        driver.get();
    }

    private void switchToWindowByTitle(String title) {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains(title)) {
                break;
            }
        }
    }

    private void closeAllWindowsExceptMain(String windowID) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(windowID)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}