package account.gmail.com;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Gmail_info extends Browser {
	@Test(priority = 0)
	public void close_parentWindow() {
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
		driver.switchTo().window(parentWindowHandler); // switch to popup window
		driver.close();
		driver.switchTo().window(subWindowHandler); // switch back to parent window

		System.out.println("Popus URL is: " + driver.switchTo().window(subWindowHandler).getCurrentUrl());
		System.out.println(
				"Popups Title: " + driver.switchTo().window(subWindowHandler).getTitle().contains(subWindowHandler));
	}

	@Test(priority = 1)
	public void account_info(String firstname, String lastname, String email, String password, String passwordconfirm) {

		WebElement first = driver.findElement(By.xpath("//input[@id='firstName']"));
		if (first.isDisplayed() && first.isEnabled()) {
			first.sendKeys(firstname);
		}

		WebElement last = driver.findElement(By.xpath("//input[@id='lastName']"));
		if (last.isDisplayed() && last.isEnabled()) {
			last.sendKeys(lastname);
		}

		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		if (username.isDisplayed() && username.isEnabled()) {
			username.sendKeys(email);
		}

		WebElement passwd = driver.findElement(By.xpath("//input[@name='Passwd']"));
		if (passwd.isDisplayed() && passwd.isEnabled()) {
			passwd.sendKeys(password);
		}

		WebElement confirm = driver.findElement(By.xpath("//input[@name='ConfirmPasswd']"));
		if (confirm.isDisplayed() && confirm.isEnabled()) {
			confirm.sendKeys(passwordconfirm);
		}
		try {
			WebElement nextbutton = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
			nextbutton.click();
		} catch (Exception e) {
			System.out.println("Next button not foun :" + e.getMessage());
		}

		String error = driver.findElement(By.xpath("//*[contains(text(),'That username is taken. Try another.')]"))
				.getText();
		Assert.assertEquals("That username is taken. Try another.", error);
		System.out.println(error);
	}

}
