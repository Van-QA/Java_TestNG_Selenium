package testSuite;

import api.MemberApi;
import base.BaseTest;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(listeners.TestNGListener.class)
@Feature("All Members")
@Slf4j
public class TestCase_AllMembers extends BaseTest {

    HomePage homePage;

    @BeforeSuite
    public void setupHomePage() {
        log.info("Init Home Page.");
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void openURL() {
        log.info("Open Home tab.");
        homePage.openDefaultURL();
    }

    @Test(priority = 1, description = "Verify total member displayed correctly")
    public void totalMemberDisplay() {
        Assert.assertTrue(homePage.verifyTotalMembersDisplayed(new MemberApi().getTotalMember()));
    }

}
