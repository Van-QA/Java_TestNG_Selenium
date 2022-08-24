package testSuite;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SynGoogleSearch;

@Listeners(listeners.TestNGListener.class)
public class TestCase_GoogleSearch extends TestBase {

    SynGoogleSearch objGoogleSearch;


    @Test(priority = 1, description = "Open Google Search URL")
    public void openUrl() {

        log.info("Open Google Search URL.");
        driver.get(data.getProperty("base.url"));

        log.info("Get input string from properties file and put it into the search box.");
        objGoogleSearch = new SynGoogleSearch(driver);
        String searchData = data.getProperty("TestCase_1.searchString_1");
        objGoogleSearch.searchByFirstOption(searchData);

        log.info("Assert actual searched string with expected string from properties file.");
        String firstOption = objGoogleSearch.getFirstOption();

        Assert.assertTrue(firstOption.contains(searchData+123), "First option: " + firstOption + " contains: " + searchData);
    }

    @Ignore
    @Test(priority = 2, description = "Click on first search option")
    public void clickFirstSearchOption() {
        log.info("Click on first search option");
        objGoogleSearch.clickOnFirstSearchOption();
    }
}
