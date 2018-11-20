package tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DressesPageObject;
import common.ApplicationProperties;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class executes tests to ensure the 'dresses' section of the page of the website works as expected.
 */
public class DressesPageTest {
    private DressesPageObject dressesPageObject;
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
        dressesPageObject = new DressesPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    /**
     * This case verifies the subcategories of dresses are displayed.
     */
    @Test(groups = {"smoke"})
    public void verifySubcategories() {
        System.out.println("==========================");
        System.out.println("verifySubcategories : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        dressesPageObject.navigateToDressesSection();

        boolean isSubcategoriesDisplayed = dressesPageObject.isSubcategoriesDisplayed();
        Assert.assertTrue(isSubcategoriesDisplayed, ApplicationProperties.SUBCATEGORIES_ARE_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * This case verifies the subcategories of dresses are displayed.
     */
    @Test
    public void verifyPopularDresses() {
        System.out.println("==========================");
        System.out.println("verifyPopularDresses : current thread id : " + Thread.currentThread().getId());
        System.out.println("==========================");
        dressesPageObject.navigateToDressesSection();

        boolean isPopularDressesDisplayed = dressesPageObject.isDressesCategoriesDisplayed();
        Assert.assertTrue(isPopularDressesDisplayed, ApplicationProperties.CATEGORIES_ARE_NOT_DISPLAYED_PROPERLY);
    }
}
