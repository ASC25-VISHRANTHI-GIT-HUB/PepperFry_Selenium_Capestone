package com.pepperfry.pages;
 
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
 
public class loginPage {
 
    WebDriver driver;

    WebDriverWait wait;
 
    // Locators

    By loginIcon = By.xpath("//div[contains(@class,'pf-icon pf-icon-user') or contains(@class,'header-icon-user')]");

    By emailField = By.xpath("//input[contains(@placeholder,'Email') or contains(@placeholder,'Phone') or @type='email']");

    By continueButton = By.xpath("html/body/pf-modal/div/div/div/pf-authentication/div/section/pf-signup-login/section[2]/div/section/form/pf-button/button");

    By profileIcon = By.xpath("html/body/app-root/pf-header/header/div[2]/pf-header-main/div/div/div/div[4]/pf-header-icons/div/div/div/div");
    //By SignUp=By.xpath("html/body/app-root/pf-header/header/div[2]/pf-header-main/div/div/div/div[4]/pf-header-icons/div/div/div/div/div");
 
    public loginPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }
 
    public void openLoginPopup() {

        try {

            wait.until(ExpectedConditions.elementToBeClickable(loginIcon)).click();

            System.out.println("Clicked on Login icon.");

        } catch (Exception e) {

            System.out.println("Could not click Login icon - maybe already on login popup." + e.getMessage());

        }

    }
 
    public void enterEmail(String username) {

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(username);

            System.out.println("Entered email/phone: " + username);

        } catch (Exception e) {

            System.out.println("Could not find the email input field: " + e.getMessage());

        }

    }
 
    public void clickContinueButton() {

        try {

            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
            
            System.out.println("Clicked Continue/Login with OTP.");

        } catch (Exception e) {

            System.out.println("Could not click Continue button: " + e.getMessage());

        }

    }
 
    public boolean isProfileVisible() {

    	try {
    	        wait.withTimeout(Duration.ofSeconds(15));

    	        // Wait for profile icon to be visible
    	        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));

    	        // Get the text inside the profile icon or greeting area
    	        String profileText = profile.getText().toLowerCase();

    	        // Check if it contains the expected greeting
    	        if (profileText.contains("hi") && profileText.contains("vishranthi")) {
    	            System.out.println("Found greeting: " + profileText + " - Login successful!");
    	            return true;
    	        } else {
    	            System.out.println("Profile visible but greeting not matched - Login failed.");
    	            return false;
    	        }

    	    } catch (Exception e) {
    	        System.out.println("Login Unsuccessful: " + e.getMessage());
    	        return false;
    	    }



    }

}

 