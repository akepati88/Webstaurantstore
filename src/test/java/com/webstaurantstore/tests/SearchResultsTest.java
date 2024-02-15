package com.webstaurantstore.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.Webstaurantstore.pages.SearchResultsPage;
import com.webstaurantstore.base.BaseTest;

public class SearchResultsTest extends BaseTest{
	public WebDriver driver;
	SearchResultsPage searchResultspage ;
	

	@BeforeClass
	public void setup() {
		driver = initalizeBrowserAndAppURL(("edge"));
		searchResultspage  = new SearchResultsPage(driver);
	}
	
	//Enter input and search for item
	@Test(priority=0)	
	public void verifySearchingItem() {

		searchResultspage.searchInput();
		searchResultspage.clicksearchBtn();

	}
	
	//Verify search results
	
	@Test(priority=1,dependsOnMethods ={"verifySearchingItem"})	
	public void verifySearchResultsTest(){

		searchResultspage.verifySearchResults();
		
	}
	
	//View cart
	
	@Test(priority=2,dependsOnMethods ={"verifySearchResultsTest"})	
	public void verifyViewCart() {

		searchResultspage.viewCart();
		
	}
	//empty cart
	@Test(priority=3,dependsOnMethods ={"verifyViewCart"})	
	public void verifyEmptyCart() {

		try {
			searchResultspage.emptyCart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
