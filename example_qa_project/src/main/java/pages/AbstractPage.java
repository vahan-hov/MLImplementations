package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * This page contains main functionalities used in all other pages
 */
public class AbstractPage {

    protected WebDriverWait wait;

    protected RemoteWebDriver driver;

    /**
     * Initialize driver
     */
    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Wait for element to appear on the screen
     */
    protected void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    /**
     * Click on element
     */
    protected void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
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
     * Find element on the screen
     */
    protected WebElement findElementByLocator(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy);
    }

    /**
     * Find element list on the screen
     */
    protected List<WebElement> findElementListByLocator(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElements(elementBy);
    }
}
