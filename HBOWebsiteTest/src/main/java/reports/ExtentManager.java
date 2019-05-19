package reports;

import com.relevantcodes.extentreports.ExtentReports;
import common.ApplicationProperties;

public class ExtentManager {

    private static ExtentReports report;

    public static synchronized ExtentReports getInstance() {
        if (report == null) {
            report = new ExtentReports(ApplicationProperties.TEST_OUTPUT_PATH);
        }
        return report;
    }
}
