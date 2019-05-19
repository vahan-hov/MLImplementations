//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.util.List;
//import java.util.Random;
//
//public class SpecialsPageObject extends AbstractPage {
//
//    private final By discoverTheLatest = By.xpath("//*[text() = 'Discover the Latest']");
//    private final By fantasticallyFunnyFemales = By.xpath("//*[text() = 'Fantastically Funny Females']");
//    private final By letTheGamesBegin = By.xpath("//*[text() = 'Let the Games Begin']");
//    private final By essentialSpecials = By.xpath("//*[text() = 'Essential Specials']");
//    private final By specials = By.xpath("//*[text() = 'Specials']");
//    private final By featuredSpecials = By.xpath("//a[text() = 'Featured Specials']");
//    private final By allSpecials = By.xpath("//a[text()='All Specials']");
//    private final By discoverLatestList = By.xpath("//*[@class = 'modules/PromotionalCard--cardContainer promotional-card-container']");
//    private final By discoverItemImage = By.xpath("//*[@class = '__player-placeholder-background']");
//    private final By allSpecialItemsList = By.xpath("//*[@class = 'modules/cards/CatalogCard--container modules/cards/SamplingCatalogCard--container modules/cards/CatalogCard--notIE modules/cards/CatalogCard--desktop']");
//    private final By allSpecialItemsImage = By.xpath("//*[@class = 'components/HeroImage--heroImageContainer']");
//    private final By allSpecialItemsTitle = By.xpath("//*[@class = 'modules/InfoSlice--assetTitle']");
//    private final By aboutTab = By.xpath("//a[text()='About']");
//
//    public SpecialsPageObject(RemoteWebDriver driver) {
//        super(driver);
//    }
//
//    public void navigateToSpecialsSection() {
//        WebElement item = findElementListByLocator(specials).get(0);
//        waitVisibility(item);
//        click(item);
//    }
//
//    public boolean isUserInSpecialsSection() {
//        return verifyElementByIsDisplayed(featuredSpecials);
//    }
//
//    public void navigateToAllSpecialsTab() {
//        WebElement element = findElementListByLocator(allSpecials).get(0);
//        waitVisibility(element);
//        click(element);
//    }
//
//    public void selectItemFromDiscoverLatestList() throws InterruptedException {
//        List<WebElement> list = findElementListByLocator(discoverLatestList);
//        list.get(new Random().nextInt(2)).click();
//        Thread.sleep(2000);
//    }
//
//    public void selectItemFromSpecialsList() {
//        List<WebElement> list = findElementListByLocator(allSpecialItemsList);
//        list.get(new Random().nextInt(30)).click();
//    }
//
//    public boolean isDiscoverTheLatestDisplayed() {
//        return verifyElementByIsDisplayed(discoverTheLatest);
//    }
//
//    public boolean isLetTheGamesBeginDisplayed() {
//        return verifyElementByIsDisplayed(letTheGamesBegin);
//    }
//
//    public boolean isEssentialSpecialsDisplayed() {
//        return verifyElementByIsDisplayed(essentialSpecials);
//    }
//
//    public boolean isFantasticallyFunnyFemalesDisplayed() {
//        return verifyElementByIsDisplayed(fantasticallyFunnyFemales);
//    }
//
//    public boolean isDiscoverItemImageDisplayed() {
//        return verifyElementByIsDisplayed(discoverItemImage);
//    }
//
//    public boolean isAllSpecialItemsListDisplayed() {
//        return verifyElementByIsDisplayed(allSpecialItemsList, 20);
//    }
//
//    public boolean isAllSpecialItemsImageDisplayed() {
//        return verifyElementByIsDisplayed(allSpecialItemsImage);
//    }
//
//    public boolean isAllSpecialItemsTitleDisplayed() {
//        return verifyElementByIsDisplayed(allSpecialItemsTitle);
//    }
//}
