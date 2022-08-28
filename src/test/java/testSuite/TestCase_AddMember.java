package testSuite;

import api.MemberApi;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import objects.Member;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddMemberPage;
import utilities.DataUtils;

@Listeners(listeners.TestNGListener.class)
@Feature("Add Members")
@Slf4j
public class TestCase_AddMember extends BaseTest {

    AddMemberPage addMemberPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite
    public void setupAddMemberPage() {
        log.info("Init Add Member Page.");
        addMemberPage = new AddMemberPage(driver);
    }

    @BeforeMethod
    public void openURL() {
        log.info("Open View Members tab.");
        addMemberPage.openDefaultURL();
    }

    @Description("Verify adding new member with valid info successfully")
    @Test(priority = 1, description = "Open Add Member URL", dataProvider = "getMembers", dataProviderClass = DataUtils.class)
    public void addMemberSuccessfully(Member member) {
        log.info("Get input string from data provider and put it into the add member fields");
        addMemberPage.enterMemberInfo(member);
        addMemberPage.clickSubmitButton();

        log.info("Assert new member added successfully via confirmation pop up");
        String toastMsg = addMemberPage.getToastSuccessMsg();
        Assert.assertTrue(toastMsg.contains("Member has been added"), "New member added successfully: " + member);
        Member newMember = addMemberPage.getMemberById(toastMsg);
        Assert.assertTrue(newMember.equals(member), "New member added successfully");
    }

    @Test(priority = 2, description = "Verify adding new member with random invalid info")
    public void addMemberWithRandomInfo() {
        log.info("Get random string and put it into the search box.");
        addMemberPage.clickSubmitButton();
        softAssert.assertTrue(addMemberPage.verifyMandatoryWarningMgs(), "Verified mandatory messages");
        addMemberPage.enterRandomMemberInfo();
        softAssert.assertTrue(addMemberPage.verifyMandatoryWarningStt(), "Verified mandatory validation");
        addMemberPage.clickSubmitButton();

        log.info("Assert new member NOT added successfully");
        Assert.assertTrue(addMemberPage.verifyToastSuccessMsgNotDisplay(), "Toast success message does not displayed");
        softAssert.assertAll();
    }
}
