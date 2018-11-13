package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageObject extends AbstractPage {

    private final String pageTitle = "My Store";

    private final By womentButtonHeader = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a");

    private final By dressesButtonHeader = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");

    private final By tShirtsButtonHeader = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a");

    private final By womentButtonFooter = By.xpath("//h4[contains(text(), 'Categories')]");

    private final By informationButtonFooter = By.xpath("//h4[contains(text(), 'Information')]");

    private final By accountButtonFooter = By.xpath("//a[contains(text(), 'My account')]");

    public MainPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleGOTDisplayed() {
        return pageTitle.equals(driver.getTitle());
    }

    public boolean isHeaderDisplayed() {
        return findElementByLocator(womentButtonHeader).isDisplayed() &&
                findElementByLocator(dressesButtonHeader).isDisplayed() &&
                findElementByLocator(tShirtsButtonHeader).isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return findElementByLocator(womentButtonFooter).isDisplayed() &&
                findElementByLocator(informationButtonFooter).isDisplayed() &&
                findElementByLocator(accountButtonFooter).isDisplayed();
    }


}