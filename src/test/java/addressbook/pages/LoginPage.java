package addressbook.pages;

import addressbook.TestData;
import addressbook.waitutils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    WebDriver driver;
    TestData testData = new TestData();
    Waiter waiter = new Waiter();

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //***************************************************Elements******************************************************/

    @FindBy(css = "h1")
    public WebElement infoFormName;

    @FindBy(css = "button[ng-click='register()']")
    public WebElement buttonToRegister;

    @FindBy(css = "button[type='submit']")
    public WebElement buttonSubmit;

    @FindBy(css = "input[type='email']")
    public WebElement fieldEmail;

    @FindBy(css = "input[type='password']")
    public WebElement fieldPassword;

    //***************************************************Methods*******************************************************/

    /**
     * Переход по указанному URL
     */
    public void getPage() {
        driver.navigate().to(testData.APP_URL);
    }

    /**
     * Очистка поля и ввод текста
     * @param element
     * @param text
     */
    public void fillField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public RegisterPage clickButtonRegister() throws Exception {
        waiter.waitForElementClickable(driver, buttonToRegister);
        buttonToRegister.click();
        return new RegisterPage(driver);
    }

    /**
     * Очистка и ввод значений в поля логин и пароль на странице авторизации
     * @param email
     * @param password
     */
    public void fillLoginForm(String email, String password) {
        fillField(fieldEmail, email);
        fillField(fieldPassword, password);
    }

    public AddressBook clickButtonSubmit() {
        buttonSubmit.click();
        return new AddressBook(driver);

    }
}
