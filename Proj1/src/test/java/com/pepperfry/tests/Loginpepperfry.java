package com.pepperfry.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pepperfry.base.BaseTest;
import com.pepperfry.pages.loginPage;
import com.pepperfry.utilities.ExcelUtilities;
import com.pepperfry.utilities.Screenshots;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpepperfry extends BaseTest {

    static String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "logindata")
    public void verifylogin(String username, String password) throws IOException, InterruptedException {

        navigateurl("https://www.pepperfry.com/");
        test = extent.createTest("Login Test (OTP Flow) for: " + username);

        // Create login page object
        loginPage obj = new loginPage(driver);

        // Step 1: Open the login popup
        obj.openLoginPopup();
        test.pass("Opened Login Page");
        // Step 2: Enter email or mobile number
        obj.enterEmail(username);
        test.pass("Email Entered Successfully");
        // Step 3: Click Continue / Login with OTP
        obj.clickContinueButton();
        test.pass("Continue Button Clicked Successfully");

        // Step 4: Pause for manual OTP entry
        System.out.println("Please enter the OTP manually within 50 seconds...");
        Thread.sleep(50000); // wait for OTP entry
        test.pass("OTP Entered Successfully");

        // Step 5: Validate login success
        if (obj.isProfileVisible()) {
            test.pass("Login successful for: " + username);
            System.out.println("Successful");
            
        } else {
            String screenpath = Screenshots.Capture(driver, "LoginFailed_" + username);
            test.fail("Login failed for: " + username)
                .addScreenCaptureFromPath(screenpath);
            System.out.println("UnSuccessful");
        }
    }


    @DataProvider
    public Object[][] logindata() throws InvalidFormatException, IOException {
        String excelpath = projectpath + "\\src\\test\\resources\\TestData\\data.xlsx";
        System.out.println("Excel Path: " + excelpath);
        return ExcelUtilities.getdata(excelpath, "Sheet1");
    }
}
