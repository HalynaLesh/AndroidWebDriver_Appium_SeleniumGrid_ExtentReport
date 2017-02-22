package eleks.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class LoginPage extends BasePage{

	public LoginPage(AppiumDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = "//input[@name='login']")
	private WebElement login_name;
	
	@FindBy (xpath = "//input[@name='pass']")
	private WebElement login_pass;
	
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement login_btn;
	
	public void open() {
		driver.get("http://i.ua");
	}
	
	public boolean isLoginPageOpened() {
		if (login_name.isDisplayed() & login_pass.isDisplayed() && login_btn.isDisplayed()){
			return true;
		} else {
			return false;
		}			
	}
	
	public MailBoxPage loginAs(String username, String pass) {
		type(login_name, username);
		type(login_pass, pass);
		login_btn.click();
		return new MailBoxPage(driver);//PageFactory.initElements(driver, MailBoxPage.class);
	}

}
