package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class DressesPageObject extends AbstractPage {

    private final By casualDressesSubCategory = By.xpath("//*[@id='subcategories']/ul/li[1]");

    private final By eveningDressesSubCategory = By.xpath("//*[@id='subcategories']/ul/li[2]");

    private final By summerDressesSubCategory = By.xpath("//*[@id='subcategories']/ul/li[3]");

    private final By dressesButtonHeader = By.xpath("//*[@id='block_top_menu']/ul/li[2]/a");

    private final By dressCategoriesList = By.xpath("//*[@id='categories_block_left']/div/ul/li");

    public DressesPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToDressesSection() {
        click(dressesButtonHeader);
        waitVisibility(casualDressesSubCategory);
    }

    public boolean isSubcategoriesDisplayed() {
        return findElementByLocator(casualDressesSubCategory).isDisplayed() &&
                findElementByLocator(eveningDressesSubCategory).isDisplayed() &&
                findElementByLocator(summerDressesSubCategory).isDisplayed();
    }

    public boolean isDressesCategoriesDisplayed() {
        List<WebElement> categories = findElementListByLocator(dressCategoriesList);
        for (WebElement category : categories) {
            if (!category.isDisplayed()) {
                return false;
            }
        }
        return true;
    }
}
