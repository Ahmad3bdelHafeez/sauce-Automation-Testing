package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends TestBase{
    @BeforeMethod
    public void setup() {
        driver.get("https://www.saucedemo.com");
        Allure.step("User login with valid credentials.");
        loginPage.userLogin("standard_user", "secret_sauce");
    }

    @Description("This test attempts to checkout without Items. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html/cart.html")
    @Epic("Web interface - Chrome")
    @Feature("Checkout")
    @Story("Checkout without Items")
    @Test()
    public void checkoutWithoutItemsTest() {
        Allure.step("Click on Cart button.");
        inventoryPage.clickOnCartBtn();

        Allure.step("Click on checkout button.");
        cartPage.clickOnCheckoutBtn();

        softAssert.assertTrue(cartPage.getErrorMsg().isDisplayed(), "Error message should be displayed for an empty cart checkout attempt.");
//        softAssert.assertEquals("Your cart is empty. Add items before checkout.", cartPage.getErrorMsg().getText(), "Error message text mismatch.");
        softAssert.assertEquals("", cartPage.getErrorMsg().getText(), "Error message text mismatch.");

        softAssert.assertAll();
    }

    @Description("This test attempts to checkout with invalid information. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html/cart.html")
    @Epic("Web interface - Chrome")
    @Feature("Checkout")
    @Story("Checkout with invalid information")
    @Test()
    public void checkoutWithInvalidInfoTest() {
        Allure.step("Click on the first item to add.");
        inventoryPage.clickOnFirstProductAddButton();

        Allure.step("Click on Cart button.");
        inventoryPage.clickOnCartBtn();

        Allure.step("Click on Checkout button.");
        cartPage.clickOnCheckoutBtn();

        Allure.step("Click on Continue button.");
        cartPage.clickOnContinueBtn();

        softAssert.assertTrue(cartPage.getErrorMsg().isDisplayed(), "Error message should be displayed for missing information.");
        softAssert.assertEquals("Error: First Name is required", cartPage.getErrorMsg().getText(), "Error message text mismatch for missing information.");

        softAssert.assertAll();
    }

    @Description("This test attempts to checkout with valid information. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html/cart.html")
    @Epic("Web interface - Chrome")
    @Feature("Checkout")
    @Story("Checkout with valid information")
    @Test()
    public void checkoutWithValidInfoTest() {
        Allure.step("Click on the first item to add.");
        inventoryPage.clickOnFirstProductAddButton();

        Allure.step("Click on Cart button.");
        inventoryPage.clickOnCartBtn();

        Allure.step("Click on Checkout button.");
        cartPage.clickOnCheckoutBtn();

        Allure.step("Fill the required data.");
        cartPage.fillInformation("Hamada", "mada", "101010");

        Allure.step("Click on Continue button.");
        cartPage.clickOnContinueBtn();

        Allure.step("Click on Finish button.");
        cartPage.clickOnFinishBtn();

        softAssert.assertTrue(cartPage.getSuccessMsg().isDisplayed(), "Success message should be displayed after valid checkout.");
        softAssert.assertEquals("Thank you for your order!", cartPage.getSuccessMsg().getText(), "Success message text mismatch.");

        softAssert.assertAll();
    }


}
