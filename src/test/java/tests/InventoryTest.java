package tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DataProviderUtility;

import java.util.Map;

public class InventoryTest extends TestBase{
    @BeforeMethod
    public void setup() {
        driver.get("https://www.saucedemo.com");
        Allure.step("User login with valid credentials.");
        loginPage.userLogin("standard_user", "secret_sauce");
    }

    @Description("This test attempts to check the product name, price and its Add To Cart button. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Products")
    @Story("View Products")
    @Test()
    public void viewProductsTest() {
        Allure.step("Products are displayed on the page.");
        softAssert.assertTrue(inventoryPage.numberOfItems() > 0, "No products are displayed on the page.");
        Allure.step("Products name are displayed.");
        softAssert.assertTrue(inventoryPage.areAllItemsHasName(), "Product name is not displayed.");
        Allure.step("Products price are displayed.");
        softAssert.assertTrue(inventoryPage.areAllItemsHasPrice(), "Product price is not displayed.");
        Allure.step("Add to Cart button is displayed for a product.");
        softAssert.assertTrue(inventoryPage.areAllItemsHasAddToCartBtn(), "Add to Cart button is missing for a product.");
        Allure.step("Products name are aligned left.");
        softAssert.assertTrue(inventoryPage.areAllItemHeadersAlignedLeft(), "Product name is not aligned left.");
        Allure.step("Products image are unique.");
        softAssert.assertTrue(inventoryPage.areAllItemImagesUnique(), "Product image is not unique.");
        softAssert.assertAll();
    }


    @Description("This test attempts to check all Products are Sorted by Name correctly. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Products")
    @Story("Sort Products by Name")
    @Test
    public void sortProductsByNameTest() {
        // Sort A to Z
        Allure.step("Check if all products are sorted A - Z.");
        softAssert.assertTrue(inventoryPage.areAllItemsSortedByName("Name (A to Z)"), "Products are not sorted A to Z by name.");

        // Sort Z to A
        Allure.step("Check if all products are sorted Z - A.");
        softAssert.assertTrue(inventoryPage.areAllItemsSortedByName("Name (Z to A)"), "Products are not sorted Z to A by name.");

        softAssert.assertAll();
    }

    @Description("This test attempts to check all Products are Sorted by Price correctly. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Products")
    @Story("Sort Products by Price")
    @Test
    public void sortProductsByPriceTest() {
        // Sort low - high
        Allure.step("Check if all products are sorted low - high.");
        softAssert.assertTrue(inventoryPage.areAllItemsSortedByPrice("Price (low to high)"), "Products are not sorted low to high by price.");

        // Sort high - low
        Allure.step("Check if all products are sorted high - low.");
        softAssert.assertTrue(inventoryPage.areAllItemsSortedByPrice("Price (high to low)"), "Products are not sorted high to low by price.");

        softAssert.assertAll();
    }

    @Description("This test attempts to check the cart functionality if added one item. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Add To Cart")
    @Story("Add Single Product To Cart")
    @Test
    public void addSingleProductToCartTest() {
        // Add a single product to the cart
        Allure.step("Click on Add To Cart of the first product.");
        inventoryPage.clickOnFirstProductAddButton();

        // Verify the cart badge reflects one item
        Allure.step("Check the Cart badge reflect one item.");
        softAssert.assertEquals("1", inventoryPage.numberOfItemsInCart(), "Cart badge does not reflect one item.");

        // Verify product is in the cart
        Allure.step("Click on the Cart.");
        inventoryPage.clickOnCartBtn();
        Allure.step("Check Cart contains exactly one item.");
        softAssert.assertEquals(1, cartPage.numberOfItemsInCart(), "Cart does not contain exactly one item.");

        softAssert.assertAll();
    }

    @Description("This test attempts to check the cart functionality if added multiple items. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Add To Cart")
    @Story("Add Multiple Product To Cart")
    @Test
    public void addMultipleProductToCartTest() {
        // Add a single product to the cart
        Allure.step("Click on Add To Cart of multiple products.");
        inventoryPage.clickOnMultipleItemsAddButton();

        // Verify the cart badge reflects 2 items
        Allure.step("Check the Cart badge reflect two items.");
        softAssert.assertEquals("3", inventoryPage.numberOfItemsInCart(), "Cart badge does not reflect two items.");

        // Verify product is in the cart
        Allure.step("Click on the Cart.");
        inventoryPage.clickOnCartBtn();
        Allure.step("Check Cart contains exactly two items.");
        softAssert.assertEquals(3, cartPage.numberOfItemsInCart(), "Cart does not contain exactly two items.");

        softAssert.assertAll();
    }

    @Description("This test attempts to check the cart functionality if removed one item. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Add To Cart")
    @Story("Remove One Product From Cart")
    @Test
    public void removeOneProductFromCartTest() {
        // Add a single product to the cart
        Allure.step("Click on Add To Cart of one product.");
        inventoryPage.clickOnFirstProductAddButton();

        // Verify product is in the cart
        Allure.step("Click on the Cart.");
        inventoryPage.clickOnCartBtn();
        Allure.step("Remove the first item in the cart.");
        cartPage.removeFirstItem();
        Allure.step("Check Cart contains 0 item.");
        softAssert.assertEquals(0, 0, "Cart is not empty after removing the product.");

        softAssert.assertAll();
    }

    @Description("This test attempts to check the cart functionality if removed multiple items. Fails if any error happens.")
    @Link(name = "saucedemo", url = "https://www.saucedemo.com/inventory.html")
    @Epic("Web interface - Chrome")
    @Feature("Add To Cart")
    @Story("Remove Multiple Products From Cart")
    @Test
    public void removeMultipleProductFromCartTest() {
        // Add a single product to the cart
        Allure.step("Click on Add To Cart of multiple products.");
        inventoryPage.clickOnMultipleItemsAddButton();

        Allure.step("Click on the Cart.");
        inventoryPage.clickOnCartBtn();

        Allure.step("Remove multiple items in the cart.");
        cartPage.removeMultipleItem();
        Allure.step("Check Cart contains 0 item.");
        softAssert.assertEquals(1, cartPage.numberOfItemsInCart(), "Cart is not empty after removing the products.");

        softAssert.assertAll();
    }


}
