package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FooterTestPageObject extends AbstractPage {

    private final By about = By.xpath("//a[text()='About']");
    private final By waysToGet = By.xpath("//a[text()='Ways to Get']");
    private final By help = By.xpath("//a[text()='Help']");
    private final By shop = By.xpath("//a[text()='Shop']");
    private final By HBOInspires = By.xpath("//a[text()='HBO Inspires']");

    /**
     * Constructor
     */
    public FooterTestPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Check if 'About' link is displayed in footer
     */
    public boolean verifyAboutIsDisplayed() {
        return verifyElementByIsDisplayed(about);
    }

    /**
     * Check if 'HBO inspires' link is displayed in footer
     */
    public boolean verifyHboInspiresIsDisplayed() {
        return verifyElementByIsDisplayed(HBOInspires);
    }

    /**
     * Check if 'Help' link is displayed in footer
     */
    public boolean verifyHelpIsDisplayed() {
        return verifyElementByIsDisplayed(help);
    }

    /**
     * Check if 'Shop' link is displayed in footer
     */
    public boolean verifyShopIsDisplayed() {
        return verifyElementByIsDisplayed(shop);
    }

    /**
     * Check if 'Ways to get' link is displayed in footer
     */
    public boolean verifyWaysToGetIsDisplayed() {
        return verifyElementByIsDisplayed(waysToGet);
    }
}
