package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DriverConfig {
    private WebDriver driver;
    Properties configProps;

    // resource paths
    private final String configFilepath = "/resources/config.properties";

    /**
     * initialize driver
     *
     * @return - WebDriver
     */
    public WebDriver initializeDriver() {
        configProps = loadProperty(configFilepath);
        log.debug("Running on browser: " + getBrowser());
        switch (getBrowser()) {
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
     * @param propsFilepath
     * @return
     */
    public Properties loadProperty(String propsFilepath) {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + propsFilepath);
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
     * @param url - key can be google-url, bing-url etc.
     * @return - String
     */
    public String getUrl(String url) {
        return configProps.getProperty(url);
    }

    /**
     * get browser value from properties
     *
     * @return - String
     */
    public String getBrowser() {
        return configProps.getProperty("browser");
    }

    /**
     * Read Properties.
     */
    protected static String getPropertyValue(String PropertyKey) {
        Properties props = null;
        FileInputStream fin = null;
        String PropertyValue = null;

        try {
            File f = new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + "env.properties");
            fin = new FileInputStream(f);
            props = new Properties();
            props.load(fin);
            PropertyValue = props.getProperty(PropertyKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return PropertyValue;
    }

    /**
     * Quit Driver.
     */
    public void quitDriver() {
        driver.quit();
        driver = null;
        log.info("Closing Browser.");
    }


}
