package eleks.com.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEmailPage extends BasePage {

	public CreateEmailPage(AppiumDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='to']")
	private WebElement to_field;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement subject_field;

	@FindBy(xpath = "//textarea[@id='message_body']")
	private WebElement body_field;

	@FindBy(xpath = "//button[1][@class='i_send']")
	private WebElement sent_btn;

	@FindBy(xpath = "//button[2][@id='i_folders']")
	private WebElement folder_btn;

	public boolean isCreateEmailPageOpened() {
		if (to_field.isDisplayed() && subject_field.isDisplayed()
				&& body_field.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public MailBoxPage navigateToMailBoxPage() throws InterruptedException {
		folder_btn.click();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		return new MailBoxPage(driver);
	}
	
	public SuccessSendMailPage createEmail(String to, String subject,
			String body) {
		type(to_field, to);
		type(subject_field, subject);
		type(body_field, body);
		sent_btn.click();
		return new SuccessSendMailPage(driver);	
	}
	

}
