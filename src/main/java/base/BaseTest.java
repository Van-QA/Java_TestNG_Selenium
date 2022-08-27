package base;

import config.DriverConfig;
import constants.GlobalVars;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.Objects;
import java.util.Properties;

@Slf4j
public abstract class BaseTest extends DriverConfig {
    // resource paths
    private static final String dataFilePath = GlobalVars.getDataFilePath();

    public static final Properties dataProps =  Objects.requireNonNull(loadProperty(dataFilePath));
    public static WebDriverWait wait = null;
    public static WebDriver driver;


    /**
     * initializes driver
     */
    @BeforeSuite
    public void setup() {
        log.info("initialized driver");
        driver = initializeDriver();
    }

    public void openDefaultURL(BasePage basePage) {
        basePage.openDefaultURL();
    }
    /**
     * testNG data provider for tests
     *
     * @return - Object[][]
     */
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"Selenium", "Selenium"}};
    }

    /**
     * launches home page URL
     */
//    @BeforeMethod
    public void launchURL() {
        String url = getBaseUrl();
        log.info("Launching URL: " + url);
        driver.get(url);
    }

    /**
     * closes driver
     */
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            log.debug("End of Test. Closing driver" + System.lineSeparator());
            driver.close();
        }
    }

}
