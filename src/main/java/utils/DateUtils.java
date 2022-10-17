package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	private static final SimpleDateFormat sdfDefault = new SimpleDateFormat("MM/d/yyyy");
	private static final SimpleDateFormat sdfUS = new SimpleDateFormat("MM/dd/yyyy");

	private Calendar calendar;
	private SimpleDateFormat dateFormat;

	public static int getCurrentYear() {
		return Calendar.YEAR;
	}

	private DateUtils(SimpleDateFormat dateFormat) {
		calendar = GregorianCalendar.getInstance();
		this.dateFormat = dateFormat;
	}

	public static DateUtils withDefaultDateFormat() {
		return new DateUtils(sdfDefault);
	}

	public static DateUtils withUSDateFormat() {
		return new DateUtils(sdfUS);
	}

	public static DateUtils withDateFormat(SimpleDateFormat simpleDateFormat) {
		return new DateUtils(simpleDateFormat);
	}

	public static DateUtils withSimpleDateFormat(String pattern) {
		return new DateUtils(new SimpleDateFormat(pattern));
	}

	public static Date getDateFromString(SimpleDateFormat format, String stringDate) {
		Date date;
		try {
			date = format.parse(stringDate);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	public static Date getUsDateFromString(String stringDate) {
		return getDateFromString(sdfUS, stringDate);
	}

	public String now() {
		return nowPlusNDays(0);
	}

	public String yesterday() {
		return nowPlusNDays(-1);
	}

	public String nowPlusOneDay() {
		return nowPlusNDays(1);
	}

	public String nowPlusTwoDays() {
		return nowPlusNDays(2);
	}

	public String nowMinusTwoDays() {
		return nowPlusNDays(-2);
	}

	public String nowPlusOneWeek() {
		return nowPlusNDays(7);
	}

	public String nowPlusTwoWeeks() {
		return nowPlusNDays(14);
	}

	public String nowPlusOneWeekAndTwoDays() {
		return nowPlusNDays(9);
	}

	public String nowPlusOneMonth() {
		return nowPlusNMonths(1);
	}

	public String plusRandomOfMaxNDays(int n) {
		return nowPlusNDays((int) (Math.random() * n + 1));
	}

	public String nowPlusNDays(int n) {
		if (n != 0) {
			calendar.add(Calendar.DAY_OF_YEAR, n);
		}
		return dateFormat.format(calendar.getTime());
	}

	public String nowPlusNDaysShift(int n) {
		if (n != 0) {
			calendar.add(Calendar.DAY_OF_MONTH, n);
		}
		return dateFormat.format(calendar.getTime());
	}

	private String nowPlusNMonths(int n) {
		if (n != 0) {
			calendar.add(Calendar.MONTH, n);
		}
		return dateFormat.format(calendar.getTime());
	}

	public String nowMinusNDays(int n) {
		if (n != 0) {
			calendar.add(Calendar.DAY_OF_YEAR, -n);
		}
		return dateFormat.format(calendar.getTime());
	}
	public static void main(String[] args) {
		System.out.println(DateUtils.withDefaultDateFormat().now());
	}
}
