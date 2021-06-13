package account.gmail.com;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Browser {
	public static WebDriver driver;

	@BeforeClass
	@Parameters({ "url", "browser" })
	public void beforeClass(String siteUrl, String typebrowser) {
		if (typebrowser.equalsIgnoreCase("chrome")) {
			String projectpath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", projectpath + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.navigate().to("sitUrl");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("The type of the browser is: " + typebrowser);
		} else if (typebrowser.equalsIgnoreCase("firefox")) {
			String projectpath = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", projectpath + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.navigate().to("sitUrl");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("The type of the browser is: " + typebrowser);
		} else {
			System.out.println("Incorrect browser value passed! Please check name of the browser.");
		}
	}

	@Test(priority = 0)
	public void verifyHomepagetitle() {
		String title = driver.getTitle();
		System.out.println("The actual title of the website is: " + title);

		String actulUrl = driver.getCurrentUrl();
		System.out.println("The actual Url of the website is: " + actulUrl);
	}

	@AfterClass
	public void afterClass() {
	}

	public void screenshots(String source) throws IOException {
		Date d = new Date();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(source + d.toString().replace(":", "_") + ".png"));
	}
}
