//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class PageBase {
    public WebDriver driver;
    public SoftAssert softAssert;
    By menuBar = By.id("react-burger-menu-btn");
    By logoutBtn = By.id("logout_sidebar_link");
    By loginBtn = By.id("login-button");
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.softAssert = new SoftAssert();
    }

    public void clickOnMenuBarBtn(){
        driver.findElement(menuBar).click();
    }

    public void clickOnLogoutBtn(){
        driver.findElement(logoutBtn).click();
    }

    public WebElement getLoginBtn(){
        WebElement loginButton = driver.findElement(loginBtn);
        return loginButton;

    }


}
