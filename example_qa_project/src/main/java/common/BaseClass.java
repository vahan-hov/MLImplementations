package common;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import reports.ExtentManager;
import reports.ExtentTestManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class BaseClass extends TestListenerAdapter {
    private static final ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<RemoteWebDriver>();

    protected static RemoteWebDriver driver() {
        return drivers.get();
    }

    /**
     * This method runs before every method and creates a new driver (chrome or firefox) and navigates to the URL of website.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"os", "browser", "browserVersion"})
    public void setupTestBeforeMethod(@Optional String os, @Optional String browser, @Optional String browserVersion, Method method) throws MalformedURLException {
        System.out.println("BeforeMethod ============================");
        ChromeOptions chromeOptions = null;
        FirefoxOptions firefoxOptions = null;
        Platform platform = Platform.fromString(os.toUpperCase());
        if (browser.equalsIgnoreCase(ApplicationProperties.CHROME_STRING)) {
            chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            chromeOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        } else if (browser.equalsIgnoreCase(ApplicationProperties.FIREFOX_STRING)) {
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability(ApplicationProperties.PLATFORM_STRING, platform);
            firefoxOptions.setCapability(ApplicationProperties.BROWSER_VERSION_STRING, browserVersion);
        }

        RemoteWebDriver driver = null;

        if (chromeOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), chromeOptions);
        } else if (firefoxOptions != null) {
            driver = new RemoteWebDriver(new URL(ApplicationProperties.LOCALHOST_URL), firefoxOptions);
        }

        drivers.set(driver);
        driver().navigate().to(ApplicationProperties.webPageURL);
        ExtentTestManager.startTest(method.getName());
    }

    /**
     * This method runs after each test and quites browser window and sets driver to 'null'.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.isSuccess()) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test failed");
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
            ExtentTestManager.getTest().setDescription(Arrays.toString(result.getThrowable().getStackTrace()));
        }

        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();

        RemoteWebDriver driver = driver();
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentManager.getInstance().flush();
    }
}