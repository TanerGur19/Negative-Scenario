package com.prestashop.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import com.github.javafaker.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/");
		driver.findElement(By.linkText("Sign in".trim())).click(); //  it gives error if you have space NoSuchElementException.
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);   //  use this implicitly wait just one time
	}
	
	@Test
	public void loginTest() {
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String password = faker.internet().password();
		String addressStreet = faker.address().streetAddress();
		String addressCity = faker.address().city();
		String zipCode = faker.address().zipCode();
		String mobilePhone = faker.phoneNumber().cellPhone();
		
		
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		
		
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		
		driver.findElement(By.id("address1")).sendKeys(addressStreet);
		driver.findElement(By.id("city")).sendKeys(addressCity);
		
		WebElement selectState = driver.findElement(By.id("id_state"));
		
		Select list = new Select(selectState);
		
		List<WebElement> options = list.getOptions();  //  getOptions method come from Select Class
		
		int rn = ((int)(Math.random() * ((options.size() - 1) + 1)) + 1); // Math.random () gives you number from 0 to 1.it would also 
		// gives you 0.25 and double numbers thats why we cast it to int.
		
		// Random random= new Random();
		// int rn= random.nextInt(options.size()+1);  // gives range from 0 to option size. it should start from 1.otherwise you get exception
		// AssertTrue dan
		
		
		list.selectByIndex(rn);  // this is at least 1 because index 0 is empty choice.
		
		driver.findElement(By.id("postcode")).sendKeys(zipCode.substring(0,5));
		driver.findElement(By.id("phone_mobile")).sendKeys(mobilePhone);
		driver.findElement(By.id("submitAccount")).click();
		driver.findElement(By.linkText("Sign out")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();
		
		String actual = driver.findElement(By.cssSelector("a[title='View my customer account']>span")).getText();
		String expected = firstName + " " + lastName;
		Assert.assertTrue(actual.equals(expected));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}