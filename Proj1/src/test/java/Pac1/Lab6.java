package Pac1;

import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
 
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Lab6 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//STEP 1:
		driver.get("https://tutorialsninja.com/demo/index.php?");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("shree1@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Pass");
		driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[2]/div/form/input")).click();
		
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[2]/ul/li/a/i")));
        element.click();

		//STEP 2:
		driver.findElement(By.xpath("html/body/div/nav/div[2]/ul/li[3]")).click();
		//STEP 3:
		driver.findElement(By.linkText("Monitors (2)")).click();
		//STEP 4:
        WebElement show= driver.findElement(By.id("input-limit"));
		Select s=new Select(show);
		s.selectByIndex(1);
		//STEP 5:
		driver.findElement(By.linkText("Apple Cinema 30\"" )).click();
		driver.findElement(By.id("button-cart")).click();
		//STEP 6:
		driver.findElement(By.linkText("Specification")).click();
		//STEP 7:
		 WebElement specTable = driver.findElement(By.id("tab-specification"));
		 if (specTable.isDisplayed()) {
		     System.out.println("Specification details are present.");
		 }
		 else {
			 System.out.println("Specification details are not present");
		 }
		 //STEP 8:
		 driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[2]/div/button/i")).click();
 
		 //STEP 9:
		 WebElement Msg = driver.findElement(By.xpath("html/body/div[2]/div"));
         if (Msg.getText().contains("Success: You have added Apple Cinema 30\" to your wish list!")) {
            System.out.println("Wish list message verified.");
         }
         else {
        	 System.out.println("Wish List message not verified");
         }
         //Step 10:
		 driver.findElement(By.name("search")).sendKeys("Mobile");
		 driver.findElement(By.xpath("html/body/header/div/div/div[2]/div/span/button")).click();
 
         //STEP: 11,12
		 driver.findElement(By.id("description")).click();
		 driver.findElement(By.id("button-search")).click();
 
		 //STEP: 13
		 driver.findElement(By.linkText("HTC Touch HD" )).click();
 
		 //STEP 14
         WebElement quantity = driver.findElement(By.id("input-quantity"));
         quantity.clear();
         quantity.sendKeys("3");
         //Step 15:
		driver.findElement(By.id("button-cart")).click();
 
		//STEP 16:
 
		 WebElement cartMsg = driver.findElement(By.xpath("html/body/div/div"));
		 if (cartMsg.getText().contains("Success: You have added HTC Touch HD to your shopping cart!")) {
		      System.out.println("Cart message verified.");
		 }
		 else {
			 System.out.println("Cart message not verified");
		 }
		 //STEP: 17 
		 driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div")).click();
		 //STEP: 18
 
		 WebElement cartItem = driver.findElement(By.linkText("HTC Touch HD"));
		 if (cartItem.isDisplayed()) {
		     System.out.println("HTC Touch HD is present in the cart.");
		 }
		 //Step: 19
		 driver.findElement(By.linkText("Checkout")).click();
		 //STEP: 20
		 driver.findElement(By.linkText("My Account")).click();
		 //Step: 21
 
         WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement logoutLink = Wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
         logoutLink.click();
 
	     //STEP: 22
 
         WebDriverWait JustWait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement logout = JustWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Account Logout")));
         logoutLink.click();
        //STEP: 23
        driver.findElement(By.xpath("html/body/div[2]/div/div/div/div/a")).click();
	}
 
}