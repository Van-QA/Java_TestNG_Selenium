package testSuite;

import api.MemberApi;
import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import objects.Member;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ViewMemberPage;
import utilities.RandomUtils;

@Listeners(listeners.TestNGListener.class)
@Slf4j
public class TestCase_ViewMember extends BaseTest {

    ViewMemberPage viewMemberPage;
    MemberApi memberApi = new MemberApi();

    @BeforeSuite
    public void setupViewPage() {
        log.info("Init View Member Page.");
        viewMemberPage = new ViewMemberPage(driver);
    }

    @BeforeMethod
    public void openURL() {
        log.info("Open View Member tab.");
        viewMemberPage.openDefaultURL();
    }

    @Test(priority = 2, description = "Verify viewing new member with valid id")
    public void viewMemberByValidId() {
        log.info("Get member info and perform view.");

        String validId = dataProps.getProperty("TestCase_1.viewId");
        viewMemberPage.searchMemberById(validId);
        int memberId = Integer.parseInt(validId);

        Member memberInfo = memberApi.getMemberById(memberId);
        log.info("Assert returned record matching data from API");
        Assert.assertTrue(viewMemberPage.verifyMemberInfo(memberInfo, memberId));
    }

    @Test(priority = 2, description = "Verify viewing new member with random info")
    public void viewMemberByRandomId() {
        log.info("Get random string and perform view");
        Assert.assertTrue(viewMemberPage.verifyPlaceholderText());
        viewMemberPage.searchMemberById(RandomUtils.getRandomNumberAsString(17));

        log.info("No result returned");
        Assert.assertTrue(viewMemberPage.verifyNoResultDisplayed(), "No table row displayed");
    }
}
