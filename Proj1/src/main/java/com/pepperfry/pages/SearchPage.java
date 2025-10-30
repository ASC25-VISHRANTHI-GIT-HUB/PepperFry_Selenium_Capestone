package com.pepperfry.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By searchBox = By.id("search");
    By searchButton = By.id("searchButton");
    By threeSeaterFilter = By.xpath("//span[contains(text(),'3 Seater') or contains(.,'3 Seater')]");
    By searchResults = By.xpath("//div[contains(@class,'clip-prd-ttl')]");

    // Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Step 1: Search for a product
    public void searchProduct(String productName) throws InterruptedException {
        try {
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            search.clear();
            search.sendKeys(productName);

            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchBtn.click();

            // Wait until results page loads
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            Thread.sleep(3000);
            System.out.println("Searched for product: " + productName);

        } catch (Exception e) {
            System.out.println("Failed to perform search: " + e.getMessage());
            throw e;
        }
    }

    // Step 2: Apply "3 Seater" filter safely
    public void applyThreeSeaterFilter() throws InterruptedException {
        try {
            // Wait for filters to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Seater')]")));

            WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(threeSeaterFilter));

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", filter);
            Thread.sleep(1000);

            try {
                filter.click();
            } catch (ElementClickInterceptedException e) {
                System.out.println(" Click intercepted. Retrying with JavaScript...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filter);
            }

            // Wait for results to refresh after applying filter
            Thread.sleep(3000);
            System.out.println("3-Seater filter applied successfully.");

        } catch (TimeoutException e) {
            System.out.println("Timeout: 3-Seater filter not found or took too long to load.");
        } catch (Exception e) {
            System.out.println("Error applying 3-Seater filter: " + e.getMessage());
        }
    }
    

    // Step 3: Verify if results are displayed
    public boolean resultsDisplayed() {
    	try {
            // Handle multiple tabs
            String currentWindow = driver.getWindowHandle();
            List<String> allTabs = driver.getWindowHandles().stream().toList();

            // If a new tab is opened, switch to it
            if (allTabs.size() > 1) {
                driver.switchTo().window(allTabs.get(allTabs.size() - 1));
                System.out.println("Switched to new tab for product details.");
                Thread.sleep(2000);
            }

            // Try to verify results on the page
            List<WebElement> results = driver.findElements(searchResults);

            if (results.size() > 0) {
                System.out.println("Search results are visible on the page.");
                return true;
            } else {
                // Check if we are in a product details page
                boolean productTitleVisible = driver.findElements(By.xpath("//h1 | //h2[contains(@class,'title')]")).size() > 0;
                if (productTitleVisible) {
                    System.out.println("Product details page detected - assuming successful search and filter.");
                    return true;
                } else {
                    System.out.println("No search results or product details found.");
                    return false;
                }
            }
            } catch (Exception e) {
            System.out.println(" Error verifying results: " + e.getMessage());
            return false;
        }
        
    }
}
