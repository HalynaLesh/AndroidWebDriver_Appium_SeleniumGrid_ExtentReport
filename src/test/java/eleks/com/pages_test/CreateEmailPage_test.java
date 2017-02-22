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
import eleks.com.pages.SuccessSendMailPage;
import eleks.comEmailBuilder.Email;
import eleks.comEmailBuilder.EmailBuilder;

public class CreateEmailPage_test extends BasePage_test{
	
	 MailBoxPage mailBoxPage;
	 CreateEmailPage createEmailPage;
	 SuccessSendMailPage successSendMailPage;
	 
	 Email email = new EmailBuilder().buildTo("to@i.ua")
				.buildSubject("subject_tra_ta_ta").buildBody("body_tra_ta_ta")
				.build();	 
	
/*	@Test
	public void navigateToMailBoxPage_test() throws ClassNotFoundException, SQLException, InterruptedException{
		loginPage.open();
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		mailBoxPage = createEmailPage.navigateToMailBoxPage();
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		!!!!Alert
	}*/
	
	
	@Test
	public void createEmail_test() throws ClassNotFoundException, SQLException{
		ExtentTest test = ExtentTestManager.getTest();
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		createEmailPage = mailBoxPage.navigateToCreateEmailPage();
		test.log(LogStatus.INFO, "Navigate to Create Email page.");
		successSendMailPage = createEmailPage.createEmail(email.getTo(), email.getSubject(), email.getBody());
		test.log(LogStatus.INFO, "Create Email.");
		Assert.assertTrue(successSendMailPage.isSuccessSendMailPageOpened());
		test.log(LogStatus.INFO, "Verifying is Success Send Mail Page Opened page opened.");
		mailBoxPage = successSendMailPage.navigateToMailBoxPage();
		test.log(LogStatus.INFO, "Navigate to MailBox page.");
		mailBoxPage.logout();	
		test.log(LogStatus.INFO, "Logout.");
	}

}
