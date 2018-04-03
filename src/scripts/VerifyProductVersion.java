package scripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Lib;
import pompages.EnterTimeTrackPage;
import pompages.LoginPage;

public class VerifyProductVersion extends BaseTest {
	@Test

	public void testProductVersion() throws InterruptedException{
		LoginPage l = new LoginPage(driver);
		//enter username
		String username=Lib.getCellValue("ValidLogin", 1, 0);
		l.setUserName(username);
		//enter password
		String password=Lib.getCellValue("ValidLogin", 1, 1);
		l.setPassword(password);
		//click on login
		Thread.sleep(5000);
		l.clickLogin();
		Thread.sleep(5000);
		String expectedTilte=Lib.getCellValue("ProductVersion", 1, 0);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(expectedTilte));
		EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
		//click on Help Menu
		Thread.sleep(5000);
		e.clickHelpMenu();
		//clcik on about actime
		Thread.sleep(3000);
		e.clickAboutActiTimeLink();
		//fetch product version
		Thread.sleep(2000);
		String expectedProductVersion=Lib.getCellValue("ProductVersion", 1, 1);
		String actualProductVersion=e.getproductVersion();
		SoftAssert s = new SoftAssert();
		s.assertEquals(actualProductVersion, expectedProductVersion);
		Thread.sleep(2000);
		e.clickClosePopup();
		//click on logout
		Thread.sleep(2000);
		e.clickLogOut();
		s.assertAll();
	}
}