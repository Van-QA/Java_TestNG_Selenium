package listeners;

import base.BaseTest;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.DataUtils;

@Slf4j
public class TestNGListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("==== onStart method ==== " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", BaseTest.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("==== onFinish method ==== " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("==== Started test method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("==== Succeed test method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("==== Failed test method " + getTestMethodName(iTestResult));
        Object testClass = iTestResult.getInstance();

        WebDriver driver = BaseTest.driver;
//        new Common(driver).takeScreenshot(iTestResult.getName());

        // Allure ScreenShotRobot and SaveTestLog
        System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        saveScreenshotPNG(driver);

        // Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed on " + DataUtils.formatTimeSDF());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("==== Skipped test method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
