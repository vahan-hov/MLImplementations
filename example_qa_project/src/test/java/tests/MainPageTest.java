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

    @Test(groups = {"smoke"})
    public void verifyTitle() {
        System.out.println("==========================");
        System.out.println("verifyProfile");
        System.out.println("==========================");
        boolean isTitleGOTDisplayed = mainPageObject.isTitleGOTDisplayed();
        Assert.assertTrue(isTitleGOTDisplayed, ApplicationProperties.WRONG_TITLE);
    }

    /**
     * This case verifies the page title is displayed.
     */
    @Test
    public void verifyMainPageHeader() {
        System.out.println("==========================");
        System.out.println("verifyMainPageHeader");
        System.out.println("==========================");
        boolean isHeaderDisplayed = mainPageObject.isHeaderDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * This case verifies the page footer is displayed.
     */
    @Test
    public void verifyMainPageFooter() {
        System.out.println("==========================");
        System.out.println("verifyMainPageFooter");
        System.out.println("==========================");
        boolean isFooterDisplayed = mainPageObject.isFooterDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.FOOTER_IS_NOT_DISPLAYED_PROPERLY);
    }
}