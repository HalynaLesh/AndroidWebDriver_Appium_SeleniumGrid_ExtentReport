package eleks.com.androiddriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidDriverFactory {

	private static final String CHROME = "chrome";
	private static final String BROWSER = "browser";

	private static AppiumDriver appiumDriver;
	private static DesiredCapabilities cap;

	private AndroidDriverFactory() {

	}

	public static AppiumDriver getInstance(String browserName, String deviceName, String remouteURL)
			throws Exception {
		
		cap = new DesiredCapabilities();
		
		if (appiumDriver == null) {
			if (CHROME.equals(browserName)) {
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			} else if (BROWSER.equals(browserName)) {
				cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
			} else
				throw new Exception("invalid browser setup");

			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			appiumDriver = new AndroidDriver(new URL(remouteURL), cap);
			appiumDriver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.SECONDS);
		}
		return appiumDriver;
	}

	public static AppiumDriver getInstance() throws MalformedURLException {
		if (appiumDriver == null) {
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			appiumDriver = new AndroidDriver(new URL(
					"http://127.0.0.1:4723/wd/hub"), cap);
			appiumDriver.manage().timeouts()
					.implicitlyWait(10, TimeUnit.SECONDS);
		}
		return appiumDriver;
	}
	
	public static void killDriverInstance() {
		if (appiumDriver != null) {
			appiumDriver.quit();
		}
	}
}
