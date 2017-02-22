package eleks.com.pages_test;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import eleks.com.ExtentReport.ExtentTestManager;
import eleks.com.pages.CreateEmailPage;
import eleks.com.pages.LoginPage;
import eleks.com.pages.MailBoxPage;
import eleks.com.pages.SentEmailPage;
import eleks.com.pages.SuccessSendMailPage;
import eleks.comEmailBuilder.Email;
import eleks.comEmailBuilder.EmailBuilder;

public class SentEmailPage_test extends BasePage_test{
	
	 SoftAssert softAssert = new SoftAssert();
	
	 MailBoxPage mailBoxPage;
	 CreateEmailPage createEmailPage;
	 SuccessSendMailPage successSendMailPage;
	 SentEmailPage sentEmailPage;
	// LoginPage loginPage = new LoginPage(DRIVER.get());
	 
	 Email email = new EmailBuilder().buildTo("to@i.ua")
				.buildSubject("subject_tra_ta_ta").buildBody("body_tra_ta_ta")
				.build();
	 
	
	@Test
	public void navigateToMailBoxPage_test() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		Thread.sleep(2000);
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		mailBoxPage = sentEmailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		test.log(LogStatus.INFO, "Verifying is MailBox page opened.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");		
	}
	
	@Test
	public void positive_checkValidEmailOnSentEmailPage() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		test.log(LogStatus.INFO, "Navigate to Create Email page.");
		successSendMailPage = createEmailPage.createEmail(email.getTo(), email.getSubject(), email.getBody());
		test.log(LogStatus.INFO, "Create Email.");
		mailBoxPage = successSendMailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		Assert.assertTrue(sentEmailPage.isCorrectToOnSentPage(email.getTo()));
		test.log(LogStatus.INFO, "Verifying is correct TO filed on sent page.");
		Assert.assertTrue(sentEmailPage.isCorrectSubjectOnSentPage(email.getSubject()));
		test.log(LogStatus.INFO, "Verifying is correct SUBJECT filed on sent page.");
		softAssert.assertAll();
		mailBoxPage = sentEmailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}
	
	@Test
	public void negative_checkValidEmailOnSentEmailPage() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		test.log(LogStatus.INFO, "Navigate to Create Email page.");
		successSendMailPage = createEmailPage.createEmail(email.getTo(), email.getSubject(), email.getBody());
		test.log(LogStatus.INFO, "Create Email.");
		mailBoxPage = successSendMailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		Assert.assertTrue(sentEmailPage.isCorrectToOnSentPage(email.getTo()));
		test.log(LogStatus.INFO, "Verifying is correct TO filed on sent page.");
		Assert.assertTrue(sentEmailPage.isCorrectSubjectOnSentPage("dgdgds"));
		test.log(LogStatus.INFO, "Verifying is correct SUBJECT filed on sent page.");
		softAssert.assertAll();
		mailBoxPage = sentEmailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}

	@Test
	public void positive_openSentEmail_test() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		test.log(LogStatus.INFO, "Navigate to Create Email page.");
		successSendMailPage = createEmailPage.createEmail(email.getTo(), email.getSubject(), email.getBody());
		test.log(LogStatus.INFO, "Create Email.");
		mailBoxPage = successSendMailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		sentEmailPage.openSentEmail(email.getSubject());
		test.log(LogStatus.INFO, "Open sent Email.");
		Thread.sleep(2000);
		Assert.assertTrue(sentEmailPage.isCorrectSubjectOnEmail(email.getSubject()));
		test.log(LogStatus.INFO, "Verifying is correct SUBJECT filed on Email.");
		Assert.assertTrue(sentEmailPage.isCorrectBodyOnEmail(email.getBody()));
		test.log(LogStatus.INFO, "Verifying is correct BODY filed on Email.");
		softAssert.assertAll();
		mailBoxPage = sentEmailPage.navigateToMailBoxPageFromOpenedEmail();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}	
	
	
	@Test
	public void negative_openSentEmail_test() throws ClassNotFoundException, SQLException, InterruptedException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		test.log(LogStatus.INFO, "Navigate to Create Email page.");
		successSendMailPage = createEmailPage.createEmail(email.getTo(), email.getSubject(), email.getBody());
		test.log(LogStatus.INFO, "Create Email.");
		mailBoxPage = successSendMailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		sentEmailPage = mailBoxPage.navigateToSentEmailPage();
		test.log(LogStatus.INFO, "Navigate to Sent Email page.");
		sentEmailPage.openSentEmail(email.getSubject());
		test.log(LogStatus.INFO, "Open sent Email.");
		Thread.sleep(2000);
		Assert.assertTrue(sentEmailPage.isCorrectSubjectOnEmail("sdfgdgdf"));
		test.log(LogStatus.INFO, "Verifying is correct SUBJECT filed on Email.");
		Assert.assertTrue(sentEmailPage.isCorrectBodyOnEmail("dgdsgd"));
		test.log(LogStatus.INFO, "Verifying is correct BODY filed on Email.");
		softAssert.assertAll();
		mailBoxPage = sentEmailPage.navigateToMailBoxPageFromOpenedEmail();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}	
}
