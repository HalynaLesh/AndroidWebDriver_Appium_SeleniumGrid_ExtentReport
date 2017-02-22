package eleks.com.ExtentReport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.android.AndroidDriver;

public class GetScreenShot {
	
	public static String capture(AndroidDriver driver, String screenshotName) 
    {
        /*TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\ErrorScreenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                     
        return dest;*/
		
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String screenPath = "./ErrorScreenshots/"+screenshotName + "_" +formater.format(calendar.getTime())+".png";	
		//	String screenPath = System.getProperty("user.dir") + "\\ErrorScreenshots\\"+screenshotName+".png";	
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
    }

}
