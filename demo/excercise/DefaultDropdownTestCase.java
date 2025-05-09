package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDropdownTestCase {
    WebDriver driver;
    Select select;
    ChromeOptions options = new ChromeOptions();

    @BeforeClass
    public void beforeClass() {

        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Integer> contentSettings = new HashMap<>();

        contentSettings.put("geolocation", 1);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_Rode() {
        driver.get("https://rode.com/en/support/where-to-buy");

        select = new Select(driver.findElement(By.xpath("//select[@id='country']")));
        if (select.isMultiple()) {
            System.out.println("Dropdown is multiple");
        } else {
            System.out.println("Dropdown is not multiple");
        }

        select.selectByVisibleText("Vietnam");
        driver.findElement(By.xpath("//input[@id='map_search_query']")).sendKeys("HO CHI MINH");

        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);

        for (WebElement dealer : dealers) {
            System.out.println(dealer.getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}