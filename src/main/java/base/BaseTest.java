package base;

import com.google.common.collect.ImmutableMap;
import config.DriverConfig;
import constants.GlobalVars;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;
import java.util.Properties;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Slf4j
public abstract class BaseTest extends DriverConfig {
    public static final Properties dataProps = Objects.requireNonNull(loadProperty(GlobalVars.getDataFilePath()));
    public static WebDriver driver;

    /**
     * initializes driver
     */
    @Parameters("browser")
    @BeforeSuite
    public void setup(@Optional String browser) {
        log.info("initialized driver");
        browser = getBrowserFromConfig(browser);
        driver = initializeDriver(browser);
        setAllureEnvironment(browser);

    }

    void setAllureEnvironment(String browser) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browser)
                        .put("Environment", DriverConfig.getEnv())
                        .put("URL", DriverConfig.getBaseUrl())
                        .build(), GlobalVars.getAllureResultsPath());
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
