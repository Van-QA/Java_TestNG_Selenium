package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.BasePage;
import base.BaseTest;
import config.DriverConfig;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import objects.Member;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.RandomUtils;

import java.util.*;

@Slf4j
public class AddMemberPage extends BasePage {

    static final String pageURL = DriverConfig.getBaseUrl() + Objects.requireNonNull(BaseTest.dataProps).getProperty("addMember.urlPath");

    public AddMemberPage(WebDriver driver) {
        super(driver, pageURL);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    // Web Element for new Member info Box
    @FindBy(id = "first_name")
    WebElement firstNameBox;

    @FindBy(id = "last_name")
    WebElement lastNameBox;

    @FindBy(id = "title")
    WebElement titleBox;

    @FindBy(id = "company")
    WebElement companyBox;

    @FindBy(id = "phone")
    WebElement phoneBox;

    @FindBy(id = "website")
    WebElement websiteBox;

    @FindBy(id = "email")
    WebElement emailBox;

    @FindBy(id = "remember")
    WebElement tCBox;

    @FindBy(id = "username")
    WebElement warning;

    @FindBy(id = "toast-success")
    List<WebElement> toastSuccessMsg;

    @FindBy(css = "button[type=\"submit\"]")
    WebElement submitBtn;

    /*******************************************************************************************
     * All Methods for performing actions
     *******************************************************************************************/


    public void sendKeys(WebElement ele, String keysToSend) {

        log.info("Enter text to search: " + keysToSend);
        ele.sendKeys(keysToSend);

    }

    public String getCalculatorValue() {
        log.info("Select first option displayed by google search");

        List<WebElement> scripts = findElements(By.tagName("script"));

        String answer = "";
        List<String> focusTexts = new ArrayList<>();
        for (WebElement script : scripts) {

            String focusText = script.getAttribute("innerHTML");
            focusTexts.add(focusText);
            if (focusText.contains("canvas.strokeText")) {
                answer = focusText.substring(focusText.indexOf("Answer"), focusText.indexOf("',"));
                break;

            }
        }
        return answer;
    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     *******************************************************************************************/

    @Step("Click submit button")
    public void clickSubmitButton() {
        submitBtn.click();
    }

    @Step("Enter member info")
    public void enterRandomMemberInfo() {
        Map<WebElement, String> memberInfo = new HashMap<WebElement, String>() {{
            put(firstNameBox, RandomUtils.getRandomAlphaNumericString(7));
            put(lastNameBox, RandomUtils.getRandomAlphaNumericString(7));
            put(titleBox, RandomUtils.getRandomAlphaNumericString(7));
            put(companyBox, RandomUtils.getRandomAlphaNumericString(7));
            put(phoneBox, RandomUtils.getRandomAlphaNumericString(7));
            put(websiteBox, RandomUtils.getRandomAlphaNumericString(7));
            put(emailBox, RandomUtils.getRandomAlphaNumericString(7));
        }};

        enterMultipleTbx(memberInfo);
        tCBox.click();
    }

    @Step("Enter member info")
    public void enterMemberInfo(Member member) {
        Map<WebElement, String> memberInfo = new HashMap<WebElement, String>() {{
            put(firstNameBox, member.getFirstName());
            put(lastNameBox, member.getLastName());
            put(titleBox, member.getTitle());
            put(companyBox, member.getCompany());
            put(phoneBox, member.getPhoneNumber());
            put(websiteBox, member.getWebsite());
            put(emailBox, member.getEmail());
        }};

        enterMultipleTbx(memberInfo);
        tCBox.click();
    }

    public String getToastSuccessMsg() {
        return toastSuccessMsg.get(0).getText();
    }

    public boolean verifyToastSuccessMsgNotDisplay() {
        return verifyNoElementDisplayed(toastSuccessMsg);
    }

    public boolean verifyMandatoryWarningMgs() {
        return isValidationMessageEqual(firstNameBox, "Please fill in this field.")
                && isValidationMessageEqual(lastNameBox, "Please fill in this field.")
                && isValidationMessageEqual(titleBox, "Please fill in this field.")
                && isValidationMessageEqual(companyBox, "Please fill in this field.")
                && isValidationMessageEqual(phoneBox, "")
                && isValidationMessageEqual(websiteBox, "")
                && isValidationMessageEqual(emailBox, "Please fill in this field.");
    }

    public boolean verifyMandatoryWarningStt() {
        return isValidField(firstNameBox, true)
                && isValidField(lastNameBox, true)
                && isValidField(titleBox, true)
                && isValidField(companyBox, true)
                && isValidField(phoneBox, false)
                && isValidField(websiteBox, false)
                && isValidField(emailBox, false);
    }
}
