package account.gmail.com;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener extends Browser implements ITestListener {
	public void onTestSuccess(ITestResult iTestresult) {
		try {
			String projectpath = System.getProperty("user.dir");
			screenshots(projectpath + "\\Pass\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult iTestresult) {
		try {
			String projectpath = System.getProperty("user.dir");
			screenshots(projectpath + "\\Fail\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
