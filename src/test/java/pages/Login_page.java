package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Login_page extends PageBase {

    public Login_page(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    WebDriver driver;
    // Web Element for Email Box (6)
    @FindBy(id = "txtEmail")
    WebElement emailBox;

    // Web Element for Email Error (7)
    @FindBy(id = "lblEmailErr")
    WebElement emailErrlbl;

    // Web Element for Password Box (8)
    @FindBy(id = "txtPassword")
    WebElement passwordBox;

    // Web Element for Password Error (9)
    @FindBy(id = "lblPasswordErr")
    WebElement passwordErrlbl;

    // Web Element for Login Btn (10)
    @FindBy(id = "btnLogin")
    WebElement loginBtn;

    // Web Element for Login Successfully label (11)
    @FindBy(id = "lblLoggedinSuccessfully")
    WebElement loginSuccessLbl;


    /*******************************************************************************************
     * All Methods for performing actions
     * @return
     *******************************************************************************************/


    public void send_keys(WebElement element, String Keys_to_send) {
        log.info("Enter text: " + Keys_to_send);
        element.sendKeys(Keys_to_send);

    }

    public String get_mgs_lbl(WebElement element) {
        log.info("Select first option displayed by Email");
        return element.getText();

    }

    public void click_btn(WebElement element) {
        log.info("Click button");
        element.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void enter_email(String email) {

        this.send_keys(emailBox, email);

    }

    public void enter_password(String password) {

        this.send_keys(passwordBox, password);

    }

    public void click_login_btn() {

        this.click_btn(loginBtn);

    }

    public String get_error_email_lbl() {

        return this.get_mgs_lbl(emailErrlbl);

    }

    public String get_error_password_lbl() {

        return this.get_mgs_lbl(passwordErrlbl);

    }

    public String get_login_success_lbl() {

        return this.get_mgs_lbl(loginSuccessLbl);

    }
}
