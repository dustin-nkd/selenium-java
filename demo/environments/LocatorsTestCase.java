package environments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsTestCase {
    WebDriver driver;

    @BeforeClass
    @Test(priority = 1)
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test(priority = 2)
    public void TC_Get_URL() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test(priority = 3)
    public void TC_ID() {
        driver.findElement(By.id("txtFirstname"));
    }

    @Test(priority = 4)
    public void TC_Class() {
        driver.findElement(By.className("form-control"));
    }

    @Test(priority = 5)
    public void TC_Name() {
        driver.findElement(By.name("txtFirstname"));
    }

    @Test(priority = 6)
    public void TC_TagName() {
        driver.findElements(By.tagName("input"));
    }

    @Test(priority = 7)
    public void TC_Link_Text() {
        driver.findElements(By.linkText("Hướng dẫn sử dụng"));
    }

    @Test(priority = 8)
    public void TC_Partial_Link_Text() {
        driver.findElement(By.partialLinkText("Giới thiệu"));
    }

    @Test(priority = 9)
    public void TC_Css() {
        driver.findElement(By.cssSelector("input[id='txtFirstname']"));
    }

    @Test(priority = 10)
    public void TC_XPath() {
        driver.findElement(By.xpath("//input[@id='txtFirstname']"));
    }

    @AfterClass
    @Test(priority = 11)
    public void afterClass() {
        driver.quit();
    }
}