package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SeriesPageObject extends AbstractPage {
    private final By seriesSection = By.xpath("//a[text() = 'Series']");
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
    private final By featuredSeries = By.xpath("//a[text()='Featured Series']");

    /**
     * Call parents super method
     */
    public SeriesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to series section of 'Series' page
     */
    public void navigateToSeriesSection() {
        WebElement seriesSectionElement = findElementListByLocator(seriesSection).get(0);
        waitVisibility(seriesSectionElement);
        click(seriesSectionElement);
    }

    /**
     * Check if user is in 'Series' page
     */
    public boolean isUserInSeriesSection() {
        return verifyElementByIsDisplayed(featuredSeries);
    }

    /**
     * select item from discover items section
     */
    public void selectItemInDiscoverItemSection() {
        List<WebElement> items = findElementListByLocator(discoverTheLatestList);
        click(items.get(0));
    }

    /**
     * Check if user has left 'Series' section
     */
    public boolean isUserHasLeftSeriesSection() throws InterruptedException {
        Thread.sleep(2000);
        String seriesPageURL = "https://www.hbo.com/series";
        return !getCurrentURL().equals(seriesPageURL);
    }

    /**
     * Click on 'popular searches' element of 'Series' page
     */
    public void clickOnPopularSearches(Integer maxRange) {
        clickOnRandomItemInList(popularSearchesList, maxRange);
    }

    /**
     * Check if 'popular searches' element is displayed in 'Series' page
     */
    public boolean isPopularSearchesDisplayed() {
        return verifyElementByIsDisplayed(popularSearches);
    }

    /**
     * Check if 'essentials series' element is displayed in 'Series' page
     */
    public boolean isEssentialSeriesDisplayed() {
        return verifyElementByIsDisplayed(essentialSeries);
    }

    /**
     * Check if 'discover the latest' element is displayed in 'Series' page
     */
    public boolean isDiscoverTheLatestListDisplayed(Integer count) {
        return verifyElementByIsDisplayed(discoverTheLatestList, count);
    }

    /**
     * Check if 'about discover item' element is displayed in 'Series' page
     */
    public boolean isAboutDiscoverItemHeaderDisplayed() {
        return verifyElementByIsDisplayed(aboutDiscoverItemHeader);
    }

    /**
     * Check if 'cast and crew discover item header' element is displayed in 'Series' page
     */
    public boolean isCastAndCrewDiscoverItemHeaderDisplayed() {
        return verifyElementByIsDisplayed(castAndCrewDiscoverItemHeader);
    }

    /**
     * Check if 'shop discover item header' element is displayed in 'Series' page
     */
    public boolean isShopDiscoverItemHeaderDisplayed() {
        return verifyElementByIsDisplayed(shopDiscoverItemHeader);
    }

    /**
     * Check if 'select your season' element is displayed in 'Series' page
     */
    public boolean isSelectYourSeasonDisplayed() {
        return verifyElementByIsDisplayed(selectYourSeason);
    }

    /**
     * Check if 'popular item header' element is displayed in 'Series' page
     */
    public boolean isPopularItemHeaderDisplayed() {
        return verifyElementByIsDisplayed(popularItemHeader);
    }

    /**
     * Check if 'popular item detail' element is displayed in 'Series' page
     */
    public boolean isPopularItemDetailDisplayed() {
        return verifyElementByIsDisplayed(popularItemDetail);
    }
}
