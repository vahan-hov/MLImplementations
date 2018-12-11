package config;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTestNG {
    public static boolean executed = false;
    public TestNG runTestNGTest(Map<String, String> testngParams, String[] classesArr, int threadCount) {   //Create an instance on TestNG
        TestNG myTestNG = new TestNG();

        //Print the parameter values
        for (Map.Entry<String, String> entry : testngParams.entrySet()) {
            System.out.println("====== " + entry.getKey() + " => " + entry.getValue() + " ============================================================");
        }
        System.out.println("====== thread count " + threadCount + " ============================================================");
        for (String className : classesArr) {
            System.out.println("class " + className);
        }

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite mySuite = new XmlSuite();
        mySuite.setName("MySuite");
        mySuite.setParallel(XmlSuite.ParallelMode.METHODS);

        //Create an instance of XmlTest and assign a name for it.
        XmlTest myTest = null;
        XmlTest myTest2 = null;
        if (testngParams.containsKey("browser1")) {

            Map<String, String> paramsFor1 = new HashMap<String, String>(testngParams);
            paramsFor1.remove("browser2");
            paramsFor1.remove("browserVersion2");

            String browser = testngParams.get("browser1");
            paramsFor1.put("browser", browser);
            paramsFor1.remove("browser1");

            String browserVersion = testngParams.get("browserVersion1");
            paramsFor1.put("browserVersion", browserVersion);
            paramsFor1.remove("browserVersion1");

            myTest = new XmlTest(mySuite);
            myTest.setName("MyTest1");
            myTest.setParameters(paramsFor1);


            Map<String, String> paramsFor2 = new HashMap<String, String>(testngParams);
            paramsFor2.remove("browser1");
            paramsFor2.remove("browserVersion1");

            browser = testngParams.get("browser2");
            paramsFor2.put("browser", browser);
            paramsFor2.remove("browser2");

            browserVersion = testngParams.get("browserVersion2");
            paramsFor2.put("browserVersion", browserVersion);
            paramsFor2.remove("browserVersion2");

            myTest2 = new XmlTest(mySuite);
            myTest2.setName("MyTest2");
            myTest2.setParameters(paramsFor2);

            System.out.println("results");
            for (String key : paramsFor1.keySet()) {
                String value = paramsFor1.get(key);
                System.out.println(key + " : " + value);
            }

            for (String key : paramsFor2.keySet()) {
                String value = paramsFor2.get(key);
                System.out.println(key + " : " + value);
            }

        } else {
            myTest = new XmlTest(mySuite);
            myTest.setName("MyTest");

            //Add any parameters that you want to set to the Test.
            myTest.setParameters(testngParams);
        }

        //Create a list which can contain the classes that you want to run.
        List<XmlClass> myClasses = new ArrayList<XmlClass>();
        for (String item : classesArr) {
            myClasses.add(new XmlClass("tests." + item));
        }

        //Assign that to the XmlTest Object created earlier.
        myTest.setXmlClasses(myClasses);
        if (myTest2 != null) {
            myTest2.setXmlClasses(myClasses);
        }

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> myTests = new ArrayList<XmlTest>();
        myTests.add(myTest);
        if (myTest2 != null) {
            myTests.add(myTest2);
        }

        //add the list of tests to your Suite.
        mySuite.setTests(myTests);

        //Add the suite to the list of suites.
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        mySuites.add(mySuite);

        //Set the list of Suites to the testNG object you created earlier.
        myTestNG.setXmlSuites(mySuites);
        mySuite.setFileName("virtual.xml");
        mySuite.setThreadCount(threadCount);
//        myTestNG.run();

        //Create physical XML file based on the virtual XML content
        for (XmlSuite suite : mySuites) {
            createXmlFile(suite);
        }
        System.out.println("File created successfully.====================================================");
        return myTestNG;
    }

    //This method will create an Xml file based on the XmlSuite data
    private void createXmlFile(XmlSuite mSuite) {
        FileWriter writer;
        try {
            writer = new FileWriter(new File("virtual.xml"));
            writer.write(mSuite.toXml());
            writer.flush();
            writer.close();
            System.out.println(new File("virtual.xml").getAbsolutePath());
        } catch (IOException e) {
            System.out.println("exception ###################################################################################");
            e.printStackTrace();
        }
    }

    //Main Method
//    public static void main(String args[]) {
//        config.DynamicTestNG dt = new config.DynamicTestNG();
//
//        //This Map can hold your testng Parameters.
//        Map<String, String> testngParams = new HashMap<String, String>();
//        testngParams.put("","device1esktop");
//        testngParams.put("","device2obile");
//        testngParams.put("","device3ablet");
//        dt.runTestNGTest(testngParams);
//    }
}