package excercise;

import com.beust.ah.A;
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
import java.util.Objects;

public class DefaultCheckBoxRadioButtonTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Kendo_UI_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(3000);

        By dualZone = By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::span//input");

        if (!driver.findElement(dualZone).isSelected()) {
            driver.findElement(dualZone).click();
        }
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(dualZone).isSelected());

        if (driver.findElement(dualZone).isSelected()) {
            driver.findElement(dualZone).click();
        }
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(dualZone).isSelected());
    }

    @Test
    public void TC02_Kendo_UI_Radio() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(3000);

        By petrolRadio = By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::span//input");

        if (!driver.findElement(petrolRadio).isSelected()) {
            driver.findElement(petrolRadio).click();
        }
        Thread.sleep(3000);
    }

    @Test
    public void TC03_Angular() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        Thread.sleep(3000);

        By summer = By.xpath("//label[contains(text(),'Summer')]/preceding-sibling::div//input");
        driver.findElement(summer).click();

        if (!driver.findElement(summer).isSelected()) {
            driver.findElement(summer).click();
        }
        Thread.sleep(3000);
    }

    @Test
    public void TC04_Angular() throws InterruptedException {
        driver.get("https://material.angular.io/components/checkbox/examples");
        Thread.sleep(3000);

        By checked = By.xpath("//label[contains(text(),'Checked')]/preceding-sibling::div//input");
        By indeterminate = By.xpath("//label[contains(text(),'Indeterminate')]/preceding-sibling::div//input");

        driver.findElement(checked).click();
        driver.findElement(indeterminate).click();

        Assert.assertTrue(driver.findElement(checked).isSelected());
        Assert.assertTrue(driver.findElement(indeterminate).isSelected());

        driver.findElement(checked).click();
        driver.findElement(indeterminate).click();

        Assert.assertFalse(driver.findElement(checked).isSelected());
        Assert.assertFalse(driver.findElement(indeterminate).isSelected());
    }

    @Test
    public void TC05_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");
        Thread.sleep(3000);

        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@class='form-input-wide']//input[@type='checkbox']"));

        for (WebElement checkbox : allCheckboxes) {
            checkbox.click();
        }
        Thread.sleep(3000);

        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        for (WebElement checkbox : allCheckboxes) {
            checkbox.click();
        }
        Thread.sleep(3000);

        for (WebElement checkbox : allCheckboxes) {
            if (Objects.equals(checkbox.getDomAttribute("value"), "Fainting Spells") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
        Thread.sleep(3000);

        for (WebElement checkbox : allCheckboxes) {
            if (Objects.equals(checkbox.getDomAttribute("value"), "Fainting Spells")) {
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}