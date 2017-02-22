package eleks.com.pages;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage extends BasePage {

	public MailBoxPage(AppiumDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='Вхідні']")
	private WebElement title;

	@FindBy(xpath = "//button[3][@class='i_edit']")
	private WebElement createEmail;
	
	@FindBy(xpath = "//div[@id='container_folders']")
	private WebElement option_popup;

	@FindBy(xpath = "//button[@id='i_options']")
	private WebElement option_btn;
	
	@FindBy(xpath="//div[@id='container_folders']")
	private WebElement contener_folder;
	
	@FindBy(xpath ="//button[@id='buttonSettings']")
	private WebElement settings_btn;
	
	@FindBy(xpath = "//a[@class='i_logout']")
	private WebElement logout;
	
	@FindBy(xpath="//a[@href='#folder/sent-mail']")
	private WebElement sentEmail;
	
	public boolean isMailBoxPageOpened() {
		if (title.isDisplayed() && createEmail.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public CreateEmailPage navigateToCreateEmailPage() {
		createEmail.click();
		return new CreateEmailPage(driver);
	}
	
	public LoginPage logout() {
		option_btn.click();
		settings_btn.click();
		logout.click();
		return new LoginPage(driver);
	}
	
	public SentEmailPage navigateToSentEmailPage() throws InterruptedException {
		option_btn.click();
		Thread.sleep(2000);
		/*WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(option_popup));*/
		sentEmail.click();
		return new SentEmailPage(driver);
	}
}
