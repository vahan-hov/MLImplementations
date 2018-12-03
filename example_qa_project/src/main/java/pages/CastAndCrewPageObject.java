package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class CastAndCrewPageObject extends AbstractPage {

    private final By castText = By.xpath("//h3[contains(text(),'Cast')]");

    private final By actorsNames = By.xpath("/html/body/main/div[2]/div/div/div[1]/div/div/div");

    private final String castAndCrewPageURL = "https://www.hbo.com/game-of-thrones/cast-and-crew";

    public CastAndCrewPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToCastAndCrewSection() {
        try {
            driver.get(castAndCrewPageURL);
            Thread.sleep(5000);
            waitVisibility(castText);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isActorNamesDisplayed() {
        List<WebElement> names = findElementListByLocator(actorsNames);
        for (WebElement name : names) {
            if (!name.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}
