package testSuite;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Calculator;


public class TestCase_Calculator extends TestBase {

    Calculator obj_cal;


    @Test(priority = 1, description = "Open Google Search URL")
    public void open_url() {

        log.info("Open Google Search URL.");
        driver.get(data.getProperty("base.url"));

        log.info("Get input string from properties file and put it into the search box.");
        obj_cal = new Calculator(driver);
        String search_data = data.getProperty("TestCase_1.searchString_1");
        obj_cal.input_values_for_calculation("123+123=");


        log.info("Assert actual value from calculator with expected value from properties file.");
        String calculator_value = obj_cal.get_calculator_value();

        Assert.assertTrue(calculator_value.contains(search_data), "First option: " + calculator_value + " contains: " + search_data);
    }

    @Test(priority = 2, description = "Click on first search option")
    public void click_first_search_option() {

        log.info("Click on first search option");
        obj_cal.click_on_first_search_option();

    }

}
