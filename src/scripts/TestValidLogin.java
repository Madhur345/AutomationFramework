package scripts;

//import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Lib;
import pompages.LoginPage;

public class TestValidLogin extends BaseTest {
	@Test
	public void testValidLogin() throws InterruptedException{
		LoginPage l = new LoginPage(driver);
		//enter username
		String username=Lib.getCellValue("ValidLogin", 1, 0);
		l.setUserName(username);
		//enter password
		String password=Lib.getCellValue("ValidLogin", 1, 1);
		l.setPassword(password);
		//click on login buttton
		Thread.sleep(5000);
		l.clickLogin();
		//Assert.fail();

	}
}
