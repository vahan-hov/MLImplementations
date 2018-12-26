package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.ApplicationProperties;
import common.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

import static common.ApplicationProperties.*;

/**
 * Class verifies that series page is working as expected.
 */
public class SeriesPageTest extends BaseClass {

    /**
     * Verify page header items are is displayed in the series page.
     */
    @Test(groups = {SERIES_PAGE_TEST, VERIFY_MAIN_PAGE_SECTIONS_TEST})
    public void verifyMainPageSectionsTest(Method method) throws InterruptedException {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        seriesPageObject.navigateToSeriesSection();

        boolean isSeriesSectionDefaultViewPresent = seriesPageObject.isSeriesSectionDefaultViewPresent();
        Assert.assertTrue(isSeriesSectionDefaultViewPresent, ApplicationProperties.SERIES_SECTION_DEFAULT_VIEW_IS_NOT_PRESENT);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_SERIES_SECTION_DEFAULT_VIEW_IS_PRESENT);
    }

    /**
     * Verify series page sections are displayed.
     */
    @Test(groups = {SMOKE, SERIES_PAGE_TEST, VERIFY_SERIES_PAGE_SECTIONS_TEST})
    public void verifySeriesPageSectionsTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        seriesPageObject.navigateToSeriesSection();

        boolean isDiscoverLatestItemsDisplayed = seriesPageObject.isDiscoverLatestItemsDisplayed();
        Assert.assertTrue(isDiscoverLatestItemsDisplayed, ApplicationProperties.DISCOVER_LATEST_ITEMS_NOT_DISPLAYED);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_DISCOVER_LATEST_ITEMS_ARE_DISPLAYED);

        seriesPageObject.selectItemInDiscoverItemSection();

        boolean isDiscoverItemHeaderDisplayed = seriesPageObject.isDiscoverItemHeaderDisplayed();
        Assert.assertTrue(isDiscoverItemHeaderDisplayed, ApplicationProperties.DISCOVER_LATEST_ITEMS_HEADER_NOT_DISPLAYED);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_DISCOVER_LATEST_ITEMS_HEADER_IS_DISPLAYED);

        boolean isSelectSeasonDisplayed = seriesPageObject.isSelectSeasonDisplayed();
        Assert.assertTrue(isSelectSeasonDisplayed, ApplicationProperties.SELECT_YOUR_SEASON_NOT_DISPLAYED);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_SELECT_YOUR_SEASON_IS_DISPLAYED);
    }
}
