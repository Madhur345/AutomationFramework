
package scripts;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Lib;
import pompages.LoginPage;
import testlink.api.java.client.TestLinkAPIException;

public class TestInValidLogin extends BaseTest {
	String PLAN_NAME ="Accotech";
	String BUILD_NAME="Build -1";
	String TEST_CASE ="AT-7";
	@Test
	public void testInValidLogin() throws InterruptedException, TestLinkAPIException{
		try{
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
			reportTestPassResult(PLAN_NAME, BUILD_NAME, TEST_CASE);
		}catch(Exception e){
			reportTestFailResult(PLAN_NAME, BUILD_NAME, TEST_CASE, e);
		}
	}
}