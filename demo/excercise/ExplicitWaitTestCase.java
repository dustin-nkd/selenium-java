package excercise;

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
import java.util.Objects;

public class ExplicitWaitTestCase {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    @Test
    public void TC01_GitHub_Invisible() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']//button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC02_GitHub_Visible() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']//button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC03_Telerik() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"), "No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText(), "No Selected Dates to display.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table//a[contains(text(),'23')]")));
        driver.findElement(By.xpath("//table//a[contains(text(),'23')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"), "Monday, June 23, 2025"));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText(), "Monday, June 23, 2025");
        wait.until(ExpectedConditions.attributeContains(By.xpath("//a[text()='23']/parent::td"), "class", "rcSelected"));
        Assert.assertTrue(Objects.requireNonNull(driver.findElement(By.xpath("//a[text()='23']/parent::td")).getDomAttribute("class")).contains("rcSelected"));
    }

    @Test
    public void TC04_GoFile() {
        driver.get("https://gofile.io/?t=uploadFiles");
        String image1 = "image1.jpg";
        String image2 = "image2.jpg";
        String image3 = "image3.jpg";
        String uploadFilesPath = System.getProperty("user.dir") + "\\demo\\image\\";
        String image1Path = uploadFilesPath + image1;
        String image2Path = uploadFilesPath + image2;
        String image3Path = uploadFilesPath + image3;
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#fileList div.progress-container"))));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center>h2"), "Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text-center>h2")).getText(), "Upload Complete");
        driver.get(driver.findElement(By.cssSelector("a.linkSuccessCard")).getDomAttribute("href"));
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='truncate']//a[text()='"+image1+"']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']//a[text()='"+image1+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']//a[text()='"+image2+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']//a[text()='"+image3+"']")).isDisplayed());
    }
}