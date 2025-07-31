package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AAAPatternTestCase {
    WebDriver driver;

    // Arrange
    @BeforeClass
    public void initial() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass(alwaysRun = true)
    public void clean() {
        driver.quit();
    }

    // Action - Assert
    @Test(groups = {"smoke"})
    public void TestCase() {
        // write your code here
    }
}
