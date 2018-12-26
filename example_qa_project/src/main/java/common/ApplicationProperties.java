package common;

public class ApplicationProperties {

    //Paths, names
    static final String webPageURL = "https://www.hbo.com";
    static final String LOCALHOST_URL = "http://localhost:4444/wd/hub";
    static final String PLATFORM_STRING = "platform";
    static final String CHROME_STRING = "chrome";
    static final String FIREFOX_STRING = "firefox";
    static final String BROWSER_VERSION_STRING = "browserversion";
    public static final String CHROME_VERSION = "69.0.3497.100";
    public static final String FIREFOX_VERSION = "63.0.3";
    public static final String TEST_OUTPUT_PATH = System.getProperty("user.dir") + "/test-output/testReport.html";

    //Error messages
    public static final String WRONG_TITLE = "Wrong title";
    public static final String MAIN_PAGE_SECTIONS_ARE_NOT_DISPLAYED = "Main page sections are not displayed properly.";
    public static final String MAIN_PAGE_HEADER_ITEMS_ARE_NOT_DISPLAYED = "Main page header items are not displayed properly.";
    public static final String ACTORS_NAMES_ARE_NOT_DISPLAYED_PROPERLY = "Actors names are not displayed properly.";
    public static final String SERIES_SECTION_DEFAULT_VIEW_IS_NOT_PRESENT = "Series section default view is not present";
    public static final String DISCOVER_LATEST_ITEMS_NOT_DISPLAYED = "Discover latest items are not displayed";
    public static final String DISCOVER_LATEST_ITEMS_HEADER_NOT_DISPLAYED = "Discover latest items header not displayed";
    public static final String SELECT_YOUR_SEASON_NOT_DISPLAYED = "Select your season not displayed";

    //Group names
    public static final String SMOKE = "smoke";

    //Classes
    public static final String MAIN_PAGE_TEST = "MainPageTest";
    public static final String SERIES_PAGE_TEST = "SeriesPageTest";

    //Individual tests
    public static final String VERIFY_MAIN_PAGE_SECTIONS_TEST = "verifyMainPageSections";
    public static final String VERIFY_MAIN_PAGE_HEADER_TEST = "verifyMainPageHeader";
    public static final String VERIFY_SERIES_PAGE_SECTIONS_TEST = "verifySeriesPageSectionsTest";

    //Logs
    public static final String VERIFY_HEADER_IS_DISPLAYED = "Verify header is displayed";
    public static final String VERIFY_SECTIONS_ARE_DISPLAYED = "Verify sections are displayed";
    public static final String VERIFY_HEADER_ITEMS_ARE_CLICKABLE = "Verify header items are clickable";
    public static final String VERIFY_SERIES_SECTION_DEFAULT_VIEW_IS_PRESENT = "Verify series section default view is present";
    public static final String VERIFY_DISCOVER_LATEST_ITEMS_ARE_DISPLAYED = "Verify discover latest items are displayed";
    public static final String VERIFY_DISCOVER_LATEST_ITEMS_HEADER_IS_DISPLAYED = "Verify discover latest items header is displayed";
    public static final String VERIFY_SELECT_YOUR_SEASON_IS_DISPLAYED = "Verify select your season is displayed";


}
