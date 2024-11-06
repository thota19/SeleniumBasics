package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	public LandingPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessageLoginIssues;
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	public ProductCatalogue loginApplication(String email,String password) {
		this.email.sendKeys(email);
		this.password.sendKeys( password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	public void goTo(String url) {
		driver.get(url);
	}
	public String getErrorMessage() {
		webelementTOAppear(errorMessageLoginIssues);
		return errorMessageLoginIssues.getText();
	}
}
