package addressbook;

import addressbook.waitutils.Waiter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import addressbook.pages.*;
import ru.stqa.selenium.factory.WebDriverPool;

public class BaseClass
{

	public WebDriver driver;
	private DesiredCapabilities browserCapabilities;
	private LoginPage loginPage;
	public Waiter waiter = new Waiter();
	public TestData testData = new TestData();
	public Helper helper = new Helper();

	/************************************* В каком браузере будем проводить тестирование? ******************************
	 * BROWSER = 0   Mozilla Firefox
	 * BROWSER = 1   Google Chrome
	 * ****************************************************************************************************************/

	/**
	 * Указание пути к драйверам, выбор, запуск и настройка браузера перед первым тестом в наборе
	 * @throws IOException
	 */
	@BeforeSuite(alwaysRun = true)
	public void setUp() throws IOException
	{
		testData = new TestData();

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

		switch (testData.BROWSER)
		{
			case "0":
				browserCapabilities = DesiredCapabilities.firefox();
				browserCapabilities.setPlatform(Platform.LINUX);
				break;
			case "1":
				browserCapabilities = DesiredCapabilities.chrome();
				browserCapabilities.setPlatform(Platform.LINUX);
				break;
		}

		if (this.driver == null) {
			if(testData.HUB.equals("")) {
				this.driver = WebDriverPool.DEFAULT.getDriver(browserCapabilities);
			} else {
				this.driver = WebDriverPool.DEFAULT.getDriver(testData.HUB, browserCapabilities);
			}
			this.driver.manage().deleteAllCookies();
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}

	/**
	 * Переход на стартовую страницу приложения перед каждым тестом
	 * @throws Exception
	 */
	@BeforeMethod(alwaysRun = true)
	public void goToLoginPage() throws Exception {
		loginPage = new LoginPage(this.driver);
		loginPage.getPage();
	}

	/**
	 * Закрытие всех открытых драйверов(браузеров)
	 */
	@AfterSuite(alwaysRun = true)
	public void tearDown()
	{
		WebDriverPool.DEFAULT.dismissAll();

	}

}
