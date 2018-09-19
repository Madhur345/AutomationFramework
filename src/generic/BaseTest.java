package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class BaseTest implements IAutoConstant{
	String result ="";
	String execNotes;
	public static String DEV_KEY=Lib.getPropertyValue("DEV_KEY");
	public static String SERVER_URL=Lib.getPropertyValue("SERVER_URL");
	public static String PROJECT_NAME =Lib.getPropertyValue("PROJECT_NAME");
	public WebDriver driver;
	static{
		System.setProperty(	GECKO_KEY, GECKO_VALUE);
		System.setProperty(	CHROME_KEY, CHROME_VALUE);
	}
	@BeforeMethod
	public void openApplication() throws TestLinkAPIException{
		driver = new FirefoxDriver();
		String url =Lib.getPropertyValue("URL");
		driver.get(url);
		String ITO =Lib.getPropertyValue("ImplicitWait");
		long timeout =Long.parseLong(ITO);
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}
	public void updateTestLinkResult(String planName, String buildName, String testCaseName, String execNotes, String result) throws TestLinkAPIException{
		TestLinkAPIClient testlink = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		testlink.reportTestCaseResult(PROJECT_NAME, planName, testCaseName, buildName, execNotes, result);
	}


	public void reportTestPassResult(String planName, String buildName, String testCase) throws TestLinkAPIException{
		result=TestLinkAPIResults.TEST_PASSED;
		execNotes ="Test case Excecuted Successfully";
		updateTestLinkResult(planName,buildName, testCase, execNotes, result);
	}

	public void reportTestFailResult(String planName, String buildName, String testCase, Exception e) throws TestLinkAPIException{
		result =TestLinkAPIResults.TEST_FAILED;
		execNotes = "Test case Excecution Failed because  "+ e.getMessage();
		updateTestLinkResult(planName,buildName, testCase, execNotes, result);
		Lib.captureScreenshot(driver,testCase);
	}

	@AfterMethod
	public void closeApplication(){
		driver.quit();
	}
}