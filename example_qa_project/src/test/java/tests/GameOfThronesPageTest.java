package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
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

        BasePageObject basePageObject = new BasePageObject(driver());
        GameOfThronesPageObject gameOfThronesPageObject = new GameOfThronesPageObject(driver());
        SeriesPageObject seriesPageObject = new SeriesPageObject(driver());

        By castAndCrewSectionItemsList = gameOfThronesPageObject.getCastAndCrewSectionItemsList();
        By itemsDetails = gameOfThronesPageObject.getItemsDetails();
        By itemsImage = gameOfThronesPageObject.getItemsImage();
        By itemsTitle = gameOfThronesPageObject.getItemsTitle();

        seriesPageObject.navigateToSeriesSection();
        gameOfThronesPageObject.selectGOT();
        gameOfThronesPageObject.navigateToCastAndCrewSections();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(castAndCrewSectionItemsList, 20), "'castAndCrewSectionItemsList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'castAndCrewSectionItemsList' is displayed");

        gameOfThronesPageObject.selectRandomItemFromCastAndCrewList();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(itemsDetails), "'itemsDetails' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsDetails' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(itemsImage), "'itemsImage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsImage' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(itemsTitle), "'itemsTitle' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'itemsTitle' is displayed");
    }
}
