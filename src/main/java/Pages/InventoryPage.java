package Pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryPage extends PageBase {
    By inventoryContainer = By.id("inventory_container");
    By items = By.className("inventory_item");
    By itemHeaders = By.className("inventory_item_name");
    By itemImages = By.cssSelector("img[class='inventory_item_img']");
    By ddFilter = By.className("product_sort_container");
    By addToCartBtn = By.className("btn_inventory");
    By itemPrice = By.className("inventory_item_price");
    By cartBadgeNumber = By.className("shopping_cart_badge");
    By firstItem = By.xpath("(//button[contains(@class,'btn_inventory')])[1]");
    By itemBtn = By.className("btn_inventory");
    By cartBtn = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Wait until the inventory container is visible
    public void waitForInventoryPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set the timeout as needed
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
    }

    // Check if all item headers are aligned to the left
    public boolean areAllItemHeadersAlignedLeft() {
        List<WebElement> headers = driver.findElements(itemHeaders);

        for (WebElement header : headers) {
            String textAlign = header.getCssValue("text-align");
            String marginLeft = header.getCssValue("margin-left");

            // Check if the header is aligned to the left
            if (!"left".equals(textAlign) && !"0px".equals(marginLeft)) {
                return false;  // Return false if any header is not aligned left
            }
        }
        return true; // Return true if all headers are aligned left
    }

    // Check if all item images are unique
    public boolean areAllItemImagesUnique() {
        List<WebElement> images = driver.findElements(itemImages);
        Set<String> imageSources = new HashSet<>();

        for (WebElement image : images) {

            String src = image.getAttribute("src");
            System.out.println(src);
            if (!imageSources.add(src)) { // Returns false if src already exists in Set
                return false;
            }
        }
        return true;
    }

    public int numberOfItems(){
        List<WebElement> products = driver.findElements(items);
        return products.size();
    }

    public boolean areAllItemsHasName(){
        // Get all products are displayed on the inventory page
        List<WebElement> products = driver.findElements(items);

        for (WebElement product : products) {
            // Verify that the product name is displayed
            WebElement productName = product.findElement(itemHeaders);
            if (!productName.isDisplayed()) return false;
        }
        return true;
    }

    public boolean areAllItemsHasPrice(){
        // Get all products are displayed on the inventory page
        List<WebElement> products = driver.findElements(items);

        for (WebElement product : products) {
            // Verify that the product price is displayed

            WebElement productPrice = product.findElement(itemHeaders);
            if (!productPrice.isDisplayed()) return false;
        }
        return true;
    }

    public boolean areAllItemsHasAddToCartBtn(){
        // Get all products are displayed on the inventory page
        List<WebElement> products = driver.findElements(items);

        for (WebElement product : products) {
            // Verify the "Add to Cart" button is displayed
            WebElement addToCartButton = product.findElement(addToCartBtn);
            if (!addToCartButton.isDisplayed()) return false;
        }
        return true;
    }

    public Boolean areAllItemsSortedByName(String filter){
        Allure.step("Select from the filter drop down menu '" + filter + "'.");
        driver.findElement(ddFilter).sendKeys(filter);
        List<String> productNames = driver.findElements(itemHeaders)
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedNames = new ArrayList<>(productNames);
        if (filter.equals("Name (A to Z)")) {
            sortedNames.sort(Collections.reverseOrder());
        }
        else if (filter.equals("Name (Z to A)")) {
            Collections.sort(sortedNames);
        }
        return sortedNames.equals(productNames);
    }

    public Boolean areAllItemsSortedByPrice(String filter){
        Allure.step("Select from the filter drop down menu '" + filter + "'.");
        driver.findElement(ddFilter).sendKeys(filter);
        List<Double> productPrices = driver.findElements(itemPrice)
                .stream().map(price -> Double.parseDouble(price.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new ArrayList<>(productPrices);
        if (filter.equals("Price (low to high)")) {
            Collections.sort(sortedPrices);
        }
        else if (filter.equals("Price (high to low)")) {
            sortedPrices.sort(Collections.reverseOrder());
        }
        return sortedPrices.equals(productPrices);
    }

    public void clickOnFirstProductAddButton(){
        WebElement firstProductAddButton = driver.findElement(firstItem);
        firstProductAddButton.click();
    }

    public void clickOnMultipleItemsAddButton(){
        List<WebElement> addButtons = driver.findElements(itemBtn);
        addButtons.get(0).click();
        addButtons.get(1).click();
        addButtons.get(2).click();
    }

    public void clickOnCartBtn(){
        driver.findElement(cartBtn).click();
    }

    public String numberOfItemsInCart(){
        WebElement cartBadge = driver.findElement(cartBadgeNumber);
        return cartBadge.getText();
    }
}

