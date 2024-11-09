package tests;

import io.cucumber.java.an.Allora;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Pages.LoginPage;
import Pages.InventoryPage;
import utils.DataProviderUtility;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void setup() {
        driver.get("https://www.saucedemo.com/");
    }


    @Description("This test attempts to log into the website using a username and a password. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/")
    @Epic("Web interface - Chrome")
    @Feature("Login")
    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtility.class)
    public void loginTest(Map<String, String> data) {
        long start = System.currentTimeMillis();
        loginPage.userLogin(data.get("username"), data.get(("password")));

        if ("valid".equals(data.get("status"))) {
            Allure.step("Check if inventory page is opened.");
            // Assert on successful login
            loginPage.softAssert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Failed Valid User");
            // Measure the time taken to reach the inventory page
            inventoryPage.waitForInventoryPageLoad();
            long end = System.currentTimeMillis();
            long loadTime = end - start;

            // Define an acceptable load time threshold (e.g., 5000 milliseconds)
            long acceptableLoadTime = 7000;
            Allure.step("Check if the loading time is less than " + acceptableLoadTime);
            Assert.assertTrue(loadTime < acceptableLoadTime, "Performance glitch user has excessive load time: " + loadTime + "ms");
            Allure.step("Check if all items are aligned left.");
            Assert.assertTrue(inventoryPage.areAllItemHeadersAlignedLeft(), "Not all item headers are aligned to the left as expected.");
            if ("problem_user".equals(data.get("expectedResult"))) {
                Allure.step("Check if all items images are not unique.");
                Assert.assertFalse(inventoryPage.areAllItemImagesUnique(), "Some item images are duplicated.");
            }
            else {
                Allure.step("Check if all items images are unique.");
                Assert.assertTrue(inventoryPage.areAllItemImagesUnique(), "Some item images are duplicated.");
            }

        } else {
            // Check for error message
            String actualError = loginPage.getErrorMessage();
            Allure.step("Check if the given username and password are invalid.");
            loginPage.softAssert.assertTrue(actualError.contains(data.get("expectedResult")), "Failed Invalid User");
        }
        loginPage.softAssert.assertAll();
    }


}
