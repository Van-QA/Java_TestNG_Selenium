package base;

import config.DriverConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public abstract class TestBase extends DriverConfig {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + File.separator + "resources" + File.separator;
    public static Properties env = null;
    public static Properties config = null;
    public static Properties data = null;
    private static boolean isInitialized = false;
    public static WebDriverWait wait = null;
    private static final FileInputStream ip = null;
    public static WebDriver driver;
    public static Logger log;

    protected TestBase() {
        if (!isInitialized) {
            initLogs();
            initConfig();
            isInitialized = true;
        }
    }

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

//    /**
//     * launches home page URL
//     */
//    @BeforeMethod
//    public void launchURL() {
//        String url = getUrl("url");
//        log.info("Launching URL: " + url);
//        driver.get(url);
//    }

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

    /**
     * Quit Driver.
     */
    public static void quitDriver() {

        driver.quit();
        driver = null;
        log.info("Closing Browser.");

    }

    /**
     * Initialize Logger.
     */
    private static void initLogs() {
        if (log == null) {
            // Initialize Log4j logs
            DOMConfigurator.configure(RESOURCES_PATH + "log4j.xml");
            log = Logger.getLogger("MyLogger");
            log.info("Logger is initialized..");
        }
    }


    private static void initConfig() {
        if (config == null) {
            try {
//              env = initializePropertiesFile("env");
                config = initializePropertiesFile("config");
                data = initializePropertiesFile("data");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Properties initializePropertiesFile(String name) throws IOException {
        //initialize properties file
        Properties propertiesList = new Properties();
        String fileName = name + ".properties";
        FileInputStream ip = new FileInputStream(RESOURCES_PATH + fileName);
        propertiesList.load(ip);
//        log.info(name + " file initialized for environment = " + env.getProperty("Environment"));
        return propertiesList;
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
     * Assert Actual and Expected Strings
     */

    protected void assertStrings(String actual, String expected) {


        try {
            Assert.assertEquals(actual, expected);
            log.info("Actual string: [ " + actual + " ] does match with Expected string: [ " + expected + " ]");

        } catch (AssertionError e) {
            log.error("Actual string: [ " + actual + " ] does not match with Expected string: [ " + expected + " ]");
            Assert.fail();

        }

    }

}
