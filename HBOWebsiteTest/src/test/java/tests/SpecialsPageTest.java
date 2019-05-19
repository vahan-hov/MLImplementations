//package tests;
//
//import com.relevantcodes.extentreports.LogStatus;
//import common.BaseTest;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.SpecialsPageObject;
//import reports.ExtentTestManager;
//
//import java.lang.reflect.Method;
//
//public class SpecialsPageTest extends BaseTest {
//    /**
//     * Verify default view items are displayed in the 'specials' page.
//     */
//    @Test(groups = {"SpecialsPageTest", "verifyDefaultViewSpecialsPageTest"})
//    public void verifyDefaultViewSpecialsPageTest(Method method) {
//        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");
//
//        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());
//
//        specialsPageObject.navigateToSpecialsSection();
//
//        Assert.assertTrue(specialsPageObject.isUserInSpecialsSection(), "The user is not in 'Specials' section");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify the user is in 'Specials' section");
//
//        Assert.assertTrue(specialsPageObject.isDiscoverTheLatestDisplayed(), "'discoverTheLatest' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverTheLatest' is displayed");
//
//        Assert.assertTrue(specialsPageObject.isLetTheGamesBeginDisplayed(), "'letTheGamesBegin' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'letTheGamesBegin' is displayed");
//
//        Assert.assertTrue(specialsPageObject.isEssentialSpecialsDisplayed(), "'essentialSpecials' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'essentialSpecials' is displayed");
//
//        Assert.assertTrue(specialsPageObject.isFantasticallyFunnyFemalesDisplayed(), "'fantasticallyFunnyFemales' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'fantasticallyFunnyFemales' is displayed");
//    }
//
//    /**
//     * Verify 'discover the latest; items are displayed properly in the 'specials' page.
//     */
//    @Test(groups = {"SpecialsPageTest", "discoverTheLatestSectionSpecialsPageTest"})
//    public void discoverTheLatestSectionSpecialsPageTest(Method method) throws InterruptedException {
//        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");
//
//        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());
//
//        specialsPageObject.navigateToSpecialsSection();
//
//        Assert.assertTrue(specialsPageObject.isUserInSpecialsSection(), "The user is not in 'Specials' section");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify the user is in 'Specials' section");
//
//        specialsPageObject.selectItemFromDiscoverLatestList();
//
////        Assert.assertTrue(specialsPageObject.isDiscoverItemImageDisplayed(), "'discoverItemImage' is not displayed");
////        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'discoverItemImage' is displayed");
//    }
//
//    /**
//     * Verify 'all specials' tab is displayed properly in the 'specials' page.
//     */
//    @Test(groups = {"SpecialsPageTest", "verifyAllSpecialsSpecialsPageTest"})
//    public void verifyAllSpecialsSpecialsPageTest(Method method) {
//        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");
//
//        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());
//
//        specialsPageObject.navigateToSpecialsSection();
//        specialsPageObject.navigateToAllSpecialsTab();
//
//        Assert.assertTrue(specialsPageObject.isUserInSpecialsSection(), "The user is not in 'Specials' section");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify the user is in 'Specials' section");
//
//        Assert.assertTrue(specialsPageObject.isAllSpecialItemsListDisplayed(), "'allSpecialItemsList' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsList' is displayed");
//    }
//
//    /**
//     * Verify 'all specials' tab items are displayed properly in the 'specials' page.
//     */
//    @Test(groups = {"SpecialsPageTest", "verifyAllSpecialsPageItemSpecialsPageTest"})
//    public void verifyAllSpecialsPageItemSpecialsPageTest(Method method) {
//        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");
//
//        SpecialsPageObject specialsPageObject = new SpecialsPageObject(driver());
//
//        specialsPageObject.navigateToSpecialsSection();
//        specialsPageObject.navigateToAllSpecialsTab();
//
//        Assert.assertTrue(specialsPageObject.isUserInSpecialsSection(), "The user is not in 'Specials' section");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify the user is in 'Specials' section");
//
//        Assert.assertTrue(specialsPageObject.isAllSpecialItemsListDisplayed(), "'allSpecialItemsList' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsList' is displayed");
//
//        specialsPageObject.selectItemFromSpecialsList();
//
//        Assert.assertTrue(specialsPageObject.isAllSpecialItemsImageDisplayed(), "'allSpecialItemsImage' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsImage' is displayed");
//
//        Assert.assertTrue(specialsPageObject.isAllSpecialItemsTitleDisplayed(), "'allSpecialItemsTitle' is not displayed");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allSpecialItemsTitle' is displayed");
//    }
//}
