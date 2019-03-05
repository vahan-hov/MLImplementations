package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

/**
 * This page contains main functionalities used in all other pages
 */
public class AbstractPage {

    private final WebDriverWait wait;
    private final RemoteWebDriver driver;

    /**
     * Initialize driver
     */
    AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Wait for element to appear on the screen
     */
    void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    /**
     * Wait for element to appear on the screen
     */
    void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    /**
     * Wait for element list to appear on the screen
     */
    void waitVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Click on element
     */
    protected void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    /**
     * Click on element
     */
    void click(WebElement element) {
        waitVisibility(element);
        element.click();
    }

    /**
     * Enter text in element
     */
    protected void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    /**
     * Read text from element
     */
    protected String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    /**
     * Scroll until element is visible
     */
    protected void scrollIntoElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    /**
     * Find element on the screen
     */
    protected WebElement findElementByLocator(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy);
    }

    /**
     * Find element by locator and check if it is displayed
     */

    boolean findElementByLocatorAndCheckIsDisplayed(By elementBy) {
        WebElement element = driver.findElement(elementBy);
        waitVisibility(element);
        return element.isDisplayed();
    }

    /**
     * Find element by locator and click
     */
    protected void findElementByLocatorAndClick(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    /**
     * Find element list on the screen
     */
    List<WebElement> findElementListByLocator(By elementBy) {
        return driver.findElements(elementBy);
    }

    /**
     * Get URL of current page
     */
    String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Check if a given list has given number of elements displayed.
     */
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

    /**
     * Check if a given list's first element is displayed.
     */
    public boolean verifyElementByIsDisplayed(By elementBy) {
        WebElement element = findElementListByLocator(elementBy).get(0);
        waitVisibility(element);
        return element.isDisplayed();
    }

    /**
     * Click on a random element of a given list.
     */
    public void clickOnRandomItemInList(By elementBy, Integer maxRange) {
        List<WebElement> items = findElementListByLocator(elementBy);
        if (null == maxRange || maxRange > items.size()) {
            maxRange = items.size();
        }
        int rand = new Random().nextInt(maxRange);
        click(items.get(rand));
    }
}
