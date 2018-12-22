package tests;

import common.ApplicationProperties;
import common.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CastAndCrewPageObject;
import pages.MainPageObject;

/**
 * This class executes tests to ensure the 'cast and crew' section of the page works as expected.
 */
public class CastAndCrewPageTest extends BaseClass {

    /**
     * Verify that actor names are displayed in the 'cst and crew' section of web-page.
     */
    @Test(groups = {"smoke", "CastAndCrewPageTest", "verifyActorNames"})
    public void verifyActorNames() {
        System.out.println("\nverifyActorNames : current thread id : " + Thread.currentThread().getId() + "\n");

        CastAndCrewPageObject castAndCrewPageObject = new CastAndCrewPageObject(driver());
        castAndCrewPageObject.navigateToCastAndCrewSection();

        boolean isSubcategoriesDisplayed = castAndCrewPageObject.isActorNamesDisplayed();
        Assert.assertTrue(isSubcategoriesDisplayed, ApplicationProperties.ACTORS_NAMES_ARE_NOT_DISPLAYED_PROPERLY);
    }

    /**
     * Verify that header is displayed in the 'cst and crew' section of web-page.
     */
    @Test(groups = {"CastAndCrewPageTest", "verifyHeaderIsDisplayed"})
    public void verifyHeaderIsDisplayed() {
        System.out.println("\nverifyHeaderIsDisplayed : current thread id : " + Thread.currentThread().getId() + "\n");

        CastAndCrewPageObject castAndCrewPageObject = new CastAndCrewPageObject(driver());
        castAndCrewPageObject.navigateToCastAndCrewSection();

        MainPageObject mainPageObject = new MainPageObject(driver());
        boolean isPopularDressesDisplayed = mainPageObject.isHeaderItemsDisplayed();
        Assert.assertTrue(isPopularDressesDisplayed, ApplicationProperties.HEADER_IS_NOT_DISPLAYED_PROPERLY);

        Assert.fail();
    }
}
