package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	By toast=By.id("toast-container");
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	public ProductCatalogue (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".ng-animating")	
		WebElement spinner;
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	public List<WebElement> getProductList() {
		
		elementTOAppear(productBy);
		
		return products;
		
	}
	
	public WebElement getProductName(String productName) {
		
		WebElement product= getProductList().stream().filter(prod->
			prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase( productName)).findFirst().orElse(null);
		
			return product;
	}
	
	public AbstractComponent addProduct(String productName) throws Exception {
		
		WebElement prod=getProductName(productName);
		prod.findElement(addToCart).click();
		elementTOAppear(toast);
		try {
			waitElementTODisappear(spinner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AbstractComponent(driver);
	}
}
