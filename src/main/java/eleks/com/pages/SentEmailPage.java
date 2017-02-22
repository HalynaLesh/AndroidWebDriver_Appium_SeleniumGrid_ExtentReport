package eleks.com.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentEmailPage extends BasePage {

	public SentEmailPage(AppiumDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='Відправлені']")
	private WebElement title;

	@FindBy(xpath = "//button[@id='i_options']")
	private WebElement option_btn;

	@FindBy(xpath = "//a[@href='#folder/INBOX']")
	private WebElement mailBox_btn;
	
	@FindBy(xpath="(//button[@id='i_folders'])[1]")
	private WebElement folder_btn;

	public boolean isSentEmailPageOpened() {
		if (title.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public MailBoxPage navigateToMailBoxPage() {
		option_btn.click();
		mailBox_btn.click();
		return new MailBoxPage(driver);
	}
	
	public MailBoxPage navigateToMailBoxPageFromOpenedEmail() {
		folder_btn.click();
		option_btn.click();
		mailBox_btn.click();
		return new MailBoxPage(driver);
	}

	public void openSentEmail(String subject) {
		final String subject_find = "//span[@class='subject' and text()='%s'][1]";
		driver.findElement(By.xpath(String.format(subject_find, subject)))
				.click();
	}

	public boolean isCorrectToOnSentPage(String to) {
		final String body_find = "//span[@class='frm' and text()='%s'][1]";
		if (driver.findElement(By.xpath(String.format(body_find, to)))
				.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCorrectSubjectOnSentPage(String subject) {
		final String body_find = "//span[@class='subject' and text()='%s'][1]";
		if (driver.findElement(By.xpath(String.format(body_find, subject)))
				.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCorrectSubjectOnEmail(String subject) {
		final String body_find = "//*[text()='%s']";
		if (driver.findElement(By.xpath(String.format(body_find, subject))).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCorrectBodyOnEmail(String body) {
		final String body_find = "//*[starts-with(text(),'%s')]";
		if (driver.findElement(By.xpath(String.format(body_find, body))).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

}
