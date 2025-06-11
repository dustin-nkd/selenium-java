package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class UploadFilesTestCase {
    WebDriver driver;

    String image1 = "image1.jpg";
    String image2 = "image2.jpg";
    String image3 = "image3.jpg";
    String uploadFilesPath = System.getProperty("user.dir") + "\\demo\\image\\";
    String image1Path = uploadFilesPath + image1;
    String image2Path = uploadFilesPath + image2;
    String image3Path = uploadFilesPath + image3;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    @Test
    public void TC01_Upload_Files() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        Thread.sleep(3000);

        By addFilesButton = By.xpath("//span[contains(@class,'fileinput-button')]//input");
//        driver.findElement(addFilesButton).sendKeys(image1Path);
//        driver.findElement(addFilesButton).sendKeys(image2Path);
//        driver.findElement(addFilesButton).sendKeys(image3Path);

        driver.findElement(addFilesButton).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);

        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(), '" +image1+"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(), '" +image2+"')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and contains(text(), '" +image3+"')]")).isDisplayed());

        List<WebElement> uploadButtons = driver.findElements(By.xpath("//table//button[contains(.,'Start')]"));

        for (WebElement button : uploadButtons) {
            button.click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+image1+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+image2+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='"+image3+"']")).isDisplayed());

        Thread.sleep(3000);
    }
}