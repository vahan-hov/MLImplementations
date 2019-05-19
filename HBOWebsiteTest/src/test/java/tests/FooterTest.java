package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterTestPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class FooterTest extends BaseTest {

    /**
     * Verify main page footer items are is displayed properly.
     */
    @Test(groups = {"FooterTest", "mainPageFooterTest"})
    public void mainPageFooterTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        FooterTestPageObject footerTestPageObject = new FooterTestPageObject(driver());

        Assert.assertTrue(footerTestPageObject.verifyAboutIsDisplayed(), "'about' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'about' is displayed");

        Assert.assertTrue(footerTestPageObject.verifyHboInspiresIsDisplayed(), "'hboInspires' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'hboInspires' is displayed");

        Assert.assertTrue(footerTestPageObject.verifyHelpIsDisplayed(), "'help' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'help' is displayed");

        Assert.assertTrue(footerTestPageObject.verifyShopIsDisplayed(), "'shop' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'shop' is displayed");

        Assert.assertTrue(footerTestPageObject.verifyWaysToGetIsDisplayed(), "'waysToGet' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'waysToGet' is displayed");
    }
}
