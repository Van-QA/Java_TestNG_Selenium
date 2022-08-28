package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class BasePage {
    public static final int WAIT = 5;
    /**
     * The Driver.
     */
    protected final WebDriver driver;
    protected String pageURL = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.pageURL = url;
        PageFactory.initElements(driver, this);
    }

    public void openDefaultURL() {
        driver.get(pageURL);
    }

    /**
     * Wait
     */
    public static void wait(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilClickable(WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(this.driver, timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Sample action code
     */

    public List<WebElement> findElements(By by) {
        return this.driver.findElements(by);
    }

    public void pressEnter() {
        try {
            Thread.sleep(5000L);
            Actions ac = getActions();
            ac.sendKeys(Keys.ENTER).perform();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    private Actions getActions() {
        return new Actions(driver);
    }

    public void sendKeys(String keysToSend) {
        Actions ac = getActions();
        ac.sendKeys(keysToSend).perform();

    }

    protected void enterMultipleTbx(Map<WebElement, String> memberInfo) {
        for (Map.Entry<WebElement, String> entry : memberInfo.entrySet()) {
            enterTxtNotNull(entry.getKey(), entry.getValue());
        }
    }

    protected void enterTxtNotNull(WebElement element, String value) {
        if (value != null) {
            element.sendKeys(value);
        }
    }

    protected boolean isValidationMessageEqual(WebElement ele, String value) {
        return ele.getAttribute("validationMessage").equals(value);
    }

    protected boolean isValidField(WebElement ele, boolean isValid) {
        return new Common(driver).jsValidField(ele) == isValid;
    }

    protected boolean isPlaceHolderEqual(WebElement ele, String value) {
        return ele.getAttribute("placeholder").equals(value);
    }

    public boolean verifyNoElementDisplayed(List<WebElement> elementList) {
        return elementList.size() == 0;
    }

}
