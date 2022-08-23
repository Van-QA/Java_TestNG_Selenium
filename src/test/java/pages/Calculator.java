package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Calculator extends PageBase {

    public Calculator(WebDriver driver) {
        super(driver);
    }

    /*******************************************************************************************
     * All WebElements are identified by @FindBy annotation
     *******************************************************************************************/

    WebDriver driver;
    // Web Element for Google Search Box
//    @FindBy(id = "canvas")
    @FindBy(xpath = "//*[@id=\"canvas\"]")
    WebElement calculatorCanvas;


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

    public String get_calculator_value() {
        log.info("Select first option displayed by google search");

        List<WebElement> scripts = findElements(By.tagName("script"));

        String answer = "";
        List<String> focusTexts = new ArrayList<String>();
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

    public void select_first_option() {
        log.info("Select first option displayed by google search");
        this.waitUntil(searchFirstOption, 10);
        searchFirstOption.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void input_values_for_calculation(String cal_value) {
        this.send_keys(cal_value);
    }

    public void click_on_first_search_option() {

        this.select_first_option();

    }
}
