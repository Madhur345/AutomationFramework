package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;


public class BaseTest implements IAutoConstant{
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
	public void updateTestLinkResult(String testCaseName, String execNotes, String result) throws TestLinkAPIException{
		String DEV_KEY=Lib.getPropertyValue("DEV_KEY");
		String SERVER_URL=Lib.getPropertyValue("SERVER_URL");
		String PROJECT_NAME =Lib.getPropertyValue("PROJECT_NAME");
		TestLinkAPIClient testlink = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		testlink.reportTestCaseResult(PROJECT_NAME, PLAN_NAME, testCaseName, BUILD_NAME, execNotes, result);
	}
	@AfterMethod
	public void closeApplication(ITestResult results){
		if(ITestResult.FAILURE==results.getStatus()){
			Lib.captureScreenshot(driver,results.getName());
		}
		driver.quit();
	}
}

