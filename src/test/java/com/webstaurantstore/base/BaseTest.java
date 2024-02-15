package com.webstaurantstore.base;


import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;





public class BaseTest {
	
	
	WebDriver  driver ;
   
	public WebDriver initalizeBrowserAndAppURL(String browserName) {
	if(browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	}else if (browserName.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
	}else if (browserName.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
	}else if(browserName.equalsIgnoreCase("safari")) {
		driver = new SafariDriver();	
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	driver.get("https://www.webstaurantstore.com/");
	return driver;
}

/*
 *  Note: 
 * 1.I have used edge browser to execute my scripts they are are running fine without any obstacles,due to some personal browser conflicts with chromebrowser I am not able to use chromebrowser
    Though I have configured code for chromebrowser.
   2.Instead of WebdriverManager I have used latest SeleniumManager to auto download browser drivers due to new and latest version of laptop 
   However I have added WebDriverManager dependency in POM.xml for reference use
  
 */

	
}