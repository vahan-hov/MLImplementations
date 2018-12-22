package tests;

import common.ApplicationProperties;
import common.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPageObject;

/**
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest extends BaseClass {

    /**
     * Verify the page title (the one displayed inside browser tab) is displayed.
     */
    @Test(groups = {"MainPageTest", "verifyTitle"})
    public void verifyTitle() {
        System.out.println("\nverifyTitle : current thread id : " + Thread.currentThread().getId() + "\n");

        MainPageObject mainPageObject = new MainPageObject(driver());
        boolean isTitleGOTDisplayed = mainPageObject.isTitleDisplayed();
        Assert.assertTrue(isTitleGOTDisplayed, ApplicationProperties.WRONG_TITLE);
    }

    /**
     * Verify page header items are is displayed in the main page.
     */
    @Test(groups = {"smoke", "MainPageTest", "verifyMainPageHeader"})
    public void verifyMainPageHeader() {
        System.out.println("\nverifyMainPageHeader : current thread id : " + Thread.currentThread().getId() + "\n");

        MainPageObject mainPageObject = new MainPageObject(driver());
        boolean isHeaderDisplayed = mainPageObject.isHeaderItemsDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * This case verifies the page central image of the main page is displayed.
     */
    @Test(groups = {"MainPageTest", "verifyMainPageCentralImage"})
    public void verifyMainPageCentralImage() {
        System.out.println("\nverifyMainPageCentralImage : current thread id : " + Thread.currentThread().getId() + "\n");

        MainPageObject mainPageObject = new MainPageObject(driver());
        boolean isFooterDisplayed = mainPageObject.isImageDisplayed();
        Assert.assertTrue(isFooterDisplayed, ApplicationProperties.IMAGE_IS_NOT_DISPLAYED_PROPERLY);

        Assert.fail();
    }
}