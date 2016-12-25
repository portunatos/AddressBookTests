package addressbook.pages;

import addressbook.waitutils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddressBook {
	WebDriver driver;
	Waiter waiter = new Waiter();

	public AddressBook(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	//***************************************************Elements******************************************************/

	@FindBy(css = "h3.ng-scope")
	public WebElement infoPageName;

	@FindBy(css = "button[ng-click='$mdOpenMenu()']")
	public WebElement buttonOpenMenu;

	@FindBy(css = "button[ng-click='logout()']")
	public WebElement buttonLogout;

	//***************************************************Methods*******************************************************/

	public void clickButtonOpenMenu() throws Exception {
		waiter.waitForElementClickable(driver, buttonOpenMenu);
		buttonOpenMenu.click();
	}

	public LoginPage clickButtonLogot() throws Exception {
		waiter.waitForElementClickable(driver, buttonLogout);
		buttonLogout.click();
		return new LoginPage(driver);
	}
}
