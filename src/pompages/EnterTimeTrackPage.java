package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterTimeTrackPage {
	@FindBy(xpath="//div[contains(text(),'Help')]")
	WebElement helpMenu;

	@FindBy(linkText="About your actiTIME")
	WebElement AboutActiTimeLink;

	@FindBy(xpath="(//span[contains(text(),'actiTIME')])[1]")
	WebElement productVersion;

	@FindBy(xpath="//td[@class='close-button']//div[@id ='aboutPopupCloseButtonId']")
	WebElement closePopUpWindow;


	@FindBy(id="logoutLink")
	WebElement logoutBtn;


	public EnterTimeTrackPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public void clickHelpMenu(){
		helpMenu.click();
	}
	public void clickAboutActiTimeLink(){
		AboutActiTimeLink.click();
	}
	public String getproductVersion(){
		return	productVersion.getText();	
	}
	public void clickClosePopup(){
		closePopUpWindow.click();	
	}

	public void clickLogOut(){
		logoutBtn.click();
	}
}