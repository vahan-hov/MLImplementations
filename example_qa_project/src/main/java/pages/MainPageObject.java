package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class MainPageObject extends AbstractPage {

    private final String pageTitle = "Game of Thrones - Official Website for the HBO Series";

    private final By mainPageHeader = By.xpath("/html/body/main/div[1]/div/div/header/div[1]/div[3]/div/div/div/ul/li");

    private final By mainPageImage = By.xpath("/html/body/main/div[2]/div/div/div[1]/div[1]/img[2]");

    /**
     * Call parents super method
     */
    public MainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Check if title is displayed
     */
    public boolean isTitleDisplayed() {
        return pageTitle.equals(driver.getTitle());
    }

    /**
     * Check if header items are displayed
     */
    public boolean isHeaderItemsDisplayed() {
        List<WebElement> headerItems = findElementListByLocator(mainPageHeader);
        for (WebElement item : headerItems) {
            if (!item.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if image is displayed
     */
    public boolean isImageDisplayed() {
        return findElementByLocator(mainPageImage).isDisplayed();
    }
}