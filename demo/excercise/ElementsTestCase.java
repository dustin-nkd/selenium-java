package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ElementsTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()) {
            driver.findElement(By.xpath("//input[@id='mail']")).click();
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
            driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[normalize-space()='Name: User5']")).isDisplayed()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    @Test
    public void TC02_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//textarea[@id='edu']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//select[@id='job2']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//input[@id='development']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }

        if (driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled()) {
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }
    }

    @Test
    public void TC03_isSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//input[@id='under_18']")).click();
        driver.findElement(By.xpath("//input[@id='java']")).click();


        if (driver.findElement(By.xpath("//input[@id='under_18']")).isSelected()) {
            System.out.println("Element is selected");
        } else {
            System.out.println("Element is not selected");
        }

        if (driver.findElement(By.xpath("//input[@id='java']")).isSelected()) {
            System.out.println("Element is selected");
        } else {
            System.out.println("Element is not selected");
        }
    }

    @Test
    public void TC04_Register() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thankyou@gmail.com");
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("123");

        Assert.assertTrue(driver.findElement(By.cssSelector(".number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("abc");

        Assert.assertTrue(driver.findElement(By.cssSelector(".lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());

        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("ABC");

        Assert.assertTrue(driver.findElement(By.cssSelector(".uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector(".username-check.completed")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}