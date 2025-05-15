package excercise;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class InterActionsPartThreeTestCase {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        actions = new Actions(driver);
    }

    @Test
    public void TC01_DragAndDrop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        By sourceCircle = By.id("draggable");
        By targetCircle = By.id("droptarget");

        actions.dragAndDrop(driver.findElement(sourceCircle), driver.findElement(targetCircle)).perform();

        Assert.assertEquals(driver.findElement(targetCircle).getText(), "You did great!");
        Assert.assertEquals(Color.fromString(driver.findElement(targetCircle).getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
