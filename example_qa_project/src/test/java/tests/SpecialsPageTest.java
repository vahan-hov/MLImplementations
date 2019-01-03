package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
import pages.SpecialsPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class SpecialsPageTest extends BaseTest {

    /**
     * Verify default view items are displayed in the 'specials' page.
     */
    @Test(groups = {"SpecialsPageTest", "verifyDefaultViewSpecialsPageTest"})
    public void verifyDefaultViewSpecialsPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());

        By discoverTheLatest = specialsPageObject.getDiscoverTheLatest();
        By letTheGamesBegin = specialsPageObject.getLetTheGamesBegin();
        By essentialSpecials = specialsPageObject.getEssentialSpecials();
        By fantasticallyFunnyFemales = specialsPageObject.getFantasticallyFunnyFemales();

        specialsPageObject.navigateToGetHBOSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(discoverTheLatest), "'discoverTheLatest' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverTheLatest' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(letTheGamesBegin), "'letTheGamesBegin' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'letTheGamesBegin' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(essentialSpecials), "'essentialSpecials' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'essentialSpecials' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(fantasticallyFunnyFemales), "'fantasticallyFunnyFemales' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'fantasticallyFunnyFemales' is displayed");
    }

    /**
     * Verify 'discover the latest; items are displayed properly in the 'specials' page.
     */
    @Test(groups = {"SpecialsPageTest", "discoverTheLatestSectionSpecialsPageTest"})
    public void discoverTheLatestSectionSpecialsPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());

        By discoverItemImage = specialsPageObject.getDiscoverItemImage();

        specialsPageObject.navigateToGetHBOSection();
        specialsPageObject.selectItemFromDiscoverLatestList();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(discoverItemImage), "'discoverItemImage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverItemImage' is displayed");
    }

    /**
     * Verify 'all specials' tab is displayed properly in the 'specials' page.
     */
    @Test(groups = {"SpecialsPageTest", "verifyAllSpecialsSpecialsPageTest"})
    public void verifyAllSpecialsSpecialsPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());

        By allSpecialItemsList = specialsPageObject.getAllSpecialItemsList();

        specialsPageObject.navigateToGetHBOSection();
        specialsPageObject.navigateToAllSpecialsTab();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(allSpecialItemsList, 20), "'allSpecialItemsList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsList' is displayed");
    }

    /**
     * Verify 'all specials' tab items are displayed properly in the 'specials' page.
     */
    @Test(groups = {"SpecialsPageTest", "verifyAllSpecialsPageItemSpecialsPageTest"})
    public void verifyAllSpecialsPageItemSpecialsPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());

        By allSpecialItemsList = specialsPageObject.getAllSpecialItemsList();
        By allSpecialItemsImage = specialsPageObject.getAllSpecialItemsImage();
        By allSpecialItemsTitle = specialsPageObject.getAllSpecialItemsTitle();

        specialsPageObject.navigateToGetHBOSection();
        specialsPageObject.navigateToAllSpecialsTab();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(allSpecialItemsList, 20), "'allSpecialItemsList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsList' is displayed");

        specialsPageObject.selectItemFromSpecialsList();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(allSpecialItemsImage), "'allSpecialItemsImage' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsImage' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(allSpecialItemsTitle), "'allSpecialItemsTitle' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsTitle' is displayed");

    }
}
