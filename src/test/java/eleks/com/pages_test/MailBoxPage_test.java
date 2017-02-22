package eleks.com.pages_test;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import eleks.com.ExtentReport.ExtentTestManager;
import eleks.com.pages.CreateEmailPage;
import eleks.com.pages.LoginPage;
import eleks.com.pages.MailBoxPage;
import eleks.com.pages.SentEmailPage;
import eleks.com.pages.SuccessSendMailPage;

public class MailBoxPage_test extends BasePage_test {
	
	 MailBoxPage mailBoxPage;
	 CreateEmailPage createEmailPage;
	 SentEmailPage sentEmailPage;

/*	@Test
	public void navigateToCreateEmailPage_test() throws ClassNotFoundException,
			SQLException {
		loginPage.open();
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		Assert.assertTrue(createEmailPage.isCreateEmailPageOpened());
		Allert!!
		//logout
	}*/

	@Test
	public void logout_test() throws ClassNotFoundException, SQLException, InterruptedException {
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		Assert.assertTrue(loginPage.isLoginPageOpened());
		test.log(LogStatus.INFO, "Verifying is Login page opened.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		test.log(LogStatus.INFO, "Verifying is MailBox page opened.");
		loginPage = mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
		Thread.sleep(2000);
		Assert.assertTrue(loginPage.isLoginPageOpened());
		test.log(LogStatus.INFO, "Verifying is Login page opened.");
	}
	
	@Test
	public void navigateToSentEmailPage_test() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		Thread.sleep(2000);
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		Assert.assertTrue(sentEmailPage.isSentEmailPageOpened());
		test.log(LogStatus.INFO, "Verifying is SentEmail page opened.");
		mailBoxPage = sentEmailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}
}
