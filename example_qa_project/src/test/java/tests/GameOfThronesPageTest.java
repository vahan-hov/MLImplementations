package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GameOfThronesPageObject;
import pages.SeriesPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class GameOfThronesPageTest extends BaseTest {

    /**
     * Verify 'Game of Thrones' page's 'cast and crew' tabis displayed properly.
     */
    @Test(groups = {"GameOfThronesPageTest", "castAndCrewPageTest"})
    public void castAndCrewPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        GameOfThronesPageObject gameOfThronesPageObject = new GameOfThronesPageObject(driver());
        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());

        seriesPageObject.navigateToSeriesSection();
        gameOfThronesPageObject.selectGOT();
        gameOfThronesPageObject.navigateToCastAndCrewSections();

        Assert.assertTrue(gameOfThronesPageObject.isUserInCastAndCrewSection(), "User is not in 'Cast and Crew' page");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'Cast and Crew' page");

        Assert.assertTrue(gameOfThronesPageObject.iscastAndCrewSectionItemsListDisplayed(20), "'castAndCrewSectionItemsList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'castAndCrewSectionItemsList' is displayed");

        gameOfThronesPageObject.selectRandomItemFromCastAndCrewList();

        Assert.assertTrue(gameOfThronesPageObject.isItemsDetailsDisplayed(), "'itemsDetails' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsDetails' is displayed");

        Assert.assertTrue(gameOfThronesPageObject.isItemsImageDisplayed(), "'itemsImage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsImage' is displayed");

        Assert.assertTrue(gameOfThronesPageObject.isItemsTitleDisplayed(), "'itemsTitle' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsTitle' is displayed");
    }
}
