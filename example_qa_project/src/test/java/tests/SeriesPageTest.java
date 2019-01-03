package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
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
        BasePageObject basePageObject = new BasePageObject(driver());

        By discoverTheLatest = seriesPageObject.getDiscoverTheLatest();
        By popularSearches = seriesPageObject.getPopularSearches();
        By essentialSeries = seriesPageObject.getEssentialSeries();

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(discoverTheLatest), "'discoverTheLatest' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverTheLatest' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(popularSearches), "'popularSearches' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularSearches' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(essentialSeries), "'essentialSeries' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'essentialSeries' is displayed");
    }

    /**
     * Verify 'about' tab of series section is displayed properly.
     */
    @Test(groups = {"smoke", "SeriesPageTest", "verifyAboutTabOfSeriesPageTest"})
    public void verifyAboutTabOfSeriesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());
        BasePageObject basePageObject = new BasePageObject(driver());

        By discoverTheLatestList = seriesPageObject.getDiscoverTheLatestList();
        By aboutDiscoverItemHeader = seriesPageObject.getAboutDiscoverItemHeader();
        By castAndCrewDiscoverItemHeader = seriesPageObject.getCastAndCrewDiscoverItemHeader();
        By shopDiscoverItemHeader = seriesPageObject.getShopDiscoverItemHeader();
        By selectYourSeason = seriesPageObject.getSelectYourSeason();

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(discoverTheLatestList, 4), "'discoverTheLatestList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverTheLatestList' is displayed");

        seriesPageObject.selectItemInDiscoverItemSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(aboutDiscoverItemHeader), "'aboutDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'aboutDiscoverItemHeader' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(castAndCrewDiscoverItemHeader), "'castAndCrewDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'castAndCrewDiscoverItemHeader' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(shopDiscoverItemHeader), "'shopDiscoverItemHeader' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'shopDiscoverItemHeader' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(selectYourSeason), "'selectYourSeason' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'selectYourSeason' is displayed");
    }

    /**
     * Verify 'popular searches' tab of series section is displayed properly.
     */
    @Test(groups = {"smoke", "SeriesPageTest", "verifyPopularSearchesSectionOfSeriesPageTest"})
    public void verifyPopularSearchesSectionOfSeriesPageTest(Method method) throws InterruptedException {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());
        BasePageObject basePageObject = new BasePageObject(driver());

        By popularSearches = seriesPageObject.getPopularSearches();
        By popularSearchesList = seriesPageObject.getPopularSearchesList();
        By popularItemHeader = seriesPageObject.getPopularItemHeader();
        By popularItemDetail = seriesPageObject.getPopularItemDetail();

        seriesPageObject.navigateToSeriesSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(popularSearches), "'popularSearches' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularSearches' is displayed");

        basePageObject.clickOnRandomItemInList(popularSearchesList, 5);
        Assert.assertTrue(seriesPageObject.isUserHasLeftSeriesSection(), "Could not choose 'popular searches' item");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Click on'popular searches' item");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(popularItemHeader), "'popularItemHeader' item is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularItemHeader' item is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(popularItemDetail), "'popularItemDetail' item is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'popularItemDetail' item is displayed");
    }
}
