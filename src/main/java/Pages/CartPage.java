package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends PageBase{

    By items = By.className("cart_item");
    By removeBtn = By.className("cart_button");
    By checkoutBtn = By.id("checkout");
    By errorMsg = By.className("error-message-container");
    By successMsg = By.className("complete-header");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By firstNameInput = By.id("first-name");
    By lastNameInput = By.id("last-name");
    By postalCodeInput = By.id("postal-code");
    public CartPage(WebDriver driver) {
        super(driver);
    }



    public int numberOfItemsInCart(){
        List<WebElement> cartItems = driver.findElements(items);
        return cartItems.size();
    }

    public void removeFirstItem(){
        WebElement removeButton = driver.findElement(removeBtn);
        removeButton.click();
    }

    public void removeMultipleItem(){
        List<WebElement> removeButtons = driver.findElements(removeBtn);
        removeButtons.get(0).click();
        removeButtons.get(1).click();
    }

    public void clickOnCheckoutBtn(){
        WebElement checkoutButton = driver.findElement(checkoutBtn);
        checkoutButton.click();
    }

    public void clickOnContinueBtn(){
        WebElement continueButton = driver.findElement(continueBtn);
        continueButton.click();
    }

    public void clickOnFinishBtn(){
        driver.findElement(finishBtn).click();
    }

    public WebElement getErrorMsg(){
        WebElement errorMessage = driver.findElement(errorMsg);
        return errorMessage;
    }

    public WebElement getSuccessMsg(){
        WebElement successMessage = driver.findElement(successMsg);
        return successMessage;
    }



    public void fillInformation(String firstName, String lastName, String postaCode){
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postaCode);
    }
}
