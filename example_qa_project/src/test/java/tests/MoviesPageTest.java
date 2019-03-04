package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MoviesPageObject;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class MoviesPageTest extends BaseTest {

    /**
     * Verify default view items are displayed in the movies page.
     */
    @Test(groups = {"MoviesPageTest", "verifyDefaultViewMoviesPageTest"})
    public void verifyDefaultViewMoviesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        MoviesPageObject moviesPageObject = new MoviesPageObject(driver());

        moviesPageObject.navigateToMoviesSection();

        Assert.assertTrue(moviesPageObject.isUserNavigatedToMoviesSection(), "User is not in 'Movies' section");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'Movies' section");

        Assert.assertTrue(moviesPageObject.isAllMoviesDisplayed(), "'allMovies' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allMovies' is displayed");

        Assert.assertTrue(moviesPageObject.isFeaturedMoviesDisplayed(), "'featuredMovies' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'featuredMovies' is displayed");

        Assert.assertTrue(moviesPageObject.isComingSoonDisplayed(), "'comingSoon' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'comingSoon' is displayed");

        Assert.assertTrue(moviesPageObject.isDocumentariesDisplayed(), "'documentaries' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'documentaries' is displayed");

        Assert.assertTrue(moviesPageObject.isNewReleasesDisplayed(), "'newReleases' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'newReleases' is displayed");
    }

    /**
     * Verify default view items are displayed in the 'all movies' page.
     */
    @Test(groups = {"MoviesPageTest", "verifyDefaultViewAllMoviesTabMoviesPageTest"})
    public void verifyDefaultViewAllMoviesTabMoviesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        MoviesPageObject moviesPageObject = new MoviesPageObject(driver());

        moviesPageObject.navigateToMoviesSection();
        moviesPageObject.navigateToAllMoviesTab();

        Assert.assertTrue(moviesPageObject.isUserNavigatedToAllMoviesTab(), "User is not in 'AllMovies' tab");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify user is in 'AllMovies' tab");

        Assert.assertTrue(moviesPageObject.isMoviesListDisplayed(), "'moviesList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'moviesList' is displayed");

        Assert.assertTrue(moviesPageObject.isGenreListDisplayed(), "'genreList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'genreList' is displayed");

        Assert.assertTrue(moviesPageObject.areMoviesSortedAlphabetically(), "Movies list is not ordered alphabetically");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify movies list is ordered alphabetically");
    }
}
