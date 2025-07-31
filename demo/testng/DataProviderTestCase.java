package testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTestCase {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"admin", "admin123"},
                {"user1", "pass1"},
                {"user2", "pass2"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        System.out.println("Username: " + username + ", Password: " + password);
    }
}
