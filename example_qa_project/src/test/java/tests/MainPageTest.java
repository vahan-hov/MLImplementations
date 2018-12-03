package tests;

import common.ApplicationProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.MainPageObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest {
    private MainPageObject mainPageObject;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();

    /**
     * This methods runs before the class runs and instantiates driver as chrome or firefox driver depending on the argument given.
     * Also the method sets different attributes for driver again depending on the arguments.
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser", "browserVersion"})
    public void setupTestBeforeClass(String os, String browser, String browserVersion) {
        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase(ApplicationProperties.CHROME_STRING)) {
            this.chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            chromeOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        } else if (browser.equalsIgnoreCase(ApplicationProperties.FIREFOX_STRING)) {
            this.firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            firefoxOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        }
    }

    /**
     * This method runs before every method and creates a new driver (chrome or firefox) and navigates to the URL of website.
     */
    @BeforeMethod(alwaysRun = true)
    public void setupTestBeforeMethod(ITestResult testResult) throws MalformedURLException {
        RemoteWebDriver driver = null;
        if (chromeOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), chromeOptions);
        } else if (firefoxOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), firefoxOptions);
        }
        drivers.set(driver);
        mainPageObject = new MainPageObject(driver());
        driver().navigate().to(ApplicationProperties.webPageURL);
    }

    /**
     * This method runs after each test and quites browser window and sets driver to 'null'.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownTestAfterMethod() {
        RemoteWebDriver driver = driver();
        driver.quit();
        driver = null;
    }

    /**
     * This method returns driver instance if it's value is not null.
     */
    private RemoteWebDriver driver() {
        RemoteWebDriver driver = drivers.get();
        if (driver == null) {
            throw new IllegalStateException("Driver should have not been null.");
        }
        return driver;
    }

    /**
     * Verify the page title (the one displayed inside browser tab) is displayed.
     */
    @Test(groups = {"MainPageTest"})
    public void verifyTitle() {
        System.out.println("==========================");
        System.out.println("verifyTitle : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        boolean isTitleGOTDisplayed = mainPageObject.isTitleDisplayed();
        Assert.assertTrue(isTitleGOTDisplayed, ApplicationProperties.WRONG_TITLE);
    }

    /**
     * Verify page header items are is displayed in the main page.
     */
    @Test(groups = {"smoke", "MainPageTest"})
    public void verifyMainPageHeader() {
        System.out.println("==========================");
        System.out.println("verifyMainPageHeader : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        boolean isHeaderDisplayed = mainPageObject.isHeaderItemsDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * This case verifies the page central image of the main page is displayed.
     */
    @Test(groups = {"MainPageTest"})
    public void verifyMainPageCentralImage() {
        System.out.println("==========================");
        System.out.println("verifyMainPageCentralImage : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        boolean isFooterDisplayed = mainPageObject.isImageDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.IMAGE_IS_NOT_DISPLAYED_PROPERLY);
    }
}