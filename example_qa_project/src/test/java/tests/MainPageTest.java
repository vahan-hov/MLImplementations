package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DressesPageObject;
import pages.MainPageObject;
import utils.ApplicationProperties;

/**
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest {
    private MainPageObject mainPageObject;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setupTestBeforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTestAfterClass() {
        driver = null;
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTestBeforeMethod() {
        mainPageObject = new MainPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTestAfterMethod() {
        driver.quit();
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
