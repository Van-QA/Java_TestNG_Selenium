package listeners;

import base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.*;

public class TestNGListener implements ITestListener {
    private WebDriver driver;
    CommonFunctions functions;
    private Logger log = TestBase.log;

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
//        try {
//            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
//                    .get(result.getInstance());// get driver instance
//        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//            log.error("Exception in TestNG Listener" + e.getMessage());
//        }

        functions = new CommonFunctions(TestBase.driver);

        functions.takeScreenshot(result.getName());// take screenshot with test name

    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

}
