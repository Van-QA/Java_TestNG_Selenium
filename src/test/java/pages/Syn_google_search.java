package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;


public class Syn_google_search extends PageBase {

    public Syn_google_search(WebDriver driver) {
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
     * @return
     *******************************************************************************************/


    public void send_keys(WebElement ele, String Keys_to_send) {

        log.info("Enter text to search: " + Keys_to_send);
        ele.sendKeys(Keys_to_send);

    }

    public String get_first_option() {
        log.info("Select first option displayed by google search");
        return searchFirstOption.getText();

    }

    public void select_first_option() {
        log.info("Select first option displayed by google search");
        this.waitUntil(searchFirstOption, 10);
        searchFirstOption.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void search_by_first_option(String text_to_search) {

        this.send_keys(searchBox, text_to_search);

    }

    public void click_on_first_search_option() {

        this.select_first_option();

    }
}
