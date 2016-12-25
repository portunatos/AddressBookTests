package addressbook;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Helper {
	TestData testData = new TestData();

	/**
	 * Получение текущей даты с заданным форматом
	 * @param format
	 * @return
	 */
	public String getCurrentDate(String format)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		Date verifiableDate = calendar.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(verifiableDate);
	}

	/**
	 * Получение валидного уникального email
	 * @return
	 */
	public String getCorrectEmail() {
		return getCurrentDate("yyyyMMddHHmmssSSS") + testData.CORRECT_EMAIL;
	}
}
