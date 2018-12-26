package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SeriesPageObject extends AbstractPage {

    private final By seriesSection = By.xpath("//a[text() = 'Series']");

    private final WebElement seriesSectionElement = findElementListByLocator(seriesSection).get(0);

    private final By discoverTheLatest = By.xpath("//*[text() = 'Discover the Latest']");

    private final By popularSearches = By.xpath("//*[text() = 'Popular Searches']");

    private final By essentialSeries = By.xpath("//*[text() = 'Essential Series']");

    private final By discoverTheLatestList = By.xpath("//*[@class = 'components/CardImage--imageContainer']");

    private final By aboutDiscoverItemHeader = By.xpath("//a[text()='About']");

    private final By castAndCrewDiscoverItemHeader = By.xpath("//a[text()='Cast & Crew']");

    private final By shopDiscoverItemHeader = By.xpath("//a[text()='SHOP']");

    private final By selectYourSeason = By.xpath("//*[text()='Select Your Season']");

    /**
     * Call parents super method
     */
    public SeriesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to series section
     */
    public void navigateToSeriesSection() {
        waitVisibility(seriesSectionElement);
        click(seriesSectionElement);
        waitVisibility(discoverTheLatest);
    }

    /**
     * Check if series sections default view is present
     */
    public boolean isSeriesSectionDefaultViewPresent() throws InterruptedException {
        Thread.sleep(5000);
        waitVisibility(findElementListByLocator(discoverTheLatest));
        WebElement discoverTheLatestElement = findElementListByLocator(discoverTheLatest).get(0);
        WebElement popularSearchesElement = findElementListByLocator(popularSearches).get(0);
        WebElement essentialSeriesElement = findElementListByLocator(essentialSeries).get(0);
        return discoverTheLatestElement.isDisplayed() &&
                popularSearchesElement.isDisplayed() &&
                essentialSeriesElement.isDisplayed();
    }

    /**
     * Check if discover latest items are displayed
     */
    public boolean isDiscoverLatestItemsDisplayed() {
        List<WebElement> items = findElementListByLocator(discoverTheLatestList);
        for (int i = 0; i < 4; ++i) {
            if (!items.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * select item from discover items section
     */
    public void selectItemInDiscoverItemSection() {
        List<WebElement> items = findElementListByLocator(discoverTheLatestList);
        click(items.get(0));
    }

    /**
     * Check if discover item header is displayed
     */
    public boolean isDiscoverItemHeaderDisplayed() {
        WebElement aboutDiscoverItemHeaderElement = findElementListByLocator(aboutDiscoverItemHeader).get(0);
        WebElement castAndCrewDiscoverItemHeaderElement = findElementListByLocator(castAndCrewDiscoverItemHeader).get(0);
        WebElement shopDiscoverItemHeaderElement = findElementListByLocator(shopDiscoverItemHeader).get(0);
        return aboutDiscoverItemHeaderElement.isDisplayed() &&
                castAndCrewDiscoverItemHeaderElement.isDisplayed() &&
                shopDiscoverItemHeaderElement.isDisplayed();
    }

    /**
     * Check if select season is displayed
     */
    public boolean isSelectSeasonDisplayed() {
        return findElementByLocatorAndCheckIsDisplayed(selectYourSeason);
    }

    public boolean isSectionsDisplayed() {
        return findElementByLocatorAndCheckIsDisplayed(discoverTheLatest);
    }
}
