package com.pepperfry.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WishlistPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By firstProductWishlistIcon = By.xpath("//div[contains(@class,'vip-image-gallery')]//div[contains(@class,'pf-icon-wishlist') or contains(@class,'wishlist')]");
    By wishlistHeaderIcon = By.xpath("//pf-header-icons//div[contains(@class,'pf-icon-wishlist') or contains(@class,'wishlist')]");
    By wishlistContainer = By.xpath("//div[contains(@class,'wishlist') or contains(@class,'wishlistPage')]");
    By wishlistItems = By.xpath(
        "//div[contains(@class,'clip-prd-ttl') or " +
        "contains(@class,'wishlist-item') or " +
        "contains(@class,'vip-product-name') or " +
        "contains(@class,'product-title') or " +
        "contains(@class,'product-details')]"
    );

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Step 1: Add product to wishlist
    public void addProductToWishlist() {
        try {
            WebElement wishlistIcon = wait.until(ExpectedConditions.presenceOfElementLocated(firstProductWishlistIcon));
            closePopupsIfAny();

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", wishlistIcon);
            Thread.sleep(1000);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(wishlistIcon)).click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Normal click failed, retrying with JS click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishlistIcon);
            }

            System.out.println("Product added to wishlist successfully.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error adding product to wishlist: " + e.getMessage());
        }
    }

    // Step 2: Open wishlist page
    public void openWishlistPage() {
        try {
            WebElement wishlistIcon = wait.until(ExpectedConditions.presenceOfElementLocated(wishlistHeaderIcon));
            closePopupsIfAny();

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", wishlistIcon);
            Thread.sleep(1000);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(wishlistIcon)).click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted, retrying with JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishlistIcon);
            }

            wait.until(ExpectedConditions.presenceOfElementLocated(wishlistContainer));
            System.out.println("Navigated to wishlist page successfully.");
        } catch (Exception e) {
            System.out.println("Error opening wishlist: " + e.getMessage());
        }
    }

    


    // Step 3: Close unexpected popups
    private void closePopupsIfAny() {
        try {
            List<WebElement> popups = driver.findElements(
                By.xpath("//div[contains(@class,'popup') or contains(@class,'overlay') or contains(@class,'modal') or contains(@class,'close')]")
            );
            for (WebElement popup : popups) {
                if (popup.isDisplayed()) {
                    try {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popup);
                        System.out.println("Closed a popup.");
                        Thread.sleep(500);
                    } catch (Exception ignore) {}
                }
            }
        } catch (Exception e) {
            System.out.println("No popups found or could not close popups: " + e.getMessage());
        }
    }
}
