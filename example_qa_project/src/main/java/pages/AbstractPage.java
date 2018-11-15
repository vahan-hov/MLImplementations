package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AbstractPage {

    protected WebDriverWait wait;

    protected RemoteWebDriver driver;

    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    protected void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    protected void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    protected void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    protected String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    protected WebElement findElementByLocator(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy);
    }

    protected List<WebElement> findElementListByLocator(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElements(elementBy);
    }
}
