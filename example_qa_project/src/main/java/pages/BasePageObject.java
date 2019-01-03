package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;

public class BasePageObject extends AbstractPage {

    public BasePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean verifyElementByIsDisplayed(By elementBy, Integer count) {
        List<WebElement> items = findElementListByLocator(elementBy);
        if (null == count) {
            count = items.size();
        }

        for (int i = 0; i < count; ++i) {
            if (!items.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyElementByIsDisplayed(By elementBy) {
        WebElement element = findElementListByLocator(elementBy).get(0);
        return element.isDisplayed();
    }

    public void clickOnRandomItemInList(By elementBy, Integer maxRange) {
        List<WebElement> items = findElementListByLocator(elementBy);
        if (null == maxRange) {
            maxRange = items.size();
        }
        int rand = new Random().nextInt(maxRange);
        click(items.get(rand));
    }
}
