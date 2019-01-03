package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FooterTestPageObject extends AbstractPage {

    private final By about = By.xpath("//a[text()='About']");
    private final By waysToGet = By.xpath("//a[text()='Ways to Get']");
    private final By help = By.xpath("//a[text()='Help']");
    private final By shop = By.xpath("//a[text()='Shop']");
    private final By HBOInspires = By.xpath("//a[text()='HBO Inspires']");

    public FooterTestPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getAbout() {
        return about;
    }

    public By getWaysToGet() {
        return waysToGet;
    }

    public By getHelp() {
        return help;
    }

    public By getShop() {
        return shop;
    }

    public By getHBOInspires() {
        return HBOInspires;
    }
}
