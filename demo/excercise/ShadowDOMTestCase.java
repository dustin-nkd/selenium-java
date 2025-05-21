package excercise;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShadowDOMTestCase {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom");
        Thread.sleep(3000);

        WebElement firstShadowHost = driver.findElement(By.xpath("//div[@id='shadow_host']"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();
        WebElement firstPara = firstShadowRoot.findElement(By.cssSelector(".info"));

        Assert.assertTrue(firstPara.isDisplayed());

        WebElement checkbox = firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']"));

        Assert.assertFalse(checkbox.isSelected());

        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();
        WebElement secondPara = secondShadowRoot.findElement(By.cssSelector("#nested_shadow_content>div"));

        Assert.assertTrue(secondPara.isDisplayed());
    }

    @Test
    public void TC02_Book_Pwakit() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);

        WebElement firstShadowHost = driver.findElement(By.xpath("//book-app[@apptitle='BOOKS']"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        firstShadowRoot.findElement(By.cssSelector("#input")).sendKeys("Harry Potter");
        Thread.sleep(3000);
        firstShadowRoot.findElement(By.cssSelector("#input")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("book-explore[class='_page']"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();

        List<WebElement> thirdShadowHostList = secondShadowRoot.findElements(By.cssSelector("ul[class='books']>li>book-item"));

        for (WebElement thirdShadowHost : thirdShadowHostList) {
            SearchContext thirdShadowRoot = thirdShadowHost.getShadowRoot();
            System.out.println(thirdShadowRoot.findElement(By.cssSelector("div[class='info'] h2[class='title']")).getText());
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}