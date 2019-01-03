package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SeriesPageObject extends AbstractPage {

    private final By seriesSection = By.xpath("//a[text() = 'Series']");
    private final By discoverTheLatest = By.xpath("//*[text() = 'Discover the Latest']");
    private final By popularSearches = By.xpath("//*[text() = 'Popular Searches']");
    private final By essentialSeries = By.xpath("//*[text() = 'Essential Series']");
    private final By discoverTheLatestList = By.xpath("//*[@class = 'components/CardImage--imageContainer']");
    private final By aboutDiscoverItemHeader = By.xpath("//a[text()='About']");
    private final By castAndCrewDiscoverItemHeader = By.xpath("//a[text()='Cast & Crew']");
    private final By shopDiscoverItemHeader = By.xpath("//a[text()='SHOP']");
    private final By selectYourSeason = By.xpath("//*[text()='Select Your Season']");
    private final By popularSearchesList = By.xpath("//*[@class = 'components/CollectionItem--container']//div[@class='components/Thumbnail--thumbnail components/Thumbnail--landscape components/Thumbnail--large components/CollectionItem--thumbnailImage']");
    private final By popularItemHeader = By.xpath("//*[@class = 'components/Header--strongTitle']");
    private final By popularItemDetail = By.xpath("//*[@class = 'modules/Article--externalHtmlContainer modules/Article--heavyHeader modules/Article--articleContent']");

    /**
     * Call parents super method
     */
    public SeriesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getSeriesSection() {
        return seriesSection;
    }

    public By getDiscoverTheLatest() {
        return discoverTheLatest;
    }

    public By getPopularSearches() {
        return popularSearches;
    }

    public By getEssentialSeries() {
        return essentialSeries;
    }

    public By getDiscoverTheLatestList() {
        return discoverTheLatestList;
    }

    public By getAboutDiscoverItemHeader() {
        return aboutDiscoverItemHeader;
    }

    public By getCastAndCrewDiscoverItemHeader() {
        return castAndCrewDiscoverItemHeader;
    }

    public By getShopDiscoverItemHeader() {
        return shopDiscoverItemHeader;
    }

    public By getSelectYourSeason() {
        return selectYourSeason;
    }

    public By getPopularSearchesList() {
        return popularSearchesList;
    }

    public By getPopularItemHeader() {
        return popularItemHeader;
    }

    public By getPopularItemDetail() {
        return popularItemDetail;
    }

    /**
     * Navigate to series section
     */
    public void navigateToSeriesSection() {
        WebElement seriesSectionElement = findElementListByLocator(seriesSection).get(0);
        waitVisibility(seriesSectionElement);
        click(seriesSectionElement);
        waitVisibility(discoverTheLatest);
    }

    /**
     * select item from discover items section
     */
    public void selectItemInDiscoverItemSection() {
        List<WebElement> items = findElementListByLocator(discoverTheLatestList);
        click(items.get(0));
    }

    public boolean isUserHasLeftSeriesSection() throws InterruptedException {
        Thread.sleep(2000);
        String seriesPageURL = "https://www.hbo.com/series";
        return !getCurrentURL().equals(seriesPageURL);
    }
}
