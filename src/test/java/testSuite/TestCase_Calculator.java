package testSuite;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Calculator;

@Listeners(listeners.TestNGListener.class)
@Slf4j
public class TestCase_Calculator extends TestBase {

    Calculator objCal;

    @Test(priority = 1, description = "Open Google Search URL")
    public void openUrl() {
        log.info("Open Google Search URL.");
        driver.get(dataProps.getProperty("base.url"));

        log.info("Get input string from properties file and put it into the search box.");
        objCal = new Calculator(driver);
        String searchData = dataProps.getProperty("TestCase_1.searchString_1");
        objCal.inputValuesForCalculation(searchData);


        log.info("Assert actual value from calculator with expected value from properties file.");
        String calculatorValue = objCal.getCalculatorValue();

        Assert.assertTrue(searchData.contains(searchData), "First option: " + calculatorValue + " contains: " + searchData);
    }

    @Test(priority = 2, description = "Click on first search option")
    public void clickFirstSearchOption() {
        log.info("Click on first search option");
        objCal.clickOnFirstSearchOption();
    }

}
