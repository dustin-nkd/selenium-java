package testng;

import org.testng.annotations.*;

public class AnnotationTestCase {
    @BeforeSuite
    public void beforeSuit() {
        System.out.println("before suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("after suite");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("after class");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }

    @Test
    public void TC01() {
        System.out.println("test case 01");
    }

    @Test
    public void TC02() {
        System.out.println("test case 02");
    }
}