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
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC_Get_URL() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_ID() {
        driver.findElement(By.id("txtFirstname"));
    }

    @Test
    public void TC_Class() {
        driver.findElements(By.className("form-control"));
    }

    @Test
    public void TC_Name() {
        driver.findElement(By.name("txtFirstname"));
    }

    @Test
    public void TC_TagName() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_Link_Text() {
        driver.findElements(By.linkText("Hướng dẫn sử dụng"));
    }

    @Test
    public void TC_Partial_Link_Text() {
        driver.findElement(By.partialLinkText("Giới thiệu"));
    }

    @Test
    public void TC_Css() {
        driver.findElements(By.cssSelector("input[id='txtFirstname']"));
    }

    @Test
    public void TC_XPath() {
        driver.findElement(By.xpath("//input[@id='txtFirstname']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}