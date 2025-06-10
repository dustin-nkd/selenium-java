package excercise;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaScriptExecutorTestCase {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        jsExecutor = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @Test
    public void TC01_TechPanda() {
        navigateToUrlByJS("http://live.techpanda.org/");

        Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
        Assert.assertEquals(executeForBrowser("return document.URL"), "https://live.techpanda.org/");

        clickToElementByJS("//a[normalize-space()='Mobile']");
        clickToElementByJS("//a[contains(text(),'Samsung Galaxy')]/parent::h2/following-sibling::div[@class='actions']//button");

        Assert.assertEquals(getElement("//span[normalize-space()='Samsung Galaxy was added to your shopping cart.']").getText(), "Samsung Galaxy was added to your shopping cart.");

        clickToElementByJS("//a[normalize-space()='Customer Service']");
        Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");

        scrollToElementOnDown("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']", "thankyou@email.com");

        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertEquals(getElement("//li[@class='success-msg']").getText(), "Thank you for your subscription.");

        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals(executeForBrowser("return document.domain;"), "www.facebook.com");
    }

    @Test
    public void TC02_HTML5() throws InterruptedException {
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

        clickToElementByJS("//input[@name='submit-btn']");
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
        Thread.sleep(3000);

        sendkeyToElementByJS("//input[@id='fname']", "Dustin");
        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
        Thread.sleep(3000);

        sendkeyToElementByJS("//input[@id='pass']", "123");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
        Thread.sleep(3000);

        sendkeyToElementByJS("//input[@id='em']", "email");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please include an '@' in the email address. 'email' is missing an '@'.");
        Thread.sleep(3000);

        sendkeyToElementByJS("//input[@id='em']", "thankyou@email.com");
        Assert.assertEquals(getElementValidationMessage("//form[@action='/']//fieldset//ul//li//select"), "Please select an item in the list.");
        Thread.sleep(3000);
    }
}