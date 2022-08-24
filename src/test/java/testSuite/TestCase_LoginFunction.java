package testSuite;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(listeners.TestNGListener.class)
@Slf4j
public class TestCase_LoginFunction extends TestBase {

    LoginPage objLogin;

    @BeforeMethod
    public void setup() {
        log.info("Open URL.");
        //This is the URL of the website for testing
        driver.get(dataProps.getProperty("base.url"));
        objLogin = new LoginPage(driver);
    }

    @Ignore
    @Test(priority = 1, description = "Verify that a user cannot login when entering a wrong email address or password")
    public void invalidLogin() {

        log.info("Get input string from properties file and put it into the Email Address box.");
        //Enter a not existing email
        String emailData = dataProps.getProperty("TestCases.InvalidEmail");
        objLogin.enterEmail(emailData);

        //Enter password
        objLogin.enterPassword(dataProps.getProperty("TestCases.ValidPassword"));

        //Then click “Login”
        objLogin.clickLoginBtn();

        //Expected result: The system displays an error message: “The inputted email or password is not correct.”
        String errorEmailMsg = objLogin.getErrorEmailLbl();
        Assert.assertEquals(errorEmailMsg, dataProps.getProperty("TestCases.InvalidLoginMsg"), "Error email lbl: " + errorEmailMsg);
    }

    @Ignore
    @Test(priority = 2, description = "Verify that a user can login successfully")
    public void loginSuccessfully() {

        log.info("Get input string from properties file and put it into the Email Address box.");
        // Enter an existing email
        String emailData = dataProps.getProperty("TestCases.ValidEmail");
        objLogin.enterEmail(emailData);

        // Enter password
        objLogin.enterPassword(dataProps.getProperty("TestCases.ValidPassword"));

        // Then click “Login”
        objLogin.clickLoginBtn();

        // Expected result:  The page Welcome will display and the login success message displays: “You have logged-in successfully!”.
        String loginLoginSuccessMsg = objLogin.getLoginSuccessLbl();
        Assert.assertEquals(loginLoginSuccessMsg, dataProps.getProperty("TestCases.ValidLoginMsg"), "Error email lbl: " + loginLoginSuccessMsg);

    }

}
