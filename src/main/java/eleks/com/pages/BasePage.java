package eleks.com.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	AppiumDriver driver;
	
	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	protected void type(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

}
