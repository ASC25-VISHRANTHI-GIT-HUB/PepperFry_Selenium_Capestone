package Pac1;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
 
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Lab5 {
 
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		//STEP 1:

		driver.get("https://tutorialsninja.com/demo/index.php?");

		driver.manage().window().maximize();

		String title=driver.getTitle();

		if(title.equals("Your Store")) {

			System.out.println("Title is matched");

		}

		else {

			System.out.println("Title is not matched");

		}

		driver.findElement(By.partialLinkText("My Account")).click();

		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]")).click();

		String warning= driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

		System.out.println("Warning message is:"+warning);

		if(warning.equals("You must agree to the Privacy Policy!")) {

			System.out.println("warning is matched");

		}

		else {

			System.out.println("warning is not matched");
 
		}

		//PART 2

		//STEP 1:

		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Vishranthi");

		//STEP 2:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]")).click();

		//STEP 4:

		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Arun");

		//STEP:5

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]")).click();

		//Step 7:

		driver.findElement(By.xpath("//input[@placeholder='E-Mail']")).sendKeys("shree2@gmail.com");

		//STEP 8:

		driver.findElement(By.xpath("//input[@placeholder='Telephone']")).sendKeys("8925334178");

 
		WebElement subs=driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));

		if(subs.isSelected())

		{

			System.out.println("yes is selected");

		}

		else

		{

			System.out.println("yes is not selected");

		}
 
 
		//PART 4(PASSWORD)

		//STEP 1:

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("pass");

		//STEP 2:

		driver.findElement(By.xpath("//input[@placeholder='Password Confirm']")).sendKeys("pass");
 
		//PART 4(NEWS LETTER)

		//STEP 1:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/fieldset[3]/div/div/label[1]/input")).click();

		//STEP 2:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[1]")).click();

		//STEP 3:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]")).click();

		//Step 4:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/h1")).click();

        //STEP 5:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/a")).click();

		driver.findElement(By.xpath("/html/body/div[2]/div/div/ul[2]/li/a")).click();
 
		

		//STEP 6:

		driver.findElement(By.xpath("/html/body/div[2]/div/div/h1")).click();
 
 
		

	}
 
}

 