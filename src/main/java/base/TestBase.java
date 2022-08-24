package base;

import config.DriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Properties;

@Slf4j
public abstract class TestBase extends DriverConfig {
    // resource paths
    private static final String dataFilepath = "/resources/data.properties";

    public static final String RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "resources" + File.separator;
    public Properties dataProps = loadProperty(dataFilepath);
    public static WebDriverWait wait = null;
    public static WebDriver driver;


    /**
     * initializes driver
     */
    @BeforeTest
    public void setup() {
        driver = initializeDriver();
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
    //@BeforeMethod
    public void launchURL() {
        String url = getUrl("url");
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
