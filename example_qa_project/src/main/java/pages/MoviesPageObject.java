package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MoviesPageObject extends AbstractPage {

    private final By moviesSection = By.xpath("//a[text() = 'Movies']");
    private final By featuredMovies = By.xpath("//a[text() = 'FEATURED MOVIES']");
    private final By allMovies = By.xpath("//a[text() = 'ALL MOVIES']");
    private final By documentaries = By.xpath("//a[text() = 'DOCUMENTARIES']");
    private final By newReleases = By.xpath("//h2[text() = 'New Releases']");
    private final By comingSoon = By.xpath("//h2[text() = 'Coming Soon']");
    private final By leavingSoon = By.xpath("//h2[text() = 'Leaving Soon']");
    private final By moviesList = By.xpath("//*[@class = 'modules/cards/CatalogCard--title']");
    private final By genreList = By.xpath("//ul[@class='components/FilterBar--navItems']//li");

    public MoviesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getFeaturedMovies() {
        return featuredMovies;
    }

    public By getAllMovies() {
        return allMovies;
    }

    public By getDocumentaries() {
        return documentaries;
    }

    public By getNewReleases() {
        return newReleases;
    }

    public By getComingSoon() {
        return comingSoon;
    }

    public By getLeavingSoon() {
        return leavingSoon;
    }

    public By getMoviesList() {
        return moviesList;
    }

    public By getGenreList() {
        return genreList;
    }

    public void navigateToMoviesSection() {
        WebElement item = findElementListByLocator(moviesSection).get(0);
        waitVisibility(item);
        click(item);
        waitVisibility(moviesSection);
    }

    public void navigateToAllMoviesTab() {
        WebElement element = findElementListByLocator(allMovies).get(0);
        waitVisibility(element);
        click(element);
    }

    public boolean areMoviesSortedAlphabetically() {
        List<WebElement> movies = findElementListByLocator(moviesList);
        String previous = String.valueOf(movies.get(0).getText().charAt(0));

        for (final WebElement currentElement : movies) {
            String current = currentElement.getText();
            if (current.compareTo(previous) < 0) {
                System.out.println(current + " is not ordered alphabetically");
                return false;
            }
            previous = current;
        }

        return true;
    }
}
