package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

@Slf4j
public class SynGoogleSearch extends PageBase {

    public SynGoogleSearch(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    WebDriver driver;
    // Web Element for Google Search Box
    @FindBy(name = "q")
    WebElement searchBox;

    // Web Element for Google Search first option
    @FindBy(xpath = "//ul[@role=\"listbox\"]//div[@role=\"option\"][1]//span")
    WebElement searchFirstOption;

    /*******************************************************************************************
     * All Methods for performing actions
     *******************************************************************************************/


    public void sendKeys(WebElement ele, String keysToSend) {

        log.info("Enter text to search: " + keysToSend);
        ele.sendKeys(keysToSend);

    }

    public String getFirstOption() {
        log.info("Select first option displayed by google search");
        return searchFirstOption.getText();

    }

    public void selectFirstOption() {
        log.info("Select first option displayed by google search");
        this.waitUntil(searchFirstOption, 10);
        searchFirstOption.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void searchByFirstOption(String textToSearch) {

        this.sendKeys(searchBox, textToSearch);

    }

    public void clickOnFirstSearchOption() {

        this.selectFirstOption();

    }
}
