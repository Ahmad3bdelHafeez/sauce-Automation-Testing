package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoadTest extends TestBase{
    @BeforeMethod
    public void setup() {
        driver.get("https://www.saucedemo.com");
        Allure.step("User login with valid credentials => performance_glitch_user.");
        loginPage.userLogin("performance_glitch_user", "secret_sauce");
    }

    @Description("This test attempts Load Test. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html/cart.html")
    @Epic("Web interface - Chrome")
    @Feature("Load Test")
    @Test
    public void checkoutWithoutItemsTest() {
        Allure.step("Click on Menu bar button.");
        pageBase.clickOnMenuBarBtn();


        int numUsers = 10;
        Allure.step("Try to login with " + numUsers + "people and make actions.");
        long totalDuration = 0;

        for (int i = 0; i < numUsers; i++) {
            long startTime = System.currentTimeMillis();
            simulateUserActions();
            long endTime = System.currentTimeMillis();

            totalDuration += (endTime - startTime);

            Allure.step("User " + (i + 1) + " actions took: " + (endTime - startTime) + " milliseconds");
            System.out.println("User " + (i + 1) + " actions took: " + (endTime - startTime) + " milliseconds");
        }

        long averageDuration = totalDuration / numUsers;
        Allure.step("Average duration for " + numUsers + " users: " + averageDuration + " milliseconds");
        System.out.println("Average duration for " + numUsers + " users: " + averageDuration + " milliseconds");

    }


    private void simulateUserActions() {
        setup();
        
        // Add to Cart
        Allure.step("Adding any product to the cart.");
        inventoryPage.clickOnFirstProductAddButton();

        Allure.step("Click on the Cart button.");
        inventoryPage.clickOnCartBtn();

        Allure.step("Click on the Checkout button.");
        cartPage.clickOnCheckoutBtn();
    }
}
