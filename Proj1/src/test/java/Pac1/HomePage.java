package Pac1;

import org.openqa.selenium.*;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDesktopsTab() {
        return driver.findElement(By.linkText("Desktops"));
    }

    public WebElement getMacLink() {
        return driver.findElement(By.linkText("Mac (1)"));
    }

    public WebElement getMacHeading() {
        return driver.findElement(By.xpath("//h2[text()='Mac']"));
    }

    
    
    
    
    public WebElement getSortDropdown() {
        return driver.findElement(By.id("input-sort"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.xpath("//button[@onclick[contains(., 'cart.add')]]"));
    }

    public WebElement getSearchBox() {
        return driver.findElement(By.name("search"));
    }

    public WebElement getSearchButton() {
        return driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']"));
    }

    public WebElement getSearchCriteria() {
        return driver.findElement(By.name("search"));
    }

    public WebElement getDescriptionCheckbox() {
        return driver.findElement(By.name("description"));
    }

    public WebElement getSearchAgainButton() {
        return driver.findElement(By.cssSelector("input[type='button'][value='Search']"));
    }
}