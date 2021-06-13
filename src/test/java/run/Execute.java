package run;

import org.testng.Reporter;
import org.testng.annotations.Test;
import account.gmail.com.Browser;
import account.gmail.com.Gmail_info;
import account.gmail.com.Homepage;

public class Execute extends Browser{
	@Test(priority = 0)
	public void homePageTest() throws InterruptedException {
		Homepage find=new Homepage();
		find.open_google();
		Reporter.log("Executed successfully");
	}
	
	@Test(priority = 1)
	public void gmailinfo() {
		Gmail_info datainfo=new Gmail_info();
		datainfo.close_parentWindow();
		datainfo.account_info("Taylor", "James", "james1259", "cov14579", "cov14579");
		Reporter.log("Executed successfully");
	}

}
