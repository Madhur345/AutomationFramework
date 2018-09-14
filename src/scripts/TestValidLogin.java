package scripts;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Lib;
import pompages.LoginPage;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class TestValidLogin extends BaseTest {
	String PLAN_NAME ="Accotech";
	String BUILD_NAME="Build -1";
	Logger log =LogManager.getLogger(TestValidLogin.class.getName());
	@Test
	public void testValidLogin() throws InterruptedException, TestLinkAPIException{
		String result ="";
		String execNotes ="null";
		log.debug("creating an of object of LoginPage Pom class");
		LoginPage l = new LoginPage(driver);
		log.info("object creation is successful");
		//enter username
		log.debug("fetching username from excel file");
		String username=Lib.getCellValue("ValidLogin", 1, 0);
		log.info("username successfully fetched");
		l.setUserName(username);
		//enter password
		String password=Lib.getCellValue("ValidLogin", 1, 1);
		l.setPassword(password);
		//click on login buttton
		Thread.sleep(5000);
		l.clickLogin();
		try{
			result=TestLinkAPIResults.TEST_PASSED;
			execNotes ="Test case Excecuted Successfully";
			updateTestLinkResult("AT-4", execNotes, result);
		}catch(Exception e){
			result =TestLinkAPIResults.TEST_FAILED;
			execNotes = "Test case Excecution Failed because  "+e.getMessage();
			updateTestLinkResult("AT-4", execNotes, result);
		}
	}
}
