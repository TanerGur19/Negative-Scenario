package com.indeed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calculator {
	
	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
	

	 WebDriver driver = new ChromeDriver();
	
	driver.get("https://cnn.com");
	
	
	System.out.println("test");
	
	System.out.println("My name Taner");
	
	
}
}