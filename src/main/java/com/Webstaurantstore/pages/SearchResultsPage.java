package com.Webstaurantstore.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultsPage {


	WebDriver driver;
	
	
	@FindBy(id="searchval")
	private WebElement serachBar;
	
	@FindBy(xpath="(//button[contains(@value,'Search')])[1]")
	private WebElement searchBtn;
	
	@FindBy(xpath="//a[normalize-space()='View Cart']")
	private WebElement cartButton;
	
	@FindBy(xpath="//button[normalize-space()='Empty Cart']")
	private WebElement emptyButton;
	
	@FindBy(xpath="//div[contains(@class,'ReactModal__Content')]//footer/button[1]")
	private WebElement modal;
	
	

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


    //Enter input
	public void searchInput() {
		serachBar.sendKeys("stainless work table");

	}


   //Search for the item
	public void clicksearchBtn()  {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();

	}

	public void verifySearchResults() {

		// Checking  the search result ensuring every product has the word 'Table' in its title
		List<WebElement> products = driver.findElements(By.xpath("//div[@id ='product_listing']//following-sibling::div[@id='ProductBoxContainer']"));
		for (WebElement product : products) {
			String title = product.getText().toLowerCase();
			Assert.assertTrue(title.contains("table"), "table keyword missing");
		}

		// Adding the last found item to Cart
		WebElement lastProduct = products.get(products.size() - 1);
		WebElement addToCartButton = lastProduct.findElement(By.xpath("//input[contains(@gtm-id,'AddToCartATC')]"));
		addToCartButton.click();

	}


	//view items in cart 
	public void viewCart() {
		cartButton.click();
	}

	//empty cart
	public void emptyCart()  {
		emptyButton.click();
		modal.click();

	}


}
