package testSuite;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Calculator;


public class TestCase_Calculator extends TestBase {

    Calculator objCal;

    @Test(priority = 1, description = "Open Google Search URL")
    public void openUrl() {
        log.info("Open Google Search URL.");
        driver.get(data.getProperty("base.url"));

        log.info("Get input string from properties file and put it into the search box.");
        objCal = new Calculator(driver);
        String searchData = data.getProperty("TestCase_1.searchString_1");
        objCal.inputValuesForCalculation(searchData);


        log.info("Assert actual value from calculator with expected value from properties file.");
        String calculatorValue = objCal.getCalculatorValue();

        Assert.assertTrue(calculatorValue.contains(searchData), "First option: " + calculatorValue + " contains: " + searchData);
    }

    @Test(priority = 2, description = "Click on first search option")
    public void clickFirstSearchOption() {
        log.info("Click on first search option");
        objCal.clickOnFirstSearchOption();
    }

}
