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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

@Slf4j
public class ViewMemberPage extends BasePage {
    static final String pageURL = DriverConfig.getBaseUrl() + Objects.requireNonNull(BaseTest.dataProps).getProperty("viewMember.urlPath");

    public ViewMemberPage(WebDriver driver) {
        super(driver, pageURL);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    // Web Element for Default View Box

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

    @FindBy(id = "default-search")
    WebElement viewBox;

    @FindBy(css = "button[type=\"submit\"]")
    WebElement viewBtn;

    @FindBy(css = "#__next h1")
    WebElement resultMsg;


    /*******************************************************************************************
     * All Methods for performing actions
     *******************************************************************************************/


    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    @Step("View member info")
    public void searchMemberById(String id) {
        viewBox.sendKeys(id);
        viewBtn.click();
    }

    @Step("Verify placeholder text of search field")
    public boolean verifyPlaceholderText() {
        return isPlaceHolderEqual(viewBox, "ID");
    }

    @Step("Verify member info matching with expected")
    public boolean verifyMemberInfo(Member expectedInfo, int Id) {
        return expectedInfo.equals(Id, firstNameBox.getText(), lastNameBox.getText(), titleBox.getText(), companyBox.getText(),
                phoneBox.getText(), websiteBox.getText(), emailBox.getText());
    }

    @Step("Verify no result Msg display")
    public boolean verifyNoResultDisplayed() {
        return resultMsg.getText().equals("Nothing to display...");
    }
}
