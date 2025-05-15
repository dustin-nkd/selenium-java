package excercise;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InterActionsPartTwoTestCase {
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
    public void TC01_ClickAndHold() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));
        actions.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).perform();
        Thread.sleep(3000);

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 4);
    }

    @Test
    public void TC02_ClickAndHold_Random() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<String> actualNumbers = new ArrayList<String>();
        actualNumbers.add("1");
        actualNumbers.add("3");
        actualNumbers.add("6");
        actualNumbers.add("11");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));

        for (String number : actualNumbers) {
            actions.keyDown(Keys.CONTROL).perform();
            actions.click(numbers.get(Integer.parseInt(number) - 1)).perform();
            actions.keyUp(Keys.CONTROL).perform();
        }

        Thread.sleep(3000);

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        List<String> expectedNumbers = new ArrayList<String>();

        for (WebElement number : numberSelected) {
            expectedNumbers.add(number.getText());
        }

        Assert.assertEquals(actualNumbers, expectedNumbers);
    }

    @Test
    public void TC03_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        actions.doubleClick(driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC04_ContextClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Thread.sleep(3000);

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        actions.moveToElement(driver.findElement(By.cssSelector("ul.context-menu-list li.context-menu-icon-quit"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(driver.findElement(By.cssSelector("ul.context-menu-list li.context-menu-icon-quit"))).perform();
        Thread.sleep(3000);

        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}