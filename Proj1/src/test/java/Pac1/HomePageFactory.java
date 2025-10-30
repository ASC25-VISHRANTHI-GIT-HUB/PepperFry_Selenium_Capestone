package Pac1;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory {
    WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    
    

    @FindBy(linkText = "Desktops")
    public WebElement desktopsTab;

    @FindBy(linkText = "Mac (1)")
    public WebElement macLink;

    @FindBy(xpath = "//h2[text()='Mac']")
    public WebElement macHeading;

    @FindBy(id = "input-sort")
    public WebElement sortDropdown;

    @FindBy(xpath = "//button[@onclick[contains(., 'cart.add')]]")
    public WebElement addToCartButton;

    @FindBy(name = "search")
    public WebElement searchBox;

    @FindBy(css = "button[class='btn btn-default btn-lg']")
    public WebElement searchButton;

    @FindBy(name = "description")
    public WebElement descriptionCheckbox;

    @FindBy(css = "input[type='button'][value='Search']")
    public WebElement searchAgainButton;
}