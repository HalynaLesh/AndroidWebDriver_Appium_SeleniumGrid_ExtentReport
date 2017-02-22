package eleks.com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;

public class SuccessSendMailPage extends BasePage{

	public SuccessSendMailPage(AppiumDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@id='resultMessage']")
	private WebElement success_msg;
	
	@FindBy(xpath="//a[@id='newMessage']")
	private WebElement newEmail;
	
	@FindBy(xpath="//a[@href='#check']")
	private WebElement checkEmail;
	
	public boolean isSuccessSendMailPageOpened() {
		if (success_msg.isDisplayed() && newEmail.isDisplayed() && checkEmail.isDisplayed()){
			return true;
		} else {
			return false;
		}
	}
	
	public MailBoxPage navigateToMailBoxPage() {
		checkEmail.click();
		return new MailBoxPage(driver);
	}
}
