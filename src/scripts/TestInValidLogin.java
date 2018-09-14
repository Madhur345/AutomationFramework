
package scripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Lib;
import pompages.LoginPage;

public class TestInValidLogin extends BaseTest {
	@Test
	@Parameters
	public void testInValidLogin() throws InterruptedException{
		LoginPage l = new LoginPage(driver);
		int rowCount=Lib.getRowCount("InvalidLogin");
		for(int i=1;i<=rowCount;i++){
			String username=Lib.getCellValue("InvalidLogin", i, 0);
			l.setUserName(username);
			Thread.sleep(5000);
			String password=Lib.getCellValue("InvalidLogin", i, 1);
			l.setPassword(password);
			Thread.sleep(5000);
			l.clickLogin();
		}
	}	
}

