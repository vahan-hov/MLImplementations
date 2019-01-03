package tests;

import com.relevantcodes.extentreports.LogStatus;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePageObject;
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

        BasePageObject basePageObject = new BasePageObject(driver());
        MoviesPageObject moviesPageObject = new MoviesPageObject(driver());

        By allMovies = moviesPageObject.getAllMovies();
        By featuredMovies = moviesPageObject.getFeaturedMovies();
        By comingSoon = moviesPageObject.getComingSoon();
        By documentaries = moviesPageObject.getDocumentaries();
        By leavingSoon = moviesPageObject.getLeavingSoon();
        By newReleases = moviesPageObject.getNewReleases();

        moviesPageObject.navigateToMoviesSection();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(allMovies), "'allMovies' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'allMovies' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(featuredMovies), "'featuredMovies' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'featuredMovies' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(comingSoon), "'comingSoon' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'comingSoon' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(documentaries), "'documentaries' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'documentaries' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(leavingSoon), "'leavingSoon' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'leavingSoon' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(newReleases), "'newReleases' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'newReleases' is displayed");
    }

    /**
     * Verify default view items are displayed in the 'all movies' page.
     */
    @Test(groups = {"MoviesPageTest", "verifyDefaultViewAllMoviesTabMoviesPageTest"})
    public void verifyDefaultViewAllMoviesTabMoviesPageTest(Method method) {
        System.out.println("\n ========== Executing " + method.getDeclaringClass() + " " + method.getName() + ": current thread id : " + Thread.currentThread().getId() + " ==========\n");

        BasePageObject basePageObject = new BasePageObject(driver());
        MoviesPageObject moviesPageObject = new MoviesPageObject(driver());

        By moviesList = moviesPageObject.getMoviesList();
        By genreList = moviesPageObject.getGenreList();

        moviesPageObject.navigateToMoviesSection();
        moviesPageObject.navigateToAllMoviesTab();

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(moviesList, null), "'moviesList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'moviesList' is displayed");

        Assert.assertTrue(basePageObject.verifyElementByIsDisplayed(genreList, null), "'genreList' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify 'genreList' is displayed");

//        Assert.assertTrue(moviesPageObject.areMoviesSortedAlphabetically(), "Movies list is not ordered alphabetically");
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Verify movies list is ordered alphabetically");
    }
}
