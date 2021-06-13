package account.gmail.com;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Homepage extends Browser {
	@Test
	public void open_google() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS); // Wait For Page To Load
		Thread.sleep(600);
		// driver.findElement(By.xpath("//*[@id='viewport']/div[3]/div/div/a[2]")).click();

		Thread.sleep(600);
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		System.out.println("Main window Title: " + driver.switchTo().window(parentWindowHandler).getTitle());
		System.out.println("Main window URL is: " + driver.switchTo().window(parentWindowHandler).getCurrentUrl());

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		System.err.println("No of windows :  " + handles.size());
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window
		// Now you are in the popup window, perform necessary actions here
		System.out.println("Popus URL is: " + driver.switchTo().window(subWindowHandler).getCurrentUrl());
		System.out.println(
				"Popups Title: " + driver.switchTo().window(subWindowHandler).getTitle().contains(subWindowHandler));
		driver.close();
		driver.switchTo().window(parentWindowHandler); // switch back to parent window

		try {
			WebElement serach = driver.findElement(By.xpath("//*[@name='q']"));
			if (serach.isDisplayed() && serach.isEnabled()) {
				serach.click();
				serach.sendKeys("gmail account");
			}
		} catch (Exception e) {
			System.out.println("Google serach text box not found: " + e.getMessage());
		}
		// Put a Implicit wait, this means that any search for elements on the page
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// could take the time the implicit wait is set
																		// for before throwing exception
		driver.findElement(By.xpath("//input[@name='btnK']")).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

		Thread.sleep(600);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//h3[contains(text(),'Gmail - Google')]")).click();
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(600);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='h-c-button h-c-button--primary started__cta--desktop']")).click();
	}

}
