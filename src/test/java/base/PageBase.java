package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class PageBase {

    /**
     * The Driver.
     */
    protected WebDriver driver = null;
    protected Actions compositeAction = null;
    public Logger log = TestBase.log;


    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void waitUntil(WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
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
        if (compositeAction == null) {
            return new Actions(driver);
        } else
            return compositeAction;
    }

    public void send_keys(String Keys_to_send) {
        Actions ac = getActions();
        ac.sendKeys(Keys_to_send).perform();

    }

}
