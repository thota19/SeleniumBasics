package extentreports;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter spark=new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Test Reports");
		spark.config().setReportName("Web Automation Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Pavan Kalyan");
		extent.createTest(path);
		return extent;
	}
	
}
