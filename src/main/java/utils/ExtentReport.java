package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static ExtentReports extend;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath = "target/spark.html";

    public static void initialize(){
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extend = new ExtentReports();
        extend.attachReporter(spark);
    }

    // Start a new test case
    public static void startTest(String testName){
        ExtentTest extentTest = extend.createTest(testName);
        test.set(extentTest);
    }

    // Log actions on elements
    public static void logElementAction(String elementName, String action){
        test.get().info("Action on element ' " + elementName + "': " + action);
    }

    // Log actions
    public static void logAction(String action){
        test.get().info("Action: " + action);
    }

    // Log a passing message
    public static void logPass(String message){
        test.get().pass(message);
    }

    // Log a failing message with a red label
    public static void logFail(String message){
        test.get().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    // Log a warning message with a yellow label
    public static void logWarning(String message){
        test.get().warning(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
    }

    // Log an informational message with a blue label
    public static void logInfo(String message){
        test.get().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
    }

    // Log a screenshot using Base64 string
    public static void logScreenCaptureFromBase64String(String base64String, String message){
        try{
            test.get().addScreenCaptureFromBase64String(base64String, message);
        } catch (Exception e){
            test.get().fail("Failed to add screenshot: " + e.getMessage());
        }
    }

    // Log a code block
    public static void logCodeBlock(String code){
        test.get().pass(MarkupHelper.createLabel(code, ExtentColor.GREY));
    }

}
