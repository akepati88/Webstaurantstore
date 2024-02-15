package com.webstaurantstore.tests;



import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StainlessTableAddAndCheckOut {

	WebDriver driver ;


	@Test
	public void addItemAndDeleteItemFromCart() throws InterruptedException {
	
       // Initialize browser
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get("https://www.webstaurantstore.com/");
		
		// Search for 'stainless work table'
		WebElement serachBar = driver.findElement(By.id("searchval"));
		serachBar.sendKeys("stainless work table");
        
		//click on search button
		Thread.sleep(2000);
		WebElement searchBtn = driver.findElement(By.xpath("(//button[contains(@value,'Search')])[1]"));
		searchBtn.click();

		
		// Verify all the pages on page and Check the search result ensuring every product has the word 'Table' in its title
		List<WebElement> products = driver.findElements(By.xpath("//div[@id ='product_listing']//following-sibling::div[@id='ProductBoxContainer']"));
		List<WebElement> NumberOfPages = driver.findElements(By.xpath("//div[@id='paging']//ul//li[@class='inline-block leading-4 align-top']"));
		WebElement nextBtn = driver.findElement(By.xpath("//li[@class='inline-block leading-4 align-top rounded-r-md']"));
		int size = products.size();
		try {
			for (WebElement Np: NumberOfPages) {
				for (WebElement product : products) {
					String title = product.getText().toLowerCase();

					Assert.assertTrue(title.contains("table"), "table keyword missing");
				}
				Thread.sleep(3000);
				nextBtn.click();
			}
		} catch (NoSuchElementException e) {

		}

		// Add the last found item to Cart
		WebElement lastProduct = products.get(products.size() - 1);
		WebElement addToCartButton = lastProduct.findElement(By.xpath("//input[contains(@gtm-id,'AddToCartATC')]"));
		addToCartButton.click();

		// Wait for the cart to update
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// View cart
		WebElement cartButton = driver.findElement(By.xpath("//a[normalize-space()='View Cart']"));
		cartButton.click();

        //Empty cart
		WebElement emptyButton = driver.findElement(By.xpath("//button[normalize-space()='Empty Cart']"));
		emptyButton.click();
		
		//ClcikOnEmptyCartBtn
		WebElement modal = driver.findElement(By.xpath("//div[contains(@class,'ReactModal__Content')]//footer/button[1]"));
		modal.click();
		
		driver.quit();

	}


}






