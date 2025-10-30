package Test;


import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import Pac1.HomePageFactory;

public class PageFactoryTest {
    WebDriver driver;
    HomePageFactory home;
    
    
    

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");
        home = new HomePageFactory(driver);
    }

    @Test
    public void testFlow() {
        Assert.assertEquals("Your Store", driver.getTitle());

        home.desktopsTab.click();
        home.macLink.click();
        Assert.assertEquals("Mac", home.macHeading.getText());

        Select sort = new Select(home.sortDropdown);
        sort.selectByVisibleText("Name (A - Z)");

        home.addToCartButton.click();

        home.searchBox.clear();
        home.searchBox.sendKeys("Monitors");
        home.searchButton.click();

        home.searchBox.clear();
        if (!home.descriptionCheckbox.isSelected()) {
            home.descriptionCheckbox.click();
        }
        home.searchAgainButton.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}