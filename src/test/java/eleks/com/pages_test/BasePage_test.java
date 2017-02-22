package eleks.com.pages_test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.Utility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import eleks.com.ExtentReport.ExtentManager;
import eleks.com.ExtentReport.ExtentTestManager;
import eleks.com.ExtentReport.GetScreenShot;
import eleks.com.androiddriver.AndroidGridDriver;
import eleks.com.pages.LoginPage;
import eleks.com.sql.ConnectMySQL;

public class BasePage_test {

	ConnectMySQL mySQL = new ConnectMySQL();

	LoginPage loginPage;

	public static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<AndroidDriver>();
	public AndroidGridDriver androidGridDriver;
	public AndroidDriver androidDriver;
	//protected ThreadLocal<AndroidDriver> threadDriver = new ThreadLocal<AndroidDriver>();

	public static AndroidDriver getDriver() {
		return DRIVER.get();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "port", "device", "browserName" })
	public void setUp(@Optional String port, @Optional String device, @Optional String browserName,
			Method method) throws Exception {

		ExtentTest extent = ExtentTestManager.startTest(method.getName(), "Automation Tests for I.UA website").assignCategory(device);
					    
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
		
		if (DRIVER.get() == null) {
			try {	
				DRIVER.set(new AndroidDriver(new URL("http://localhost:" + port
						+ "/wd/hub"), cap));			
				setDeviceInfo(port, device, browserName);
										
			} catch (MalformedURLException e) {
				System.out.println("Mailformed URL address");
				e.printStackTrace();
			}
			DRIVER.get().manage().timeouts()
					.implicitlyWait(30, TimeUnit.SECONDS);
			loginPage = new LoginPage(DRIVER.get());		
		}
	}

	private void setDeviceInfo(String port, String device, String browserName) {
		ExtentReports report = ExtentManager.getReporter();
		report.addSystemInfo("<b>" + "DeviceName "+device + "</b>", device);
		report.addSystemInfo("OS "+device, DRIVER.get().getCapabilities().getPlatform().toString());
		report.addSystemInfo("BrowserType "+device, browserName);
		report.addSystemInfo("BrowserVersion "+device, DRIVER.get().getCapabilities().getVersion().toString());
		report.addSystemInfo("AppiumPort "+device, port);
	}
	
	@AfterMethod
	public void clearCookies(ITestResult result) throws IOException {
		DRIVER.get().manage().deleteAllCookies();
		
		//if (result.getStatus() == ITestResult.FAILURE) {
		if (!result.isSuccess())	{
			String screenshotPath = GetScreenShot.capture(DRIVER.get(), result.getName());		
			ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
			ExtentTestManager.getTest().log(LogStatus.FAIL, "screenshot below: " + ExtentTestManager.getTest().addScreenCapture(screenshotPath));
		
	/*	} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP,
					"Test skipped " + result.getThrowable());*/
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		}
		ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
		ExtentManager.getReporter().flush();
			
		if (DRIVER.get() != null) {
			DRIVER.get().quit();
			DRIVER.remove();
		}
	}
	
	@AfterSuite
	public void close_report(){
		ExtentManager.getReporter().close();
		System.out.println("Close report");
	}

	public String getUserNameSQL() throws ClassNotFoundException, SQLException {
		return mySQL.getUserNameSQL();
	}

	public String getUserPassSQL() throws ClassNotFoundException, SQLException {
		return mySQL.getUserPassSQL();
	}

}
