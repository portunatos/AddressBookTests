package addressbook.waitutils;

import addressbook.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waiter {
    TestData testData = new TestData();

    /**
     * Ожидание элемента содержащего какой-либо текст
     * @param driver
     * @param element
     * @throws Exception
     */
    public void waitForElementWithAnyText(WebDriver driver, WebElement element)
            throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, testData.TIMEOUT_WAIT, 500);
        wait.until((ExpectedCondition<Boolean>) driver1 -> {
            try
            {
                return !(element.getText().equals(""));
            }
            catch (StaleElementReferenceException e)
            {
                return false;
            }
        });
    }

    /**
     * Ожидание пока элемент станет видимым
     * @param driver
     * @param element
     * @throws Exception
     */
    public void waitForElementPresent(WebDriver driver, final WebElement element)
            throws Exception
    {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, testData.TIMEOUT_WAIT, 500);
        wait.until(new ExpectedCondition<Boolean>()
        {
            @Override
            public Boolean apply(WebDriver driver)
            {
                try
                {
                    return element.isDisplayed();
                }
                catch (StaleElementReferenceException e)
                {
                    return false;
                }
            }
        });

        driver.manage().timeouts().implicitlyWait(testData.TIMEOUT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * Ожидание пока элемент станет невидимым
     * @param driver
     * @param webElement
     * @throws Exception
     */
    public void waitForElementRemoved(WebDriver driver, WebElement webElement)
            throws Exception
    {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        boolean flag = true;
        int counter = 0;
        while(flag)
        {
            if (counter> (int)(testData.TIMEOUT_WAIT*1000/500))
            {
                throw new Exception ("Ошибка во время выполнения теста. " +
                        "В метод WaitForElementRemoved передан WebElement " +
                        webElement +
                        " который не удаляется"
                );
            }
            try
            {
                Thread.sleep(500);
                counter++;
                if (!webElement.isDisplayed()) flag = false;
            }
            catch (Exception e)
            {
                flag = false;
            }
        }

        driver.manage().timeouts().implicitlyWait(testData.TIMEOUT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * Ожидание пока элемент станет доступным(enable) и видимым(visible)
     * @param driver
     * @param element
     * @throws Exception
     */
    public void waitForElementClickable(WebDriver driver, WebElement element)
            throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, testData.TIMEOUT_WAIT, 500);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Ожидание элемента с конкретным указанным значением указанного css-свойства
     * @param driver
     * @param webElement
     * @param cssParam
     * @param cssValue
     * @throws Exception
     */
    public void waitForElementPresentWithCssValue(WebDriver driver, final WebElement webElement, final String cssParam,
                                                  final String cssValue)
            throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, testData.TIMEOUT_WAIT, 500);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String cssParameter = webElement.getCssValue(cssParam);
                if(cssParameter.equals(cssValue))
                    return true;
                else
                    return false;
            }
        });
    }
}
