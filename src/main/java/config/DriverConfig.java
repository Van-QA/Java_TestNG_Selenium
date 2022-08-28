package config;

import constants.GlobalVars;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DriverConfig {
    private WebDriver driver;
    static final Properties configProps = Objects.requireNonNull(loadProperty(GlobalVars.getConfigProp()));

    /**
     * initialize driver
     *
     * @return - WebDriver
     */
    public WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                log.error("Invalid browser/browser config doesnt exist");
        }
        log.debug("Driver initialized");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    /**
     * load properties file
     */
    public static Properties loadProperty(String propsFilepath) {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(propsFilepath);
            log.debug("Loading properties");
            props.load(fis);
            return props;
        } catch (IOException e) {
            log.error("Exception in loadProperty method: " + e.getMessage());
        }
        return null;
    }

    /**
     * get url value from properties
     *
     * @return - String
     */
    public static String getBaseUrl() {
        return configProps.getProperty("url");
    }

    public static String getAPIUrl() {
        return configProps.getProperty("api");
    }

    public static String getEnv() {
        return configProps.getProperty("env");
    }

    /**
     * get browser value from properties or form config from cmd
     *
     * @return - String
     */
    public String getBrowserFromConfig(String xmlBrowserRunner) {
        String browser = getEnvConfig("Browser");
        // 1st priority is -DBrowser value from cmd
        if (browser != null) {
            return browser;
        }
        // 2nd priority is browser value from xml testsuite config
        if (xmlBrowserRunner != null) {
            return xmlBrowserRunner;
        }
        // 3rd priority is default browsers value from config.properties files
        browser = configProps.getProperty("browser");
        System.out.println("Using default browser config from property file: " + browser);
        return browser;
    }

    /**
     * Quit Driver.
     */
    public void quitDriver() {
        driver.quit();
        driver = null;
        log.info("Closing Browser.");
    }

    public String getEnvConfig(String propertyName) {
        String propValue = System.getProperty(propertyName);
        if (propValue == null) {
            log.info("No specific config for: " + propertyName);
        } else {
            System.out.println(propertyName + ": " + propValue);
        }
        return propValue;
    }
}
