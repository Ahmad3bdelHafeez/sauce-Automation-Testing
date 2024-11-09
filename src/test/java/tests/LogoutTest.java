package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase {
    @BeforeMethod
    public void setup() {
        driver.get("https://www.saucedemo.com");
        Allure.step("User login with valid credentials.");
        loginPage.userLogin("standard_user", "secret_sauce");
    }

    @Description("This test attempts to Logout. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html/cart.html")
    @Epic("Web interface - Chrome")
    @Feature("Logout")
    @Test
    public void checkoutWithoutItemsTest() {
        Allure.step("Click on Menu bar button.");
        pageBase.clickOnMenuBarBtn();

        Allure.step("Click on Logout button.");
        pageBase.clickOnLogoutBtn();

        Allure.step("check the current URL");
        softAssert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl(), "User should be redirected to the login page after logout");

        Allure.step("Check if the Login Button is displayed.");
        softAssert.assertTrue(pageBase.getLoginBtn().isDisplayed(), "Login button should be displayed on the login page");
    }

}
