package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CustomDropdownTestCase {
    WebDriver driver;

    private void selectCustomDropdown(String parentLocator, String childLocator, String content) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(3000);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        for (WebElement item : allItems) {
            if (item.getText().equals(content)) {
                item.click();
                break;
            }
        }
    }

    private void selectEditableCustomDropdown(String parentLocator, String childLocator, String content) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(content);
        Thread.sleep(3000);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        for (WebElement item : allItems) {
            if (item.getText().equals(content)) {
                item.click();
                break;
            }
        }
    }

    private void selectHuaweiDropdown(String openDropdown, String parentLocator, String childLocator, String content) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(openDropdown))).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(content);
        Thread.sleep(3000);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        for (WebElement item : allItems) {
            if (item.getText().equals(content)) {
                item.click();
                break;
            }
        }
    }


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");

        selectCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Prof.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");

        selectCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Mr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
    }

    @Test
    public void TC02_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectCustomDropdown("div.dropdown", "div span.text", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectCustomDropdown("div.dropdown", "div span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

        selectCustomDropdown("div.dropdown", "div span.text", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
    }

    @Test
    public void TC03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectCustomDropdown(".dropdown-toggle", "ul.dropdown-menu li a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li.dropdown-toggle")).getText(), "Third Option");

        selectCustomDropdown(".dropdown-toggle", "ul.dropdown-menu li a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li.dropdown-toggle")).getText(), "First Option");

        selectCustomDropdown(".dropdown-toggle", "ul.dropdown-menu li a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li.dropdown-toggle")).getText(), "Second Option");
    }

    @Test
    public void TC04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectEditableCustomDropdown("div.search input", "div.menu span", "Benin");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");

        selectEditableCustomDropdown("div.search input", "div.menu span", "Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

        selectEditableCustomDropdown("div.search input", "div.menu span", "Belarus");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belarus");
    }

    @Test
    public void TC05_HuaweiDropdown() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        selectHuaweiDropdown("div[ht='input_emailregister_dropdown']", "input[ht='input_emailregister_search']", "ul.hwid-alpla-list span", "South Korea");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown'] span.hwid-select-text")).getText(), "South Korea");

        selectHuaweiDropdown("div[ht='input_emailregister_dropdown']", "input[ht='input_emailregister_search']", "ul.hwid-alpla-list span", "Fiji");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown'] span.hwid-select-text")).getText(), "Fiji");

        selectHuaweiDropdown("div[ht='input_emailregister_dropdown']", "input[ht='input_emailregister_search']", "ul.hwid-alpla-list span", "Nepal");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[ht='input_emailregister_dropdown'] span.hwid-select-text")).getText(), "Nepal");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}