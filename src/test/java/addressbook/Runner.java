package addressbook;


import addressbook.pages.AddressBook;
import addressbook.pages.LoginPage;
import addressbook.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Runner extends BaseClass {
    LoginPage loginPage;
    RegisterPage registerPage;
    AddressBook addressBook;


    @Test()
    public void A1_RegistrationWithAllFields() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        String email = helper.getCorrectEmail();
        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, testData.CORRECT_PASSWORD1);

        loginPage = registerPage.clickButtonCompleteRegisterWithCorrectValues();
        waiter.waitForElementWithAnyText(driver, loginPage.infoFormName);

        Assert.assertEquals(
                loginPage.infoFormName.getText(),
                "Dreamfactory - Addressbook 2.0",
                "Ошибка! Некорректное название формы"
        );

        loginPage.fillLoginForm(email, testData.CORRECT_PASSWORD1);

        addressBook = loginPage.clickButtonSubmit();
        waiter.waitForElementWithAnyText(driver, addressBook.infoPageName);

        Assert.assertEquals(
                addressBook.infoPageName.getText(),
                "Contacts",
                "Ошибка! Некорректное название формы"
        );
    }

    @Test()
    public void A2_RegistrationWithEmailAndPassword() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        String email = helper.getCorrectEmail();
        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, testData.CORRECT_PASSWORD2);

        loginPage = registerPage.clickButtonCompleteRegisterWithCorrectValues();
        waiter.waitForElementWithAnyText(driver, loginPage.infoFormName);

        Assert.assertEquals(
                loginPage.infoFormName.getText(),
                "Dreamfactory - Addressbook 2.0",
                "Ошибка! Некорректное название формы"
        );

        loginPage.fillLoginForm(email, testData.CORRECT_PASSWORD2);

        addressBook = loginPage.clickButtonSubmit();
        waiter.waitForElementWithAnyText(driver, addressBook.infoPageName);

        Assert.assertEquals(
                addressBook.infoPageName.getText(),
                "Contacts",
                "Ошибка! Некорректное название формы"
        );
    }

    @Test()
    public void A3_RegistrationWithEmptyFields() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }

    @Test()
    public void A4_RegistrationWithoutEmail() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, "", testData.CORRECT_PASSWORD1);

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }

    @Test()
    public void A5_RegistrationWithoutPassword() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        String email = helper.getCorrectEmail();
        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, "");

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }

    @Test()
    public void A6_RegistrationWithoutEmailAndPassword() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, "", "");

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }

    @Test()
    public void A7_RegistrationWithIncorrectEmail() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        registerPage.fillRegisterForm(
                testData.FIRST_NAME,
                testData.LAST_NAME,
                testData.INCORRECT_EMAIL,
                testData.CORRECT_PASSWORD1
        );
        registerPage.waitForRedEmailField();

        Assert.assertFalse(
                registerPage.buttonCompleteRegister.isEnabled(),
                "Ошибка! Кнопка должна быть недоступной"
        );
    }

    @Test()
    public void A8_RegistrationWithIncorrectPassword() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        String email = helper.getCorrectEmail();
        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, testData.INCORRECT_PASSWORD);

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }

    @Test()
    public void A9_RegistrationWithExistingEmail() throws Exception
    {
        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        String email = helper.getCorrectEmail();
        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, testData.CORRECT_PASSWORD1);

        loginPage = registerPage.clickButtonCompleteRegisterWithCorrectValues();
        waiter.waitForElementWithAnyText(driver, loginPage.infoFormName);

        Assert.assertEquals(
                loginPage.infoFormName.getText(),
                "Dreamfactory - Addressbook 2.0",
                "Ошибка! Некорректное название формы"
        );

        loginPage.fillLoginForm(email, testData.CORRECT_PASSWORD1);

        addressBook = loginPage.clickButtonSubmit();
        waiter.waitForElementWithAnyText(driver, addressBook.infoPageName);

        Assert.assertEquals(
                addressBook.infoPageName.getText(),
                "Contacts",
                "Ошибка! Некорректное название формы"
        );

        addressBook.clickButtonOpenMenu();

        loginPage = addressBook.clickButtonLogot();

        registerPage = loginPage.clickButtonRegister();
        waiter.waitForElementPresent(driver, registerPage.fieldFirstName);
        waiter.waitForElementWithAnyText(driver, registerPage.infoFormName);

        Assert.assertEquals(
                registerPage.infoFormName.getText(),
                "Register",
                "Ошибка! Некорректное название формы"
        );

        registerPage.fillRegisterForm(testData.FIRST_NAME, testData.LAST_NAME, email, testData.CORRECT_PASSWORD1);

        registerPage.clickButtonCompleteRegisterWithIncorrectValues();

        waiter.waitForElementWithAnyText(driver, registerPage.infoError);

        Assert.assertEquals(
                registerPage.infoError.getText(),
                "Error: Validation failed",
                "Некорректный текст ошибки!"
        );
    }
}
