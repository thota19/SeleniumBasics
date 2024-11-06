package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartItems;
import pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;
	
	public void elementTOAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
public void webelementTOAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitElementTODisappear(WebElement element) throws Exception {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartItems goToCart() {
		cartButton.click();
		return new CartItems(driver);
	}
	public OrderPage goToOrders() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	
}
