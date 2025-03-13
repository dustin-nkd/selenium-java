package environments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RelativeTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC_Relative() {
        driver.get("https://live.techpanda.org/index.php/catalogsearch/advanced/");
        WebElement element = driver.findElement(RelativeLocator.with(By.tagName("input"))
                .toLeftOf(By.name("price[to]"))
                .below(By.name("sku"))
                .above(By.name("tax_class_id[]")));

        element.sendKeys("hello world");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
