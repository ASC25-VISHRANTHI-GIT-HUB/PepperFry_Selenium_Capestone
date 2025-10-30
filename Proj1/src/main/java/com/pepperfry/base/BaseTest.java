package com.pepperfry.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pepperfry.utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    // Make driver static so it persists across test classes (Login -> Search)
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    // Initialize Extent Report once before the suite starts
    @BeforeSuite(alwaysRun = true)
    public void setupReport() {
        extent = ExtentManager.getinstance();
        System.out.println("Extent report initialized.");
    }

    // Set up WebDriver only once before all tests in the suite
    @BeforeTest(alwaysRun = true)
    public void setupDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("Chrome browser launched successfully.");
        }
    }

    // Helper method to navigate to a given URL
    public void navigateurl(String url) {
        driver.get(url);
        System.out.println("Navigated to: " + url);
    }

    // Do NOT close after each test — only once after the full suite finishes
    @AfterTest(alwaysRun = true)
    public void afterAllTests() {
        System.out.println("Completed test execution for current test block.");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error while closing browser: " + e.getMessage());
        }

        // Flush the report once after all tests are done
        if (extent != null) {
            extent.flush();
            System.out.println("Extent report flushed.");
        }
    }
}
