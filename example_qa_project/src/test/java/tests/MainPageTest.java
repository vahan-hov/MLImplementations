package tests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
import pages.MainPageObject;
import common.ApplicationProperties;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest {
    private MainPageObject mainPageObject;
    private RemoteWebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        context.getCurrentXmlTest().getSuite().setParallel(XmlSuite.ParallelMode.TESTS);
        context.getCurrentXmlTest().getSuite().setThreadCount(3);
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"os", "browser"})
    public void setupTestBeforeClass(String os, String browser) throws MalformedURLException {
        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), chromeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), firefoxOptions);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTestAfterClass() {
        driver.quit();
        driver = null;
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTestBeforeMethod() {
        mainPageObject = new MainPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    /**
     * Verify the page title is displayed.
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
     * Verify page header items are  is displayed.
     */
    @Test(groups = {"smoke","MainPageTest"})
    public void verifyMainPageHeader() {
        System.out.println("==========================");
        System.out.println("verifyMainPageHeader : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        boolean isHeaderDisplayed = mainPageObject.isHeaderItemsDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * This case verifies the page central image is displayed.
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