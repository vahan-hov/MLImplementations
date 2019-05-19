package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.List;

public class MoviesPageObject extends AbstractPage {

    private final By moviesSection = By.xpath("//a[text() = 'Movies']");
    private final By featuredMovies = By.xpath("//a[text() = 'FEATURED MOVIES']");
    private final By allMovies = By.xpath("//a[text() = 'ALL Movies ']");
    private final By documentaries = By.xpath("//a[text() = 'DOCUMENTARIES']");
    private final By newReleases = By.xpath("//h2[text() = 'New Releases']");
    private final By comingSoon = By.xpath("//h2[text() = 'Coming Soon']");
    private final By moviesList = By.xpath("//*[@class = 'modules/cards/CatalogCard--title']");
    private final By genreList = By.xpath("//ul[@class='components/FilterBar--navItems']//li");

    /**
     * Constructor
     */
    public MoviesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to 'Movie' section
     */
    public void navigateToMoviesSection() {
        WebElement item = findElementListByLocator(moviesSection).get(0);
        waitVisibility(item);
        click(item);
        waitVisibility(findElementListByLocator(featuredMovies).get(0));
    }

    /**
     * Check if the user is navigated to 'Movies' section
     */
    public boolean isUserNavigatedToMoviesSection() {
        return verifyElementByIsDisplayed(featuredMovies);
    }

    /**
     * Navigate to 'All Movies' section
     */
    public void navigateToAllMoviesTab() {
        WebElement element = findElementListByLocator(allMovies).get(0);
        waitVisibility(element);
        click(element);
        waitVisibility(genreList);
    }

    /**
     * Navigate to 'All Movies' section
     */
    public boolean isUserNavigatedToAllMoviesTab() {
        return verifyElementByIsDisplayed(genreList);
    }

    /**
     * Check if string starts with an article
     */
    private boolean isMovieStartWithArticle(String movie) {
        List<String> articlesList = Arrays.asList("A ", "An ", "The ", "El ", "Los ", "La ");
        for (String article : articlesList) {
            if (movie.startsWith(article)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the movies are sorted alphabetically
     */
    public boolean areMoviesSortedAlphabetically() {
        int maxNumberOfItems = 30;
        int counter = 0;
        List<WebElement> movies = findElementListByLocator(moviesList);
        String previous = String.valueOf(movies.get(0).getText().charAt(0));

        for (final WebElement currentElement : movies) {
            String current = currentElement.getText();
            if (isMovieStartWithArticle(previous)) {
                previous = current;
                continue;
            }

            if (current.compareTo(previous) < 0) {
                System.out.println(" Previous " + previous);
                System.out.println(current + " is not ordered alphabetically ========================");
                return false;
            }
            previous = current;
            counter++;
            if (counter == maxNumberOfItems) {
                return true;
            }
        }
        return true;
    }

    /**
     * Check if the user is navigated to 'Movies' section
     */
    public boolean isAllMoviesDisplayed() {
        return verifyElementByIsDisplayed(allMovies, 0);
    }

    /**
     * Check if 'Featured Movies' section is displayed
     */
    public boolean isFeaturedMoviesDisplayed() {
        return verifyElementByIsDisplayed(featuredMovies);
    }

    /**
     * Check if 'Coming soon' section is displayed
     */
    public boolean isComingSoonDisplayed() {
        return verifyElementByIsDisplayed(comingSoon);
    }

    /**
     * Check if 'Documentaries' section is displayed
     */
    public boolean isDocumentariesDisplayed() {
        return verifyElementByIsDisplayed(documentaries);
    }

    /**
     * Check if 'New releases' section is displayed
     */
    public boolean isNewReleasesDisplayed() {
        return verifyElementByIsDisplayed(newReleases);
    }

    /**
     * Check if 'Movies list' section is displayed
     */
    public boolean isMoviesListDisplayed() {
        return verifyElementByIsDisplayed(moviesList);
    }

    /**
     * Check if 'Genre list' section is displayed
     */
    public boolean isGenreListDisplayed() {
        return verifyElementByIsDisplayed(genreList);
    }
}
