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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

@Slf4j
public class SearchMemberPage extends BasePage {
    static final String pageURL = DriverConfig.getBaseUrl() + Objects.requireNonNull(BaseTest.dataProps).getProperty("searchMember.urlPath");
    public static final String SEARCH_PLACEHOLDER = "Query String";

    public SearchMemberPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public SearchMemberPage(WebDriver driver) {
        super(driver,  pageURL);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    // Web Element for Default Search Box
    @FindBy(id = "default-search")
    WebElement searchBox;

    @FindBy(css = "button[type=\"submit\"]")
    WebElement searchBtn;

    // Web Element for Google Search first option
    @FindBy(css = "table tbody tr")
    List<WebElement> searchTableRows;

    /*******************************************************************************************
     * This POM method will be exposed in test case
     *******************************************************************************************/

    @Step("Search member info")
    public void searchMembers(String textToSearch) {
        searchBox.sendKeys(textToSearch);
        searchBtn.click();
    }

    @Step("Verify each row contains search value")
    // only verify each row contains search value, which field contains the search value will be verified via API, in a different story.
    public boolean verifyTableRowContainSearchValue(String value) {
        for (WebElement row : searchTableRows) {
            if (!row.getText().toLowerCase().contains(value)) {
                return false;
            }
        }
        return true;
    }

    @Step("Verify no table row displayed")
    public boolean verifyNoTableRowDisplayed() {
        return verifyNoElementDisplayed(searchTableRows);
    }

    @Step("Verify placeholder text of search field")
    public boolean verifyPlaceholderText() {
        return isPlaceHolderEqual(searchBox, SEARCH_PLACEHOLDER);
    }
}
