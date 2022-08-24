package testSuite;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SynGoogleSearch;

@Listeners(listeners.TestNGListener.class)
@Slf4j
public class TestCase_GoogleSearch extends TestBase {

    SynGoogleSearch objGoogleSearch;


    @Test(priority = 1, description = "Open Google Search URL")
    public void openUrl() {

        log.info("Open Google Search URL.");
        driver.get(dataProps.getProperty("base.url"));

        log.info("Get input string from properties file and put it into the search box.");
        objGoogleSearch = new SynGoogleSearch(driver);
        String searchData = dataProps.getProperty("TestCase_1.searchString_1");
        objGoogleSearch.searchByFirstOption(searchData);

        log.info("Assert actual searched string with expected string from properties file.");
        String firstOption = objGoogleSearch.getFirstOption();

        Assert.assertTrue(firstOption.contains(searchData), "First option: " + firstOption + " contains: " + searchData);
    }

    @Ignore
    @Test(priority = 2, description = "Click on first search option")
    public void clickFirstSearchOption() {
        log.info("Click on first search option");
        objGoogleSearch.clickOnFirstSearchOption();
    }
}
