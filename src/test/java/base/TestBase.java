package base;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class TestBase {
    public static Properties env = null;
    public static Properties config = null;
    public static Properties data = null;
    private static boolean isInitialized = false;
    public static Logger log = null;
    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    private static final FileInputStream ip = null;

    protected TestBase() {
        if (!isInitialized) {
            initLogs();
            initConfig();
            initDriver();
            isInitialized = true;
        }
    }

    /**
     * Initialize Logger.
     */
    private static void initLogs() {
        if (log == null) {
            // Initialize Log4j logs
            DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "config" + File.separator + "log4j.xml");
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
        String path = System.getProperty("user.dir") + File.separator + "config" + File.separator + fileName;
        FileInputStream ip = new FileInputStream(path);
        propertiesList.load(ip);
//        log.info(name + " file initialized for environment = " + env.getProperty("Environment"));
        return propertiesList;
    }


    /**
     * Initialize Driver.
     */
    private static void initDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("disable-popup-blocking", false);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        driver = getWebdriver(caps);
        String waitTime = "30";
        driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().maximize();

        //Explicit Wait + Expected Conditions
        wait = new WebDriverWait(driver, 120);
    }

    private static WebDriver getWebdriver(DesiredCapabilities caps) {
        String browser = config.getProperty("browser");
        String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
        log.info(browser + " driver is initialized..");
        if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("FF")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "Download");
            profile.setPreference("browser.download.downloadDir", System.getProperty("user.dir") + File.separator + "Download");
            profile.setPreference("browser.download.defaultFolder", System.getProperty("user.dir") + File.separator + "Download");
            profile.setPreference("browser.download.manager.closeWhenDone", true);
            profile.setPreference("pdfjs.disabled", true);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip,text/csv,application/msword,application/excel,application/pdf," +
                    "application/vnd.ms-excel,application/msword,application/unknown,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            caps.setCapability(FirefoxDriver.PROFILE, profile);
            System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
            return new FirefoxDriver(profile);
        }
        if (browser.equals("InternetExplorer") || browser.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
            return new InternetExplorerDriver(caps);
        }
        if (browser.equals("GoogleChrome") || browser.equalsIgnoreCase("CHROME")) {

            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            // To remove message "You are using an unsupported command-line flag: --ignore-certificate-errors.
            // Stability and security will suffer."
            // Add an argument 'test-type'
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("--headless", "window-size=1280,1024", "--no-sandbox"); // Enable for headless option
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(caps);
        }
        return null;
    }

    @AfterSuite
    public void tearDown() {
        quitDriver();
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
     * Define path for Screenshot file.
     */
    protected String getScreenshotSavePath() {
        String packageName = this.getClass().getPackage().getName();
        File dir = new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + packageName + File.separator);
        dir.mkdirs();
        return dir.getAbsolutePath();
    }


    /**
     * Take Screenshot on failure.
     */
    protected void getScreenshot() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());
        String url = driver.getCurrentUrl().replaceAll("[\\/:*\\?\"<>\\|]", "_");
        String ext = ".png";
        String path = getScreenshotSavePath() + File.separator + date + "_" + url + ext;

        try {
            if (driver instanceof TakesScreenshot) {
                File tmpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                org.openqa.selenium.io.FileHandler.copy(tmpFile, new File(path));
                log.error("Captured Screenshot for Failure: " + path);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            getScreenshot();
            Assert.fail();

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

}
