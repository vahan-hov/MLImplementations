package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Random;

public class GameOfThronesPageObject extends AbstractPage {

    private final By gameOfThronesFromEssentials = By.xpath("//div[text()='Game of Thrones']");
    private final By castAndCrewSection = By.xpath("//a[text()='cast & crew']");
    private final By castAndCrewSectionItemsList = By.xpath("//div[@class='components/ThumbnailWithText--container']");
    private final By itemsTitle = By.xpath("//h2[@class='components/Header--strongTitle']");
    private final By itemsImage = By.xpath("//img[@class='components/HeroImage--image  components/HeroImage--desktop']");
    private final By itemsDetails = By.xpath("//div[@class='modules/Article--externalHtmlContainer modules/Article--heavyHeader modules/Article--articleContent']");
    private final By castText = By.xpath("//h3[text()='Cast']");

    /**
     * Constructor
     */
    public GameOfThronesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to cast and crew sections of 'Game of Thrones' page
     */
    public void navigateToCastAndCrewSections() {
        WebElement item = findElementListByLocator(castAndCrewSection).get(0);
        waitVisibility(item);
        click(item);
    }

    /**
     * Check if user is in to cast and crew section of 'Game of Thrones' page
     */
    public boolean isUserInCastAndCrewSection() {
        return verifyElementByIsDisplayed(castText);
    }

    /**
     * Select 'game of thrones' option from 'Game of Thrones' page
     */
    public void selectGOT() {
        WebElement item = findElementListByLocator(gameOfThronesFromEssentials).get(0);
        waitVisibility(item);
        click(item);
    }

    /**
     * Select random item from cast and crew list from 'Game of Thrones' page
     */
    public void selectRandomItemFromCastAndCrewList() {
        WebElement seriesSectionElement = findElementListByLocator(castAndCrewSectionItemsList).get(new Random().nextInt(30));
        waitVisibility(seriesSectionElement);
        click(seriesSectionElement);
        waitVisibility(itemsImage);
    }

    /**
     * Check if cast and crew section items list is displayed in 'Game of Thrones' page
     */
    public boolean iscastAndCrewSectionItemsListDisplayed(Integer count) {
        return verifyElementByIsDisplayed(castAndCrewSectionItemsList, count);
    }

    /**
     * Check if items details is displayed in 'Game of Thrones' page
     */
    public boolean isItemsDetailsDisplayed() {
        return verifyElementByIsDisplayed(itemsDetails);
    }

    /**
     * Check if items image is displayed in 'Game of Thrones' page
     */
    public boolean isItemsImageDisplayed() {
        return verifyElementByIsDisplayed(itemsImage);
    }

    /**
     * Check if items title is displayed in 'Game of Thrones' page
     */
    public boolean isItemsTitleDisplayed() {
        return verifyElementByIsDisplayed(itemsTitle);
    }
}
