package SeleniumFramework.Ecommerce;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mongodb.MapReduceCommand.OutputType;

import abstractcomponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartItems;
import pageObjects.CheckoutPage;
import pageObjects.ConfrimationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testcases.BaseTest;


public class LoginintoApplication extends BaseTest{

	
	String confirmationMessage="Thankyou for the order.";
	@Test(dataProvider="getData",groups={"purchase"})
	public  void applicationStart(HashMap<String, String> map) throws Exception {

		
		ProductCatalogue catalogue =land.loginApplication(map.get("userEmail"),map.get("userPassword"));
		catalogue.getProductList();
		AbstractComponent component = catalogue.addProduct(map.get("productName"));
		CartItems cartItems = component.goToCart();
		cartItems.getProductList();
		boolean match = cartItems.getProductName(map.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartItems.gotoCheckOut();
		checkoutPage.selectCountry("india");
		ConfrimationPage confrimationPage = checkoutPage.submit();
		String checkConfirmationMessage = confrimationPage.checkConfirmationMessage();
		Assert.assertTrue(checkConfirmationMessage.equalsIgnoreCase(confirmationMessage));
		
	}

	//to verify adidas original is displaying in orders page
	//@Test(dataProvider="getData",dependsOnMethods={"applicationStart"})//,groups={"purchase"}
	public void orderHistoryTest(HashMap<String, String> map) {
		ProductCatalogue catalogue =land.loginApplication(map.get("userEmail"),map.get("userPassword"));
		OrderPage orders = catalogue.goToOrders();
		Assert.assertTrue(orders.getOrdersDetails(map.get("productName")));
			}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\purchaseOrder.json";
		List<HashMap<String, String>> map = getJsonDataToMap(filePath);
		return new Object[][] { {map.get(0)},{map.get(1)}};
	}

}
//HashMap<String,String> map = new HashMap<String,String>();
//map.put("userEmail", "sai@123gmail.com");
//map.put("userPassword", "Fullstack@29");
//map.put("productName","ADIDAS ORIGINAL");
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("userEmail", "pavan1223@gmail.com");
//map1.put("userPassword", "Fullstack@29");
//map1.put("productName","ADIDAS ORIGINAL");
