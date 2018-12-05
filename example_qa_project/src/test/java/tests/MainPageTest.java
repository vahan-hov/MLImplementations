package tests;

import common.ApplicationProperties;
import config.DynamicTestNG;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPageObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest {
    private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();

    @BeforeSuite
    @Parameters({"os", "browser", "browserVersion", "classes","threadCount"})
    public void setupTestBeforeSuit(String os, String browser, String browserVersion,String classes, int threadCount) throws Exception {
        System.out.println("Beforesuit ============================================================");

        Map<String, String> params = new HashMap<String, String>();

        String[] browsersArr = browser.split(",");
        String[] browserVersionsArr = browserVersion.split(",");

        if (browsersArr.length == 1 && browserVersionsArr.length == 1) {
            params.put("browser", browser);
            params.put("browserVersion", browserVersion);
        } else if (browsersArr.length == 2 && browserVersionsArr.length == 2) {
            params.put("browser1", browsersArr[0]);
            params.put("browser2", browsersArr[1]);
            params.put("browserVersion1", browserVersionsArr[0]);
            params.put("browserVersion2", browserVersionsArr[1]);
        } else {
            throw new Exception("Wrong browser configs!");
        }

        params.put("os",os);

        String[] classesArr = classes.split(",");
        DynamicTestNG dynamicTestNG = new DynamicTestNG();
        dynamicTestNG.runTestNGTest(params,classesArr,threadCount);
    }

    /**
     * This method runs before every method and creates a new driver (chrome or firefox) and navigates to the URL of website.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"os", "browser", "browserVersion"})
    public void setupTestBeforeMethod(String os, String browser, String browserVersion) throws MalformedURLException {
        ChromeOptions chromeOptions = null;
        FirefoxOptions firefoxOptions = null;
        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase(ApplicationProperties.CHROME_STRING)) {
            chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            chromeOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        } else if (browser.equalsIgnoreCase(ApplicationProperties.FIREFOX_STRING)) {
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            firefoxOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        }

        RemoteWebDriver driver = null;

        if (chromeOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), chromeOptions);
        } else if (firefoxOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), firefoxOptions);
        }
        drivers.set(driver);
        driver().navigate().to(ApplicationProperties.webPageURL);
    }

    /**
     * This method runs after each test and quites browser window and sets driver to 'null'.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownTestAfterMethod() {
        RemoteWebDriver driver = driver();
        driver.quit();
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

        MainPageObject mainPageObject = new MainPageObject(driver());
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

        MainPageObject mainPageObject = new MainPageObject(driver());
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

        MainPageObject mainPageObject = new MainPageObject(driver());
        boolean isFooterDisplayed = mainPageObject.isImageDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.IMAGE_IS_NOT_DISPLAYED_PROPERLY);
    }
}