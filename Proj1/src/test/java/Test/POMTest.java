package Test;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import Pac1.HomePage;



public class POMTest {
    WebDriver driver;
    HomePage home;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");
        home = new HomePage(driver);
    }

    @Test
    public void testFlow() {
        Assert.assertEquals("Your Store", driver.getTitle());

        home.getDesktopsTab().click();
        home.getMacLink().click();
        Assert.assertEquals("Mac", home.getMacHeading().getText());

        Select sort = new Select(home.getSortDropdown());
        sort.selectByVisibleText("Name (A - Z)");

        home.getAddToCartButton().click();

        home.getSearchBox().clear();
        home.getSearchBox().sendKeys("Monitors");
        home.getSearchButton().click();

        home.getSearchCriteria().clear();
        if (!home.getDescriptionCheckbox().isSelected()) {
            home.getDescriptionCheckbox().click();
        }
        home.getSearchAgainButton().click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}