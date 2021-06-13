package dataProvider;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomeDepotDataProvider {
	public static WebDriver driver;

	@Test(dataProvider = "testdata")
	public void login(String email, String password) {
		//String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome","C:\\Users\\samia\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
                            
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://www.homedepot.com");

		driver.findElement(By.xpath("//*[@id='headerMyAccountTitle']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();

		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);

		driver.findElement(By.xpath("//input[@id='password-input-field']")).clear();
		driver.findElement(By.xpath("//input[@id='password-input-field']")).click();
		driver.findElement(By.xpath("//input[@id='password-input-field']")).sendKeys(password);

		driver.findElement(By.xpath("//span[contains(text(),'Sign In')]")).click();

		String error = driver.findElement(By.xpath("//span[@class='alert-inline__message']")).getText();
		Assert.assertEquals("We're sorry, we couldn't find an account with that email address.", error);
		System.out.println(error);

	}

	@DataProvider(name = "testdata")
	public Object[][] getdata() {
		// Rows Number of times your test has to be repeated
		// Columns Number of parameters in test data
		Object[][] data = new Object[3][2];
		// 1st row
		data[0][0] = "testemailone@gmail.com";
		data[0][1] = "password1";

		// 1nd row
		data[1][0] = "testemailtwo@gmail.com";
		data[1][1] = "password2";
		// 3rd
		data[2][0] = "testemailthree@gmail.com";
		data[2][1] = "password3";

		return data;
	}

}
