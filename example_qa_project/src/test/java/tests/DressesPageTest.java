package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DressesPageObject;
import utils.ApplicationProperties;

public class DressesPageTest {
    private DressesPageObject dressesPageObject;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        driver = new ChromeDriver();
        dressesPageObject = new DressesPageObject(driver);
        driver.navigate().to(ApplicationProperties.webPageURL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest() {
        driver.quit();
        driver = null;
    }

    @Test(groups = {"smoke"})
    public void verifySubcategories() {
        System.out.println("==========================");
        System.out.println("verifySubcategories");
        System.out.println("==========================");
        dressesPageObject.navigateToDressesSection();

        boolean isSubcategoriesDisplayed = dressesPageObject.isSubcategoriesDisplayed();
        Assert.assertTrue(isSubcategoriesDisplayed, ApplicationProperties.SUBCATEGORIES_ARE_NOT_DISPLAYED_PROPERLY);
    }

    @Test
    public void verifyPopularDresses() {
        System.out.println("==========================");
        System.out.println("verifyPopularDresses");
        System.out.println("==========================");
        dressesPageObject.navigateToDressesSection();

        boolean isPopularDressesDisplayed = dressesPageObject.isDressesCategoriesDisplayed();
        Assert.assertTrue(isPopularDressesDisplayed, ApplicationProperties.CATEGORIES_ARE_NOT_DISPLAYED_PROPERLY);
    }
}
