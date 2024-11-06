package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	By orderItems=By.cssSelector("tr td:nth-child(3)");
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderListItems;
	
	@FindBy(css="div[class*='subtotal '] button")
	WebElement subtotalButton;
	
	public List<WebElement> getOrdersList() {
		
		elementTOAppear(orderItems);
		
		return orderListItems;
	}
	
	
	public boolean getOrdersDetails(String productName) {
		
		boolean match =   getOrdersList()
				.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
	
		return match;
		
	}


	public CheckoutPage gotoCheckOut() {
		
		subtotalButton.click();
		return new CheckoutPage(driver);
	}
	
	
}
