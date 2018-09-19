package scripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Lib;
import pompages.EnterTimeTrackPage;
import pompages.LoginPage;
import testlink.api.java.client.TestLinkAPIException;

public class VerifyProductVersion extends BaseTest {
	String PLAN_NAME ="Accotech";
	String BUILD_NAME="Build -1";
	String TEST_CASE="AT-8";
	@Test

	public void testProductVersion() throws InterruptedException, TestLinkAPIException{
		try{
			LoginPage l = new LoginPage(driver);
			String username=Lib.getCellValue("ValidLogin", 1, 0);
			l.setUserName(username);
			String password=Lib.getCellValue("ValidLogin", 1, 1);
			l.setPassword(password);
			Thread.sleep(5000);
			l.clickLogin();
			Thread.sleep(5000);
			String expectedTilte=Lib.getCellValue("ProductVersion", 1, 0);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.titleIs(expectedTilte));
			EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
			Thread.sleep(5000);
			e.clickHelpMenu();
			Thread.sleep(3000);
			e.clickAboutActiTimeLink();
			Thread.sleep(2000);
			String expectedProductVersion=Lib.getCellValue("ProductVersion", 1, 1);
			String actualProductVersion=e.getproductVersion();
			SoftAssert s = new SoftAssert();
			s.assertEquals(actualProductVersion, expectedProductVersion);
			Thread.sleep(2000);
			e.clickClosePopup();
			Thread.sleep(2000);
			e.clickLogOut();
			s.assertAll();

			reportTestPassResult(PLAN_NAME, BUILD_NAME, TEST_CASE);
		}catch(Exception e){
			reportTestFailResult(PLAN_NAME, BUILD_NAME, TEST_CASE, e);
		}
	}
}