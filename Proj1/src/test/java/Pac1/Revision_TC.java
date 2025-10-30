package Pac1;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class Revision_TC {
	WebDriver driver;
  @Test(dataProvider = "logindata")
  public void login(String username, String password) {
	  driver.get("https://tutorialsninja.com/demo/index.php?");
	  driver.findElement(By.linkText("My Account")).click();
  }
  @BeforeMethod
  public void beforeMethod() {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver(); 
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }


  @DataProvider
  public Object[][] logindata() {
    return new Object[][] {
      new Object[] { "oct2025@gmail.com", "welcome" },
      new Object[] { "sept2025@gmail.com", "welcome" },
    };
  }
}
