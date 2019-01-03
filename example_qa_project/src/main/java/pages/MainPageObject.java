package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPageObject extends AbstractPage {

    private final By inHBONews = By.xpath("//*[text() = 'In HBO News']");
    private final By hereBeforeYouKnowIt = By.xpath("//*[text() = 'Here Before You Know It']");
    private final By headerItemsList = By.xpath("/html/body/main/div[1]/div/div/header/div[1]/div/div[2]/div/div/ul/li");

    /**
     * Call parents super method
     */
    public MainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getInHBONews() {
        return inHBONews;
    }

    public By getHereBeforeYouKnowIt() {
        return hereBeforeYouKnowIt;
    }

    public By getHeaderItemsList() {
        return headerItemsList;
    }
}