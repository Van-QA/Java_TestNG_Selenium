package testSuite;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login_page;


public class TestCase_LoginFunction extends TestBase {

    Login_page obj_login;

    @BeforeMethod
    public void setup() {
        log.info("Open URL.");
        //This is the URL of the website for testing
        driver.get(data.getProperty("base.url"));
        obj_login = new Login_page(driver);
    }

    @Test(priority = 1, description = "Verify that a user cannot login when entering a wrong email address or password")
    public void invalid_login() {

        log.info("Get input string from properties file and put it into the Email Address box.");
        //Enter a not existing email
        String email_data = data.getProperty("TestCases.InvalidEmail");
        obj_login.enter_email(email_data);

        //Enter password
        obj_login.enter_password(data.getProperty("TestCases.ValidPassword"));

        //Then click “Login”
        obj_login.click_login_btn();

        //Expected result: The system displays an error message: “The inputted email or password is not correct.”
        String error_email_msg = obj_login.get_error_email_lbl();
        Assert.assertEquals(error_email_msg, data.getProperty("TestCases.InvalidLoginMsg"), "Error email lbl: " + error_email_msg);
    }

    @Test(priority = 2, description = "Verify that a user can login successfully")
    public void login_successfully() {

        log.info("Get input string from properties file and put it into the Email Address box.");
        //Enter an existing email
        String email_data = data.getProperty("TestCases.ValidEmail");
        obj_login.enter_email(email_data);

        //Enter password
        obj_login.enter_password(data.getProperty("TestCases.ValidPassword"));

        //Then click “Login”
        obj_login.click_login_btn();

        //Expected result:  The page Welcome will display and the login success message displays: “You have logged-in successfully!”.
        String success_msg = obj_login.get_login_success_lbl();
        Assert.assertEquals(success_msg, data.getProperty("TestCases.ValidLoginMsg"), "Error email lbl: " + success_msg);

    }

}
