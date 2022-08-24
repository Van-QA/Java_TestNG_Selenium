package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.PageBase;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
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
     *******************************************************************************************/


    public void sendKeys(WebElement element, String keysToSend) {
        log.info("Enter text: " + keysToSend);
        element.sendKeys(keysToSend);

    }

    public String getMgsLbl(WebElement element) {
        log.info("Select first option displayed by Email");
        return element.getText();

    }

    public void clickBtn(WebElement element) {
        log.info("Click button");
        element.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void enterEmail(String email) {

        this.sendKeys(emailBox, email);

    }

    public void enterPassword(String password) {

        this.sendKeys(passwordBox, password);

    }

    public void clickLoginBtn() {

        this.clickBtn(loginBtn);

    }

    public String getErrorEmailLbl() {

        return this.getMgsLbl(emailErrlbl);

    }

    public String getErrorPasswordLbl() {

        return this.getMgsLbl(passwordErrlbl);

    }

    public String getLoginSuccessLbl() {

        return this.getMgsLbl(loginSuccessLbl);

    }
}
