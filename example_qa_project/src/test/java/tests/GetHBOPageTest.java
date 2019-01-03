package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
import pages.GetHBOPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class GetHBOPageTest extends BaseTest {

    /**
     * Verify 'Get HBO' page is displayed properly.
     */
    @Test(groups = {"GetHBOPageTest", "verifyDefaultViewGetHBOPageTest"})
    public void verifyDefaultViewGetHBOPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        GetHBOPageObject getHBOPageObject = new GetHBOPageObject(driver());

        By digitalSubscription = getHBOPageObject.getDigitalSubscription();
        By noTVPackage = getHBOPageObject.getNoTVPackage();
        By tvPackage = getHBOPageObject.getTVPackage();
        By tableItems = getHBOPageObject.getTableItems();

        getHBOPageObject.navigateToGetHBOSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(digitalSubscription), "'digitalSubscription' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'digitalSubscription' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(noTVPackage), "'noTVPackage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'noTVPackage' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(tvPackage), "'tvPackage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'tvPackage' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(tableItems, 9), "'tableItems' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'tableItems' is displayed");
    }
}
