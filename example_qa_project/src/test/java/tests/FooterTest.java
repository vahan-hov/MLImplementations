package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
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

        BasePageObject basePageObject = new BasePageObject(driver());
        FooterTestPageObject footerTestPageObject = new FooterTestPageObject(driver());

        By about = footerTestPageObject.getAbout();
        By hboInspires = footerTestPageObject.getHBOInspires();
        By help = footerTestPageObject.getHelp();
        By shop = footerTestPageObject.getShop();
        By waysToGet = footerTestPageObject.getWaysToGet();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(about), "'about' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'about' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(hboInspires), "'hboInspires' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'hboInspires' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(help), "'help' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'help' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(shop), "'shop' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'shop' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(waysToGet), "'waysToGet' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'waysToGet' is displayed");

    }
}
