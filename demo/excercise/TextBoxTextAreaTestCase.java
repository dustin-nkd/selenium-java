package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class TextBoxTextAreaTestCase {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        String firstName = "Donald";
        String lastName = "Trump";
        String fullName = firstName + " " + lastName;
        String emailAddress = firstName + lastName + new Random().nextInt() + "@gmail.com";
        String password = "123456789";

        driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformation = driver.findElement(By.xpath("//h3[normalize-space()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));

        driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("#review_field")).sendKeys("Best shop\nBest phone");
        driver.findElement(By.cssSelector("#summary_field")).sendKeys("Good");

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Your review has been accepted for moderation.");
    }

    @Test
    public void TC_OrangeHRM() throws InterruptedException {

        String firstName = "Dustin";
        String lastName = "James";
        String userName = firstName + new Random().nextInt();
        String password = "Lak123123";
        String passPort = "123-456-789-133";
        String comment = "Best Driver\n Best ADC";

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[contains(., 'Login')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a//span[contains(.,'PIM')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(.,'Add Employee')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);

        String employeeID = driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div//input")).getDomProperty("value");

        driver.findElement(By.xpath("//p[contains(.,'Create Login Details')]/following-sibling::div")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//label[contains(.,'Username')]/parent::div/following-sibling::div//input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div//input")).getDomProperty("value"), employeeID);

        driver.findElement(By.xpath("//a[contains(.,'Immigration')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//h6[contains(.,'Assigned Immigration Records')]/following-sibling::button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).sendKeys(passPort);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).sendKeys(comment);
        driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("button i.bi-pencil-fill")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value"), passPort);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).getDomProperty("value"), comment);

        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(.,'Logout')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(., 'Login')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[contains(.,'My Info')]")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(.,'Employee Id')]/parent::div/following-sibling::div//input")).getDomProperty("value"), employeeID);

        driver.findElement(By.xpath("//a[contains(.,'Immigration')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button i.bi-pencil-fill")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).getDomProperty("value"), passPort);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).getDomProperty("value"), comment);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}