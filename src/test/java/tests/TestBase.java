package tests;

import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.PageBase;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.DataProviderUtility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public PageBase pageBase;
    public SoftAssert softAssert;

    public TestBase() {
    }

    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        pageBase = new PageBase(driver);
        softAssert = new SoftAssert();
    }


    @AfterMethod
    public void closeBrowser() throws IOException{
        takeScreenshots();
        driver.quit();
    }

//    @AfterStep
//    public void attachScreenshotPNG() throws IOException{
//        takeScreenshots();
//    }
//    public void takeScreenshots(Scenario scenario) throws IOException {
//        byte[] screenShot = (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//        scenario.attach(screenShot, "image/png", "SCREENSHOT");
//    }
    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public byte[] takeScreenshots() throws IOException {
        return (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
