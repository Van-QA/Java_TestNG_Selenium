package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.BasePage;
import config.DriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Slf4j
public class HomePage extends BasePage {
    static final String pageURL = DriverConfig.getBaseUrl();

    public HomePage(WebDriver driver) {
        super(driver, pageURL);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/
    @FindBy(css = "table tbody tr")
    List<WebElement> tableRow;

    /*******************************************************************************************
     * This POM method will be exposed in test case
     *******************************************************************************************/

    public boolean verifyTotalMembersDisplayed(int totalMember) {
        return tableRow.size() == totalMember;
    }
}
