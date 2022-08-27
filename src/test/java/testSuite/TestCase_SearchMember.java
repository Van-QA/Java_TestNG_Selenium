package testSuite;

import base.BaseTest;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchMemberPage;
import utilities.RandomUtils;

@Listeners(listeners.TestNGListener.class)
@Feature("Search Members")
@Slf4j
public class TestCase_SearchMember extends BaseTest {

    SearchMemberPage searchMemberPage;

    @BeforeSuite
    public void setupSearchPage() {
        log.info("Init Search Member Page.");
        searchMemberPage = new SearchMemberPage(driver);
    }

    @BeforeMethod
    public void openURL() {
        log.info("Open Search Members tab.");
        searchMemberPage.openDefaultURL();
    }

    @Test(priority = 2, description = "Verify searching member with string")
    public void searchMemberWithCorrectInfo() {
        log.info("Get member info and perform search.");

        String searchData = dataProps.getProperty("TestCase_1.searchString_1");
        searchMemberPage.searchMembers(searchData);

        log.info("Assert returned record included the search data");
        Assert.assertTrue(searchMemberPage.verifyTableRowContainSearchValue(searchData), "Verified all rows data contained search value");
    }

    @Test(priority = 2, description = "Verify searching member with random info")
    public void searchMemberWithRandomInfo() {
        log.info("Get random string and perform search");
        searchMemberPage.verifyPlaceholderText();
        searchMemberPage.searchMembers(RandomUtils.getRandomAlphaNumericString(17));

        log.info("Assert no result returned");
        Assert.assertTrue(searchMemberPage.verifyNoTableRowDisplayed(), "No table row displayed");
    }
}
