package tests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CastAndCrewPageObject;
import common.ApplicationProperties;
import pages.MainPageObject;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class executes tests to ensure the 'cast and crew' section of the page works as expected.
 */
public class CastAndCrewPageTest {
    private CastAndCrewPageObject castAndCrewPageObject;
    private MainPageObject mainPageObject;
    private RemoteWebDriver driver;

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
        castAndCrewPageObject = new CastAndCrewPageObject(driver);
        mainPageObject = new MainPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    /**
     * Verify that actor names are displayed.
     */
    @Test(groups = {"smoke","MainPageTest"})
    public void verifyActorNames() {
        System.out.println("==========================");
        System.out.println("verifyActorNames : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        castAndCrewPageObject.navigateToCastAndCrewSection();

        boolean isSubcategoriesDisplayed = castAndCrewPageObject.isActorNamesDisplayed();
        Assert.assertTrue(isSubcategoriesDisplayed, ApplicationProperties.ACTORS_NAMES_ARE_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * Verify that header is displayed in 'cast and crew' section.
     */
    @Test(groups = {"MainPageTest"})
    public void verifyHeaderIsDisplayed() {
        System.out.println("==========================");
        System.out.println("verifyHeaderIsDisplayed : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        castAndCrewPageObject.navigateToCastAndCrewSection();

        boolean isPopularDressesDisplayed = mainPageObject.isHeaderItemsDisplayed();
        Assert.assertTrue(isPopularDressesDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }
}
