package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetHBOPageObject extends AbstractPage {

    private final By getHBO = By.xpath("//*[text() = 'Get HBO']");
    private final By NoTVPackage = By.xpath("//td[text() = 'No TV Package']");
    private final By TVPackage = By.xpath("//td[text() = 'TV Package']");
    private final By DigitalSubscription = By.xpath("//td[text() = 'Digital Subscription']");
    private final By tableItems = By.xpath("//*[@class = 'modules/OrderChart--desktopItem']");

    public GetHBOPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public By getNoTVPackage() {
        return NoTVPackage;
    }

    public By getTVPackage() {
        return TVPackage;
    }

    public By getDigitalSubscription() {
        return DigitalSubscription;
    }

    public By getTableItems() {
        return tableItems;
    }

    public void navigateToGetHBOSection() {
        WebElement item = findElementListByLocator(getHBO).get(0);
        waitVisibility(item);
        click(item);
    }
}
