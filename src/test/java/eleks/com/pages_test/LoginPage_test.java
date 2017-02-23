package eleks.com.pages_test;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import android.os.Build;
import android.widget.TextView;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import eleks.com.ExtentReport.ExtentTestManager;
import eleks.com.pages.LoginPage;
import eleks.com.pages.MailBoxPage;

public class LoginPage_test extends BasePage_test{
	
	 MailBoxPage mailBoxPage;
	
	/*@Test (priority = 0)
	public void positive_loginAs_test() throws ClassNotFoundException, SQLException{
		ExtentTest test = ExtentTestManager.getTest();	
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		Assert.assertTrue(loginPage.isLoginPageOpened());
		test.log(LogStatus.INFO, "Verifying is Login page opened.");
		mailBoxPage = loginPage.loginAs(getUserNameSQL(), getUserPassSQL());
		test.log(LogStatus.INFO, "Login.");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		test.log(LogStatus.INFO, "Verifying is MailBox page opened.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}*/
	
	@Test(priority = 1)
	public void negative_loginAs_test() throws ClassNotFoundException, SQLException{
		ExtentTest test = ExtentTestManager.getTest();	
		loginPage.open();
		test.log(LogStatus.INFO, "Opening login Page.");
		Assert.assertTrue(loginPage.isLoginPageOpened());
		test.log(LogStatus.INFO, "Verifying is Login page opened.");
		mailBoxPage = loginPage.loginAs("dfgdgd", "gdgd");
		test.log(LogStatus.INFO, "Login.");
		Assert.assertTrue(mailBoxPage.isMailBoxPageOpened());
		test.log(LogStatus.INFO, "Verifying is MailBox page opened.");
		mailBoxPage.logout();
		test.log(LogStatus.INFO, "Logout.");
	}
}
