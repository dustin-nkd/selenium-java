package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AnnotationTestCase {
    @BeforeClass
    public void initialBrowser() {

    }

    @AfterClass
    public void cleanBrowser() {

    }

    @Test
    public void TestCase() {

    }
}