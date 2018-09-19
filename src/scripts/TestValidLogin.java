package scripts;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Lib;
import pompages.LoginPage;
import testlink.api.java.client.TestLinkAPIException;

public class TestValidLogin extends BaseTest {
	String PLAN_NAME ="Accotech";
	String BUILD_NAME="Build -1";
	String TEST_CASE ="AT-4";
	Logger log =LogManager.getLogger(TestValidLogin.class.getName());
	@Test
	public void testValidLogin() throws InterruptedException, TestLinkAPIException{
		try{
			log.debug("creating an of object of LoginPage Pom class");
			LoginPage l = new LoginPage(driver);
			log.info("object creation is successful");
			log.debug("fetching username from excel file");
			String username=Lib.getCellValue("ValidLogin", 1, 0);
			log.info("username successfully fetched");
			l.setUserName(username);
			String password=Lib.getCellValue("ValidLogin", 1, 1);
			l.setPassword(password);
			Thread.sleep(5000);
			l.clickLogin();

			reportTestPassResult(PLAN_NAME, BUILD_NAME, TEST_CASE);
		}catch(Exception e){
			reportTestFailResult(PLAN_NAME, BUILD_NAME, TEST_CASE, e);
		}
	}
}