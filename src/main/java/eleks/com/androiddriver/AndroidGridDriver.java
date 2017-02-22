package eleks.com.androiddriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidGridDriver {
	
	public static AndroidDriver androidDriver;
	static DesiredCapabilities cap = null;
	
	
	private AndroidGridDriver() {

	}
	
	public static AndroidDriver getInstance() throws MalformedURLException {
		if (androidDriver == null) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			androidDriver = new AndroidDriver(new URL("http://localhost:4040/wd/hub"), cap);
		}
		return androidDriver;	
	}
		
	public static AndroidDriver getInstance(String device, String browserName,
			String port) throws Exception {
		if (androidDriver == null) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
			androidDriver = new AndroidDriver(new URL("http://localhost:" + port
					+ "/wd/hub"), cap);
			androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return androidDriver;
	}
	
	public static void killDriverInstance() {
		if (androidDriver != null) {
			androidDriver.close();
			androidDriver.quit();
		}
	}
}
