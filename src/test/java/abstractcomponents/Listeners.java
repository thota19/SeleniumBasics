package abstractcomponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentreports.ExtentReporterNG;
import testcases.BaseTest;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentReports extents = ExtentReporterNG .getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> local=new ThreadLocal();
	@Override
	public void onTestStart(ITestResult result) {
		
		test=extents.createTest(result.getMethod().getMethodName());
		local.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		local.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    String screenshotPath = null;
	    local.get().fail(result.getThrowable());
	    System.out.println("111111111111111111");
	    WebDriver driver = null;
	    try {
	        Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
	        field.setAccessible(true);
	        driver = (WebDriver) field.get(result.getInstance());
	        System.out.println("2222222222");
	    } catch (NoSuchFieldException | IllegalAccessException e) {
	        e.printStackTrace();
	        local.get().fail("Failed to access WebDriver instance: " + e.getMessage());
	    }

	    if (driver != null) {
	        try {
	        	  System.out.println("333333");
	            screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
	            System.out.println("444444");
	            local.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	        } catch (IOException e) {
	            e.printStackTrace();
	            local.get().fail("Failed to capture screenshot: " + e.getMessage());
	        }
	    } else {
	        local.get().fail("WebDriver instance is null, cannot capture screenshot.");
	    }
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extents.flush();
		
	}

}
