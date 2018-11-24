package tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
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

    @BeforeClass(alwaysRun = true)
    public void setupTestBeforeClass() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), capabilities);
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
    @Test(groups = {"smoke"})
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
    @Test
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
    @Test
    public void verifyMainPageCentralImage() {
        System.out.println("==========================");
        System.out.println("verifyMainPageCentralImage : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        boolean isFooterDisplayed = mainPageObject.isImageDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.IMAGE_IS_NOT_DISPLAYED_PROPERLY);
    }
}