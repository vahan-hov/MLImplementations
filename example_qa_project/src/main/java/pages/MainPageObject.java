package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPageObject extends AbstractPage {

    private final By inHBONews = By.xpath("//*[text() = 'In HBO News']");

    private final By newMoviesToStream = By.xpath("//*[text() = 'New Movies to Stream']");

    private final By hereBeforeYouKnowIt = By.xpath("//*[text() = 'Here Before You Know It']");

    private final By moviesLeavingSoon = By.xpath("//*[text() = 'Movies Leaving Soon']");

    private final By headerItemsList = By.xpath("/html/body/main/div[1]/div/div/header/div[1]/div/div[2]/div/div/ul/li");


    /**
     * Call parents super method
     */
    public MainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Check if main page sections are displayed
     */
    public boolean isMainPageSectionsAreDisplayed() {
        return findElementByLocatorAndCheckIsDisplayed(hereBeforeYouKnowIt) &&
                findElementByLocatorAndCheckIsDisplayed(moviesLeavingSoon) &&
                findElementByLocatorAndCheckIsDisplayed(inHBONews) &&
                findElementByLocatorAndCheckIsDisplayed(newMoviesToStream);
    }

    /**
     * Check if main page header items are displayed
     */
    public boolean isMainPageHeaderItemsAreDisplayed() {
        for (WebElement item : findElementListByLocator(headerItemsList)) {
            if (!item.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if User can navigate to every header item
     */
    public boolean isUserCanNavigateToEveryHeaderItem() {
        try {
            WebElement item = findElementListByLocator(headerItemsList).get(0);
            waitVisibility(item);
            item.click();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}