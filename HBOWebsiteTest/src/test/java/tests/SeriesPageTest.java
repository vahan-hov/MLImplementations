package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SeriesPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class SeriesPageTest extends BaseTest {

    /**
     * Verify page main sections are displayed in the series page.
     */
    @Test(groups = {"SeriesPageTest", "verifyMainPageSectionsSeriesPageTest"})
    public void verifyMainPageSectionsSeriesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(seriesPageObject.isUserInSeriesSection(), "User is not in 'Series Section' page");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'Series Section' page");

        Assert.assertTrue(seriesPageObject.isPopularSearchesDisplayed(), "'popularSearches' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularSearches' is displayed");

        Assert.assertTrue(seriesPageObject.isEssentialSeriesDisplayed(), "'essentialSeries' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'essentialSeries' is displayed");
    }

    /**
     * Verify 'about' tab of series section is displayed properly.
     */
    @Test(groups = {"smoke", "SeriesPageTest", "verifyAboutTabOfSeriesPageTest"})
    public void verifyAboutTabOfSeriesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(seriesPageObject.isUserInSeriesSection(), "User is not in 'Series Section' page");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'Series Section' page");

        Assert.assertTrue(seriesPageObject.isDiscoverTheLatestListDisplayed(4), "'discoverTheLatestList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverTheLatestList' is displayed");

        seriesPageObject.selectItemInDiscoverItemSection();

        Assert.assertTrue(seriesPageObject.isAboutDiscoverItemHeaderDisplayed(), "'aboutDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'aboutDiscoverItemHeader' is displayed");

        Assert.assertTrue(seriesPageObject.isCastAndCrewDiscoverItemHeaderDisplayed(), "'castAndCrewDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'castAndCrewDiscoverItemHeader' is displayed");

        Assert.assertTrue(seriesPageObject.isShopDiscoverItemHeaderDisplayed(), "'shopDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'shopDiscoverItemHeader' is displayed");

        Assert.assertTrue(seriesPageObject.isSelectYourSeasonDisplayed(), "'selectYourSeason' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'selectYourSeason' is displayed");
    }

    /**
     * Verify 'popular searches' tab of series section is displayed properly.
     */
    @Test(groups = {"smoke", "SeriesPageTest", "verifyPopularSearchesSectionOfSeriesPageTest"})
    public void verifyPopularSearchesSectionOfSeriesPageTest(Method method) throws InterruptedException {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(seriesPageObject.isUserInSeriesSection(), "User is not in 'Series Section' page");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'Series Section' page");

        Assert.assertTrue(seriesPageObject.isPopularSearchesDisplayed(), "'popularSearches' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularSearches' is displayed");

        seriesPageObject.clickOnPopularSearches(5);
        Assert.assertTrue(seriesPageObject.isUserHasLeftSeriesSection(), "Could not choose 'popular searches' item");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on'popular searches' item");

        Assert.assertTrue(seriesPageObject.isPopularItemHeaderDisplayed(), "'popularItemHeader' item is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularItemHeader' item is displayed");

        Assert.assertTrue(seriesPageObject.isPopularItemDetailDisplayed(), "'popularItemDetail' item is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularItemDetail' item is displayed");
    }
}
