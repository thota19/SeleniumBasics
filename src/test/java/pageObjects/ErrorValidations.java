package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import abstractcomponents.AbstractComponent;
import abstractcomponents.Retry;
import testcases.BaseTest;


public class ErrorValidations extends BaseTest{
	String userEmail="sa@123gmail.com";
	String userEmail1="sai@123gmail.com";
	String userPassword="Fullstack@29";
	String productName="ADIDAS ORIGINAL";
	WebDriver driver;
	
	public  void initializeDriverValidations() {
		driver=getDriver();
	}
	@Test(groups= {"errorvalidations"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
		initializeDriverValidations();
		ProductCatalogue catalogue =land.loginApplication(userEmail,userPassword);
		Assert.assertEquals("correct email or password.",land.getErrorMessage());
		
	}
//	@Test(groups= {"errorvalidations"})
//	public void ProductErrorValidation() throws Exception {
//		
//		ProductCatalogue catalogue =land.loginApplication(userEmail1,userPassword);
//		catalogue.getProductList();
//		AbstractComponent component = catalogue.addProduct(productName);
//		CartItems cartItems = component.goToCart();
//		cartItems.getProductList();
//		boolean match = cartItems.getProductName(productName);
//		Assert.assertTrue(match);
//	}
//	
}
