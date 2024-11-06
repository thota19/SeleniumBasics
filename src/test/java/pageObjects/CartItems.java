package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractcomponents.AbstractComponent;

public class CartItems extends AbstractComponent{
	WebDriver driver;
	By cartItems=By.cssSelector(".cart h3");
	public CartItems(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> cartListItems;
	
	@FindBy(css="div[class*='subtotal '] button")
	WebElement subtotalButton;
	
	public List<WebElement> getProductList() {
		
		elementTOAppear(cartItems);
		
		return cartListItems;
	}
	
	
	public boolean getProductName(String productName) {
		System.out.println("hiiii");
		boolean match =  getProductList()
				.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
		System.out.println("hiiii");
		return match;
		
	}


	public CheckoutPage gotoCheckOut() {
		
		subtotalButton.click();
		return new CheckoutPage(driver);
	}
	
	
}
