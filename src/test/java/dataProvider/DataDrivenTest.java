package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataDrivenTest {
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;

	@BeforeTest
	public void initialization() {
		// To set the path of the Chrome driver.
		String projectpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectpath + "\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://www.facebook.com/"); // To launch facebook
		driver.manage().window().maximize(); // To maximize the browser
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// implicit wait
	}

	@Test
	public void fbLoginLogout() throws IOException {

		String projectpath = System.getProperty("user.dir");
		File src = new File(projectpath + "\\ExcelData\\Data.xlsx"); // Import excel sheet.
		FileInputStream fis = new FileInputStream(src); // Load the file.
		workbook = new XSSFWorkbook(fis); // Load he workbook.
		sheet = workbook.getSheetAt(0); // Load the sheet in which data is stored.
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			/*
			 * I have added test data in the cell A2 as "testemailone@test.com" and B2 as
			 * "password" Cell A2 = row 1 and column 0. It reads first row as 0, second row
			 * as 1 and so on and first column (A) as 0 and second column (B) as 1 and so on
			 */

			// Import data for Email.
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(CellType.STRING);
			driver.findElement(By.xpath("//input[@type='email'][@name='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email'][@name='email']")).sendKeys(cell.getStringCellValue());

			// Import data for password.
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(CellType.STRING);
			driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).clear();
			driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).sendKeys(cell.getStringCellValue());

			// driver.findElement(By.xpath("//input[@type='submit'][@id='u_0_b']")).click();//
			// To click on Login button

			// To write data in the excel
			FileOutputStream fos = new FileOutputStream(src);
			String message = "Test Pass"; // Message to be written in the excel sheet
			// Create cell where data needs to be written.
			sheet.getRow(i).createCell(2).setCellValue(message);
			workbook.write(fos);// finally write content
			fos.close(); // close the file
		}
	}

}
