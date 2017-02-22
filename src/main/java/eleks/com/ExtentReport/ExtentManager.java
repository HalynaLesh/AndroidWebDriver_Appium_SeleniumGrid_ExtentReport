package eleks.com.ExtentReport;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir")
					+ "./MyExtentReport1.html", true);
			extent.addSystemInfo("Environment", "Staging");
			
		/*	test.log(logStatus, "<a href='file:///c:/Users/log.txt'>linky</a>");*/
		}
		return extent;
	}
}
