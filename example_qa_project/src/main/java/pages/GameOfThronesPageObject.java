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

    public GameOfThronesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getItemsTitle() {
        return itemsTitle;
    }

    public By getItemsImage() {
        return itemsImage;
    }

    public By getItemsDetails() {
        return itemsDetails;
    }

    public By getCastAndCrewSectionItemsList() {
        return castAndCrewSectionItemsList;
    }

    public void navigateToCastAndCrewSections() {
        WebElement item = findElementListByLocator(castAndCrewSection).get(0);
        waitVisibility(item);
        click(item);
    }

    public void selectGOT() {
        WebElement item = findElementListByLocator(gameOfThronesFromEssentials).get(0);
        waitVisibility(item);
        click(item);
    }

    public void selectRandomItemFromCastAndCrewList() {
        WebElement seriesSectionElement = findElementListByLocator(castAndCrewSectionItemsList).get(new Random().nextInt(30));
        waitVisibility(seriesSectionElement);
        click(seriesSectionElement);
        waitVisibility(itemsImage);
    }
}
