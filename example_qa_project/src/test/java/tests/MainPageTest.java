package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
import pages.MainPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class MainPageTest extends BaseTest {

    /**
     * Verify main page main sections are displayed properly.
     */
    @Test(groups = {"smoke", "MainPageTest", "verifyMainPageSectionsTest"})
    public void verifyMainPageSectionsTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        MainPageObject mainPageObject = new MainPageObject(driver());
        BasePageObject basePageObject = new BasePageObject(driver());

        By hereBeforeYouKnowIt = mainPageObject.getHereBeforeYouKnowIt();
        By inHBONews = mainPageObject.getInHBONews();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(hereBeforeYouKnowIt), "'hereBeforeYouKnowIt' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'hereBeforeYouKnowIt' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(inHBONews), "'inHBONews' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'inHBONews' is displayed");
    }

    /**
     * Verify page header items are is displayed in the main page.
     */
    @Test(groups = {"smoke", "MainPageTest", "verifyMainPageHeaderTest"})
    public void verifyMainPageHeaderTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        MainPageObject mainPageObject = new MainPageObject(driver());
        BasePageObject basePageObject = new BasePageObject(driver());

        By headerItemsList = mainPageObject.getHeaderItemsList();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(headerItemsList), "'headerItemsList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'headerItemsList' is displayed");
    }
}