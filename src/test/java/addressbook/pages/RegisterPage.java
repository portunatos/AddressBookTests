package addressbook.pages;


import addressbook.TestData;
import addressbook.waitutils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    Waiter waiter = new Waiter();
    TestData testData = new TestData();

    public RegisterPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //***************************************************Elements******************************************************/
    @FindBy(css = "h1")
    public WebElement infoFormName;

    @FindBy(css = "span.ng-binding")
    public WebElement infoError;

    @FindBy(css = "input[ng-model='firstName']")
    public WebElement fieldFirstName;

    @FindBy(css = "input[ng-model='lastName']")
    public WebElement fieldLastName;

    @FindBy(css = "input[type='email']")
    public WebElement fieldRegEmail;

    @FindBy(css = "input[type='password']")
    public WebElement fieldRegPassword;

    @FindBy(css = "button[ng-click='register()']")
    public WebElement buttonCompleteRegister;

    //***************************************************Methods*******************************************************/
    /**
     * Очистка поля и ввод текста
     * @param element
     * @param text
     */
    public void fillField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public LoginPage clickButtonCompleteRegisterWithCorrectValues() throws Exception {
        buttonCompleteRegister.click();
        waiter.waitForElementRemoved(driver, fieldFirstName);
        return new LoginPage(driver);
    }

    public void clickButtonCompleteRegisterWithIncorrectValues() throws Exception {
        buttonCompleteRegister.click();
    }

    /**
     * Заполнение всех полей на форме регистрации
     * @param fName
     * @param lName
     * @param email
     * @param pass
     */
    public void fillRegisterForm(String fName, String lName, String email, String pass) {
        fillField(fieldFirstName, fName);
        fillField(fieldLastName, lName);
        fillField(fieldRegEmail, email);
        fillField(fieldRegPassword, pass);
    }

    /**
     * Ожидание, пока границы элемента не отобразятся красным цветом
     * @throws Exception
     */
    public void waitForRedEmailField() throws Exception {
        String cssParam = testData.BROWSER.equals("0") ? "border-top-color" : "border-color";
        waiter.waitForElementPresentWithCssValue(driver, fieldRegEmail, cssParam,"rgb(244, 67, 54)");
    }
}
