package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPageObject;
import utils.ApplicationProperties;

public class MainPageTest {
    private MainPageObject mainPageObject;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        driver = new ChromeDriver();
        mainPageObject = new MainPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest() {
        driver.quit();
        driver = null;
    }

    @Test(groups = {"smoke"})
    public void verifyTitle() {
        System.out.println("==========================");
        System.out.println("verifyProfile");
        System.out.println("==========================");
        boolean isTitleGOTDisplayed = mainPageObject.isTitleGOTDisplayed();
        Assert.assertTrue(isTitleGOTDisplayed, ApplicationProperties.WRONG_TITLE);
    }

    @Test
    public void verifyMainPageHeader() {
        System.out.println("==========================");
        System.out.println("verifyMainPageHeader");
        System.out.println("==========================");
        boolean isHeaderDisplayed = mainPageObject.isHeaderDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }

    @Test
    public void verifyMainPageFooter() {
        System.out.println("==========================");
        System.out.println("verifyMainPageFooter");
        System.out.println("==========================");
        boolean isFooterDisplayed = mainPageObject.isFooterDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.FOOTER_IS_NOT_DISPLAYED_PROPERLY);
    }
}
