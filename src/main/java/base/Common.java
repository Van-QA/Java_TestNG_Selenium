package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class Common {
    private static JavascriptExecutor js;
	private final WebDriverWait wait;

    public Common(WebDriver driver) {
		js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    /**
     * helper method to wait for element
     *
     * @param ele - WebElement
     * @return - boolean
     */
    public boolean waitForElement(WebElement ele) {
        return wait.until(ExpectedConditions.elementToBeClickable(ele)) != null;
    }

    /**
     * element click using js executor
     *
     * @param ele - WebElement
     */
    public void jsClick(WebElement ele) {
        js.executeScript("arguments[0].click();", ele);

    }

    /**
     * enter text using js executor
     *
     */
    public void jsEnterText(WebElement ele, String text) {
        js.executeScript("arguments[0].setAttribute('value', '" + text + "')", ele);
    }

    public boolean jsValidField(WebElement ele) {
        return (Boolean) js.executeScript("return arguments[0].validity.valid;", ele);
    }


}
