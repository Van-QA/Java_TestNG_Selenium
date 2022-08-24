package pages;

/*******************************************************************************************
 * Page Factory class Template
 * @author Van Pham
 *******************************************************************************************/

import base.PageBase;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public void selectFirstOption() {
        log.info("Select first option displayed by google search");
        this.waitUntil(searchFirstOption, 10);
        searchFirstOption.click();

    }

    /*******************************************************************************************
     * This POM method will be exposed in test case
     * @param
     *******************************************************************************************/

    public void inputValuesForCalculation(String calValue) {
        this.sendKeys(calValue);
    }

    public void clickOnFirstSearchOption() {

        this.selectFirstOption();

    }
}
