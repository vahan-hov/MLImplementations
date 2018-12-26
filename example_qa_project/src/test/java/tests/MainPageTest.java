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
 * This class executes tests to ensure the main page of the website works as expected.
 */
public class MainPageTest extends BaseClass {

    /**
     * Verify page header items are is displayed in the main page.
     */
    @Test(groups = {SMOKE, MAIN_PAGE_TEST, VERIFY_MAIN_PAGE_SECTIONS_TEST})
    public void verifyMainPageSectionsTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        boolean isMainPageSectionsAreDisplayed = mainPageObject.isMainPageSectionsAreDisplayed();
        Assert.assertTrue(isMainPageSectionsAreDisplayed, ApplicationProperties.MAIN_PAGE_SECTIONS_ARE_NOT_DISPLAYED);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_SECTIONS_ARE_DISPLAYED);

    }

    /**
     * Verify page header items are is displayed in the main page.
     */
    @Test(groups = {SMOKE, MAIN_PAGE_TEST, VERIFY_MAIN_PAGE_HEADER_TEST})
    public void verifyMainPageHeaderTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        boolean isHeaderDisplayed = mainPageObject.isMainPageHeaderItemsAreDisplayed();
        Assert.assertTrue(isHeaderDisplayed, ApplicationProperties.MAIN_PAGE_HEADER_ITEMS_ARE_NOT_DISPLAYED);
        ExtentTestManager.getTest().log(LogStatus.INFO, ApplicationProperties.VERIFY_HEADER_IS_DISPLAYED);

    }
}