package cn.wxy.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

public final class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	/** new a Calendar instance */
	static GregorianCalendar cldr = new GregorianCalendar();

	/** the milli second of a day */
	public static final long DAYMILLI = 24 * 60 * 60 * 1000;

	/** the milli seconds of an hour */
	public static final long HOURMILLI = 60 * 60 * 1000;

	/** the milli seconds of a minute */
	public static final long MINUTEMILLI = 60 * 1000;

	/** the milli seconds of a second */
	public static final long SECONDMILLI = 1000;

	/** added time */
	public static final String TIMETO = " 23:59:59";

	/**
	 * set the default time zone
	 */
	static {
		cldr.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9:00"));
	}

	/** flag before */
	public static final transient int BEFORE = 1;

	/** flag after */
	public static final transient int AFTER = 2;

	/** flag equal */
	public static final transient int EQUAL = 3;

	/** date format dd/MMM/yyyy:HH:mm:ss +0900 */
	public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";

	/** date format dd/MM/yyyy:HH:mm:ss +0900 */
	public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";

	/** date format yyyy-MM-dd HH:mm:ss */
	public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** date format YYYY-MM-DD HH24:MI:SS */
	public static final String DB_TIME_PATTERN = "YYYY-MM-DD HH24:MI:SS";

	/** date format dd/MM/yy HH:mm:ss */
	public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";

	/** date format dd/MM/yy HH24:mm */
	public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";

	/** date format yyyy年MM月dd日 HH:mm:ss */
	public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";

	/** date format yyyyMMddHHmmss */
	public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";

	/** date format yyyyMMddHHmmssSSS */
	public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";

	/** date format yyyyMMdd */
	public static final String DATE_FMT_0 = "yyyyMMdd";

	/** date format yyyy/MM/dd */
	public static final String DATE_FMT_1 = "yyyy/MM/dd";

	/** date format yyyy/MM/dd hh:mm:ss */
	public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";

	/** date format yyyy-MM-dd */
	public static final String DATE_FMT_3 = "yyyy-MM-dd";

	/** date format yyyy年MM月dd日 */
	public static final String DATE_FMT_4 = "yyyy年MM月dd日";

	/** date format yyyy-MM-dd HH */
	public static final String DATE_FMT_5 = "yyyy-MM-dd HH";

	/** date format yyyy-MM */
	public static final String DATE_FMT_6 = "yyyy-MM";

	/** date format MM月dd日 HH:mm */
	public static final String DATE_FMT_7 = "MM月dd日 HH:mm";

	/** date format MM月dd日 HH:mm */
	public static final String DATE_FMT_8 = "HH:mm:ss";
	/** date format yyyy.MM.dd */
	public static final String DATE_FMT_9 = "yyyy.MM.dd";

	public static final String DATE_FMT_10 = "HH:mm";

	public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";

	/** date format yyyy年MM月dd日 */
	public static final String DATE_FMT_12 = "MM月dd日";


	public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";


	public static final String DATE_FMT_14 = "yyyyMM";

	public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";

	public static final String DATE_FMT_16 = "yyyy-MM-dd HH:mm";


	//public static final String DATE_FMT_16 = "yyyy-MM-dd:HH"; release

	public static final String DATE_FMT_17 = "mm";

	/** 账单初始时间1900-01-01 00:00:00 */
	public static final Date BILL_INIT_DATE = DateUtils.getDateFromString("1900-01-01 00:00:00");

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date getDateFromString(String dateStr) {
		return getDateFromString(dateStr, null);
	}

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date getDateTimeFromString(String dateStr) {
		return getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromString(String dateStr, String pattern) {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}

	/**
	 * 时间戳转为日期
	 *
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromLong(Long time, String pattern) {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (time == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date(time * 1000);
		String dateStr = format.format(date);
		if (StringUtils.isEmpty(dateStr)){
			return null;
		}
		return getDateFromString(dateStr,pattern);
	}

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static Date getDateFromString(String dateStr, String pattern, Locale locale) {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}

	/**
	 * 日期转为字串
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStrFromDate(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		if(date==null){
			return "";
		}
		String s = df.format(date);
		return s;
	}

	/**
	 * 日期转字串
	 *
	 * @param date
	 * @return
	 */
	public static String getLongStrFromDate(Date date) {
		return getStrFromDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期转字串
	 *
	 * @param date
	 * @return
	 */
	public static String getDateStrFromDate(Date date) {
		return getStrFromDate(date, "yyyy-MM-dd");
	}

	/**
	 * 时间格式转换
	 *
	 * @param timeValue
	 * @return
	 */
	public static Date format(String timeValue) {
		timeValue = timeValue.replaceAll("[年月/\\\\]", "-");
		timeValue = timeValue.replaceAll("<hr>|日|\\s+", "");
		if (Pattern.matches("^[\\d/-]{1,10}$", timeValue)) {
			int length = timeValue.length();
			switch (length) {
				case 10:
					break;
				case 8:
					timeValue = String.format("%s-%s-%s", timeValue.substring(0, 4), timeValue.substring(4, 6), timeValue.substring(6, 8));
					break;
				case 1:
					timeValue = "1900-01-0" + timeValue;
					break;
				case 2:
					timeValue = "1900-01-" + timeValue;
					break;
				case 3:
					timeValue = "1900-0" + timeValue.substring(0, 1) + "-" + timeValue.substring(1, 3);
					break;
				case 4:
					timeValue = "1900-" + timeValue.substring(0, 2) + "-" + timeValue.substring(2, 4);
					break;
				case 5:
					timeValue = "1900-" + timeValue.replace("/", "-");
					break;
				case 6:
					timeValue = String.format("19%s-%s-%s", timeValue.substring(0, 2), timeValue.substring(2, 4), timeValue.substring(4, 6));
					break;
			}
			return getDateFromString(timeValue);
		}
		return null;
	}

	/**
	 * change string to date 将String类型的日期转成Date类型
	 *
	 * @param sDate
	 *            the date string
	 * @param sFmt
	 *            the date format
	 *
	 * @return Date object
	 */
	public static Date toDate(String sDate, String sFmt) {
		if (StringUtil.isBlank(sDate) || StringUtil.isBlank(sFmt)) {
			return null;
		}

		SimpleDateFormat sdfFrom = null;
		Date dt = null;
		try {
			sdfFrom = new SimpleDateFormat(sFmt);
			dt = sdfFrom.parse(sDate);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		} finally {
			sdfFrom = null;
		}

		return dt;
	}

	/**
	 * change date to string 将日期类型的参数转成String类型
	 *
	 * @param dt
	 *            a date
	 *
	 * @return the format string
	 */
	public static String toString(Date dt) {
		return toString(dt, DATE_FMT_0);
	}

	/**
	 * change date object to string 将String类型的日期转成Date类型
	 *
	 * @param dt
	 *            date object
	 * @param sFmt
	 *            the date format
	 *
	 * @return the formatted string
	 */
	public static String toString(Date dt, String sFmt) {
		if (null == dt || StringUtil.isBlank(sFmt)) {
			return null;
		}

		SimpleDateFormat sdfFrom = null;
		String sRet = null;
		try {
			sdfFrom = new SimpleDateFormat(sFmt);
			sRet = sdfFrom.format(dt).toString();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		} finally {
			sdfFrom = null;
		}

		return sRet;
	}

	/**
	 * 获取Date所属月的最后一天日期
	 *
	 * @param date
	 * @return Date 默认null
	 */
	public static Date getMonthLastDate(Date date) {
		if (null == date) {
			return null;
		}

		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);

		Date lastDate = ca.getTime();
		return lastDate;
	}

	/**
	 * 获取Date所属月的最后一天日期
	 *
	 * @param date
	 * @param pattern
	 * @return String 默认null
	 */
	public static String getMonthLastDate(Date date, String pattern) {
		Date lastDate = getMonthLastDate(date);
		if (null == lastDate) {
			return null;
		}

		if (StringUtil.isBlank(pattern)) {
			pattern = TIME_PATTERN;
		}

		return toString(lastDate, pattern);
	}

	/**
	 * 获取Date所属月的第一天日期
	 *
	 * @param date
	 * @return Date 默认null
	 */
	public static Date getMonthFirstDate(Date date) {
		if (null == date) {
			return null;
		}

		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.DAY_OF_MONTH, 1);

		Date firstDate = ca.getTime();
		return firstDate;
	}

	/**
	 * 获取Date所属月的第一天日期
	 *
	 * @param date
	 * @param pattern
	 * @return String 默认null
	 */
	public static String getMonthFirstDate(Date date, String pattern) {
		Date firstDate = getMonthFirstDate(date);
		if (null == firstDate) {
			return null;
		}

		if (StringUtil.isBlank(pattern)) {
			pattern = TIME_PATTERN;
		}

		return toString(firstDate, pattern);
	}

	/**
	 * 两个时间间隔除以24小时
	 *
	 * @param firstDate
	 *            小者
	 * @param lastDate
	 *            大者
	 * @return int 默认-1
	 */
	public static int getIntervalDays(Date firstDate, Date lastDate) {
		if (null == firstDate || null == lastDate) {
			return -1;
		}

		long intervalMilli = lastDate.getTime() - firstDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * 两个时间间隔除以24小时
	 *
	 * @param firstDate
	 * @param lastDate
	 * @return
	 */
	public static int daysOfTwo(Date firstDate, Date lastDate) {
		return getIntervalDays(firstDate, lastDate);
	}

	/**
	 * 以日历的角度计算两个日期（忽略时分秒）间隔的天数，同日算一天。
	 *
	 * @param firstDate
	 * @param lastDate
	 * @return
	 */
	public static int daysByCalendar(Date firstDate, Date lastDate) {
		firstDate = formatDate(firstDate, "yyyy-MM-dd");
		lastDate  = formatDate(lastDate, "yyyy-MM-dd");
		int days=getIntervalDays(firstDate, lastDate);
		return days+1;
	}

	/**
	 * 计算两个日期间隔的小时数
	 *
	 * @param firstDate
	 *            小者
	 * @param lastDate
	 *            大者
	 * @return int 默认-1
	 */
	public static int getTimeIntervalHours(Date firstDate, Date lastDate) {
		if (null == firstDate || null == lastDate) {
			return -1;
		}

		long intervalMilli = lastDate.getTime() - firstDate.getTime();
		return (int) (intervalMilli / (60 * 60 * 1000));
	}

	/**
	 * 计算两个日期间隔的分钟数
	 *
	 * @param firstDate
	 *            小者
	 * @param lastDate
	 *            大者
	 * @return int 默认-1
	 */
	public static int getTimeIntervalMins(Date firstDate, Date lastDate) {
		if (null == firstDate || null == lastDate) {
			return -1;
		}

		long intervalMilli = lastDate.getTime() - firstDate.getTime();
		return (int) (intervalMilli / (60 * 1000));
	}

	/**
	 * 计算两个日期间隔的秒数
	 *
	 * @param firstDate
	 *            小者
	 * @param lastDate
	 *            大者
	 * @return int 默认-1
	 */
	public static int getTimeIntervalSencond(Date firstDate, Date lastDate) {
		if (null == firstDate || null == lastDate) {
			return -1;
		}
		long intervalMilli = lastDate.getTime() - firstDate.getTime();
		return (int) (intervalMilli / 1000);
	}

	/**
	 * format the date in given pattern 格式化日期
	 *
	 * @param d
	 *            date
	 * @param pattern
	 *            time pattern
	 * @return the formatted string
	 */
	public static String formatDatetoString(Date d, String pattern) {
		if (null == d || StringUtil.isBlank(pattern)) {
			return null;
		}

		SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();

		formatter.applyPattern(pattern);
		return formatter.format(d);
	}

	public static Date formatDate(Date d, String pattern) {
		return DateUtils.getDateFromString(formatDatetoString(d, pattern));
	}

	/**
	 * 比较两个日期的先后顺序
	 *
	 * @param src
	 * @param desc
	 * @return
	 */
	public static int compareDate(Date src, Date desc) {
		if ((src == null) && (desc == null)) {
			return EQUAL;
		} else if (desc == null) {
			return BEFORE;
		} else if (src == null) {
			return AFTER;
		} else {
			long timeSrc = src.getTime();
			long timeDesc = desc.getTime();

			if (timeSrc == timeDesc) {
				return EQUAL;
			} else {
				return (timeDesc > timeSrc) ? AFTER : BEFORE;
			}
		}
	}

	/**
	 * 比较两个日期的先后顺序
	 *
	 * @param first
	 *            date1
	 * @param second
	 *            date2
	 *
	 * @return EQUAL - if equal BEFORE - if before than date2 AFTER - if over
	 *         than date2
	 */
	public static int compareTwoDate(Date first, Date second) {
		if ((first == null) && (second == null)) {
			return EQUAL;
		} else if (first == null) {
			return BEFORE;
		} else if (second == null) {
			return AFTER;
		} else if (first.before(second)) {
			return BEFORE;
		} else if (first.after(second)) {
			return AFTER;
		} else {
			return EQUAL;
		}
	}

	/**
	 * 比较日期是否介于两者之间
	 *
	 * @param date
	 *            the specified date
	 * @param begin
	 *            date1
	 * @param end
	 *            date2
	 *
	 * @return true - between date1 and date2 false - not between date1 and
	 *         date2
	 */
	public static boolean isDateBetween(Date date, Date begin, Date end) {
		int c1 = compareTwoDate(begin, date);
		int c2 = compareTwoDate(date, end);

		return (((c1 == BEFORE) && (c2 == BEFORE)) || (c1 == EQUAL) || (c2 == EQUAL));
	}

	/**
	 * 比较日期是否介于当前日期的前后数天内
	 *
	 * @param myDate
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isDateBetween(Date myDate, int begin, int end) {
		return isDateBetween(myDate, getCurrentDateTime(), begin, end);
	}

	/**
	 * 比较日期是否介于指定日期的前后数天内
	 *
	 * @param utilDate
	 * @param dateBaseLine
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isDateBetween(Date utilDate, Date dateBaseLine, int begin, int end) {
		String pattern = TIME_PATTERN;

		String my = toString(utilDate, pattern);
		Date myDate = toDate(my, pattern);

		String baseLine = toString(dateBaseLine, pattern);

		// Date baseLineDate = parseString2Date(baseLine, pattern);
		String from = addDays(baseLine, begin);
		Date fromDate = toDate(from, pattern);

		String to = addDays(baseLine, end);
		Date toDate = toDate(to, pattern);

		return isDateBetween(myDate, fromDate, toDate);
	}

	/**
	 * 增加天数
	 *
	 * @param date
	 * @param day
	 * @return Date
	 */
	public static Date addDate(Date date, int day) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
		return calendar.getTime();
	}

	/**
	 * 增加月数
	 *
	 * @param date
	 * @param month
	 *            需要增加的月数，比如需要增加2个月，就传入2
	 * @return
	 */

	public static Date addMonth(Date date, int month) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		return calendar.getTime();
	}

	/**
	 * 增加小时
	 *
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		return calendar.getTime();
	}
	
	/**
	 * 增加分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
		return calendar.getTime();
	}
	
	/**
	 *
	 * @Description:增加秒数
	 * @param date
	 * @param seconds
	 * @return
	 * @author: wangyanji
	 * @time:2015年12月24日 下午5:14:48
	 */
	public static Date addSecond(Date date, int seconds) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 增加天数
	 *
	 * @param date
	 * @param day
	 * @param pattern
	 * @return
	 */
	public static String addDays(Date date, int day, String pattern) {
		return addDays(toString(date, pattern), day, pattern);
	}

	/**
	 * 增加天数
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDays(Date date, int day) {
		return addDays(toString(date, TIME_PATTERN), day);
	}

	/**
	 * 增加天数
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDays(String date, int day) {
		return addDays(date, day, TIME_PATTERN);
	}

	/**
	 * get the time of the specified date after given days
	 *
	 * @param date
	 *            the specified date
	 * @param day
	 *            day distance
	 *
	 * @return the format string of time
	 */
	public static String addDays(String date, int day, String pattern) {
		if (date == null) {
			return "";
		}

		if (date.equals("")) {
			return "";
		}

		if (day == 0) {
			return date;
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Calendar calendar = dateFormat.getCalendar();

			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
			return dateFormat.format(calendar.getTime());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return "";
		}
	}

	public static Date addDaysByDate(Date date, int day) {
		if (date == null) {
			return null;
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
			Calendar calendar = dateFormat.getCalendar();

			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
			return calendar.getTime();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * change timestamp to formatted string
	 *
	 * @param t
	 *            Timestamp
	 * @param sFmt
	 *            date format
	 *
	 * @return formatted string
	 */
	public static String formatTimestamp(Timestamp t, String sFmt) {
		if (t == null || StringUtil.isBlank(sFmt)) {
			return "";
		}

		t.setNanos(0);

		DateFormat ft = new SimpleDateFormat(sFmt);
		String str = null;

		try {
			str = ft.format(t);
		} catch (NullPointerException ex) {
			logger.error(ex.getMessage(), ex);
		}

		return str;
	}

	/**
	 * return current date
	 *
	 * @return current date
	 */
	public static Date getCurrentDate() {
		return getCurrentCalendar().getTime();
	}

	/**
	 * return current calendar instance
	 *
	 * @return Calendar
	 */
	public static Calendar getCurrentCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * return current time
	 *
	 * @return current time
	 */
	public static Timestamp getCurrentDateTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取年份
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static final int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取年份
	 *
	 * @param millis
	 *            long
	 * @return int
	 */
	public static final int getYear(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static final int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取月份
	 *
	 * @param millis
	 *            long
	 * @return int
	 */
	public static final int getMonth(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static final int getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获取小时
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static final int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取小时
	 *
	 * @param millis
	 *            long
	 * @return int
	 */
	public static final int getHour(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
	 *
	 * @param fullDate
	 * @return Date
	 */
	public static final Date zerolizedTime(Date fullDate) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(fullDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 把日期的时间变成(yyyy-MM-dd 23:59:59:999)
	 *
	 * @param date
	 * @return
	 */
	public static final Date getEndTime(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String getTimeFrom(Date date) {
		String format = "";
		if (date != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			format = sim.format(date);
		}
		return format;
	}

	/**
	 * 获取日期对应周一的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return zerolizedTime(calendar.getTime());
	}

	/**
	 * 获取周一的时间
	 *
	 * @return
	 */
	public static Date getMondayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return zerolizedTime(calendar.getTime());
	}

	/**
	 * 获取日期对应周日的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return zerolizedTime(calendar.getTime());
	}

	/**
	 * 获取周日的时间
	 *
	 * @return
	 */
	public static Date getSundayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return zerolizedTime(calendar.getTime());
	}

	/**
	 * 根据传入的年月日组装成string日期yyyy-MM-dd
	 *
	 * @return
	 */
	public static String getStringDateByYMD(int year, int month, int day) {
		return year + "-" + month + "-" + day;
	}

	/**
	 * 返回指定时间的后面一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextDay(String date) {
		return getDateFromString(addDays(date, 1));
	}

	/**
	 * 返回指定时间的后面一天
	 *
	 * @param second
	 * @return
	 */
	public static Date getNextDay(long second) {
		if (second == 0) {
			return null;
		}
		return toDate(addDays(getDate(second), 1), TIME_PATTERN);
	}

	/**
	 * 给返回时间加上空值判断
	 *
	 * @param date
	 * @return
	 */
	public static Long getTime(Date date) {
		if (date == null) {
			return null;
		} else {
			return date.getTime();
		}
	}

	/**
	 * 给返回的日期加上空值判断
	 *
	 * @param second
	 * @return
	 */
	public static Date getDate(long second) {
		if (second == 0) {
			return null;
		} else {
			return new Date(second * 1000);
		}
	}

	/**
	 * 比较两个时间是否同一天
	 *
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean isTheSameDay(Date begin, Date end) {
		String beginDate = DateUtils.formatDatetoString(begin, "yyyy-MM-dd");
		String endDate = DateUtils.formatDatetoString(end, "yyyy-MM-dd");
		return beginDate.equals(endDate);
	}

	/**
	 * 计算当前时间到 午夜 23.59.59.999 的秒数
	 *
	 * @return
	 */
	public static int calculateCurrentToEndTime() {
		Date current = getCurrentDate();
		Date end = getEndTime(getCurrentDate());
		int timeIntervalSencond = getTimeIntervalSencond(current, end);
		return timeIntervalSencond;
	}

	/**
	 * 时间格式转换 转换成String格式
	 *
	 * @param dateString
	 *            转换的时间
	 * @param formatIn
	 *            转换前格式
	 * @param formatOut
	 *            转换后格式
	 * @return
	 */
	public static String convertToString(String dateString, String formatIn, String formatOut) {
		try {
			DateFormat formatInDf = getNewDateFormat(formatIn);
			DateFormat formatOutDf = getNewDateFormat(formatOut);
			Date date = formatInDf.parse(dateString);

			return formatOutDf.format(date);
		} catch (ParseException e) {
			logger.warn("convert() --- orign date error: " + dateString);
			return "";
		}
	}

	/**
	 * 时间格式转换，转换成Date格式
	 *
	 * @param dateString
	 *            转换的时间
	 * @param formatIn
	 *            转换前格式
	 * @param formatOut
	 *            转换后格式
	 * @return
	 */
	public static Date convertToDate(String dateString, String formatIn, String formatOut) {
		String dateStr = convertToString(dateString, formatIn, formatOut);
		return getDateFromString(dateStr, TIME_PATTERN);
	}

	public static DateFormat getNewDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);

		df.setLenient(false);
		return df;
	}

	/***
	 * 获取昨天日期
	 *
	 * @return author: pengyong 下午6:47:21
	 */
	public static Date getYesterDay() {
		return addDate(getCurrentDate(), -1);
	}

	public static Date addYearsApart(Date past, int year) {
		if (past == null) {
			past = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(past);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}


	/**
	 * 计算日期范围内相差的月份（每月30天）。
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static float getIntervalMonth(Date startDate, Date endDate) {
		float month = 0.00f;
		Date sd = DateUtils.formatDate(startDate, DateUtils.DATE_FMT_3);
		Date ed = DateUtils.formatDate(endDate, DateUtils.DATE_FMT_3);
		Long m = ed.getTime() - sd.getTime();
		if (m > 0) {
			month = (float) m / 1000 / 60 / 60 / 24 / 30;
			BigDecimal b = new BigDecimal(month);
			month = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		return month;
	}

	/**
	 *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
	 *
	 * @param date 日期字符串
	 * @return 返回格式化的日期
	 * @throws ParseException 分析时意外地出现了错误异常
	 */
	public static Date strToDateFormat(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false);
		Date newDate= null;
		try {
			newDate = formatter.parse(date);
		} catch (ParseException e) {

		}
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatDate = formatter.format(newDate);
		try {
			return formatter.parse(formatDate);
		} catch (ParseException e) {

		}
		return new Date();
	}

	public static void main(String[] args) throws ParseException {
		//Date d = addDaysByDate(new Date(), 7-1);
		/*System.out.println(DateUtils.formatDate(DateUtils.getYesterDay(), DateUtils.DATE_FMT_3));*/
		Date date = DateUtils.strToDateFormat("20171228");
		System.out.print(date);
/* 		SimpleDateFormat ddd=new SimpleDateFormat("yyyyMMdd");
		ddd.parse("20160913");
		System.out.println(ddd.parse("20160913"));*/
		// Date addYearsApart =
		// addYearsApart(getBirthdayByIdentity("330501199612196245"),18);
		// if(addYearsApart.before(getCurrentDate())){
		// System.out.println("sss");
		// }
//		String s = isJudgeAfterFifteen();
//		System.out.println(s);
		/*int borrowPeriod =30;
		Date date = toDate("20151121",DATE_FMT_0);
		Date addDate = addDate(date, borrowPeriod);
		System.out.println(getTimeFrom(date));
		System.out.println(getTimeFrom(addDate));
		Calendar  calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar acalendar = Calendar.getInstance();
		acalendar.setTime(addDate);
		//int monthsOfAge = getMonthsOfAge(calendar, acalendar);
		int monthsOfAge = getMonthsBetweenDates(date, addDate);
		System.out.println("借款天数:"+borrowPeriod);

		Date temp = date;
		System.out.println("借款开始时间"+getTimeFrom(date));
		int totalDay = 0;
		for (int i =1;i <= monthsOfAge;i++) {
			System.out.println("第"+i+"个周期开始时间"+getTimeFrom(temp));
			Date endDateAddMonth = null;
			int intervalDays = 0;
			//最后一个周期 =
			if (i == monthsOfAge) {
				int i1 = borrowPeriod - totalDay ;
				endDateAddMonth = DateUtils.addDate(temp, i1 );
				System.out.println("第"+i+"个周期结束时间"+getTimeFrom(endDateAddMonth));
				intervalDays = DateUtils.getIntervalDays(temp, endDateAddMonth) ;
				totalDay  = intervalDays+totalDay;
			}else {
				endDateAddMonth =  DateUtils.addMonth(temp,1);
				endDateAddMonth = DateUtils.addDate(endDateAddMonth, -1 );
				System.out.println("第"+i+"个周期结束时间"+getTimeFrom(endDateAddMonth));
				intervalDays = DateUtils.getIntervalDays(temp, endDateAddMonth) +1;
				totalDay  = intervalDays+totalDay;
				temp = DateUtils.addDate(endDateAddMonth,1);

			}

			System.out.println("第"+i+"个周期天数"+intervalDays);
			//System.out.println("第"+i+"个周期开始时间长度"+totalDay);

		}
		System.out.println("第周期天数"+totalDay);*/

	}

	public static String isJudgeAfterFifteen() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);
		Date calTime = cal.getTime();
		int i = compareDate(calTime, date);
		return formatDatetoString(addDaysByDate(date, i), DATE_FMT_12);
	}

	/**
	 * 计算两个日期的月份
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonthsBetweenDates(Date startDate, Date endDate)
	{
		if(startDate.getTime() > endDate.getTime())
		{
			Date temp = startDate;
			startDate = endDate;
			endDate = temp;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		int yearDiff = endCalendar.get(Calendar.YEAR)- startCalendar.get(Calendar.YEAR);
		int monthsBetween = endCalendar.get(Calendar.MONTH)-startCalendar.get(Calendar.MONTH) +12*yearDiff;

		if(endCalendar.get(Calendar.DAY_OF_MONTH) >= startCalendar.get(Calendar.DAY_OF_MONTH))
			monthsBetween = monthsBetween + 1;
		return monthsBetween;

	}

	public static String prettyTime(Date inputTime) {
		if (inputTime == null) {
			return "";
		}
		// 计算抽奖时间差
		int time = DateUtils.getTimeIntervalMins(inputTime, DateUtils.getCurrentDate());
		if (time < 1) {
			return "刚刚";
		} else if (time < 60) {
			return time + "分钟前";
		} else if (time < (60 * 24)) {
			return (int) (time / 60) + "小时前";
		} else if (time < (60 * 24 * 30)) {
			return (int) (time / (60 * 24)) + "天前";
		} else if (time < (60 * 24 * 30 * 12)) {
			return (int) (time / (60 * 24 * 30)) + "个月前";
		} else {
			return (int) (time / (60 * 24 * 30 * 12)) + "年前";
		}
	}
	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate)
	{
		SimpleDateFormat sdf=new SimpleDateFormat(DateUtils.DATE_FMT_3);
		try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 *
	 * @Description:判断是否是周末
	 * @param date
	 * @return
	 * @author: wangyanji
	 * @time:2016年4月10日 下午2:57:07
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}
	/**
	 *
	 * @Description:是否为空
	 * @param date
	 * @return
	 * @author: chaisen
	 * @time:2016年6月1日 上午11:27:25
	 */
	public static boolean isDateNull(Date date) {
		if(date==null){
			return true;
		}
		return false;
	}
	/**
	 *
	 * @Description:获取两个时间之间的间隔
	 * @param date
	 * @param sdate
	 * @return
	 * @author: zhanghao
	 * @time:2016年6月15日 下午18:27:25
	 */
	public static String betweenTwoDays(Date fdate,Date sdate){
		if(fdate!=null&&sdate!=null){

			if(fdate.getTime()>sdate.getTime()){
				return "0分钟";
			}
			long interval = (fdate.getTime() - sdate.getTime())/1000;
			interval = Math.abs(interval);
			String result ="";
			long year = 12 * 30 * 24 * 60 * 60;
			long month = 30 * 24 * 60 * 60;
			long day = 24 * 60 * 60;
			long hour = 60 * 60;
			long min = 60 ;
			 /*if(interval/year>0){
				 result = result + interval/year +"年";
			 }
			 if (interval%year/month>0){
				 result = result + interval%year/month +"个月";
			 }*/
			 /*if(interval/day>0){
				 result = result + interval/day +"天";
			 }*/
			if(interval/hour>0){
				result = result + interval/hour +"小时";
			}else if(interval%hour/min>0){
				result = result + interval%hour/min +"分钟";
			}else{//不足1分钟按1分钟处理
				result = result + "1分钟";
			}
			 /*if(interval%day%hour%min>0){
				 result = result + interval%year%month%day%hour%min +"秒";
			 }*/
			return result;
		}
		return "";
	}

	/**
	 *
	 * @Description:把日期的时间变成(yyyy-MM-dd n:00:00:00)
	 * @param date
	 * @return
	 * @author: chaisen
	 * @time:2016年6月30日 下午2:06:15
	 */
	public static final Date getSpecialTime(Date date,int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
		return cal.getTime();
	}


	public static Date getDateFromStrings(String dateStr, String pattern) throws ParseException {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(dateStr);
		return date;
	}


	public static final Date getTimes(Date date){
		Date now_10 = new Date(date.getTime() - 600000); //10分钟前的时间
		return now_10;
	}
	public static boolean isValidDate(String str,String pattern) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess=false;
		}
		return convertSuccess;
	}

	public static Date formatDateTime(Date date, String pattern) {
		if (null == date || StringUtil.isBlank(pattern)) {
			return null;
		}
		String DateTime = 	DateUtils.getStrFromDate(date,pattern);
		return DateUtils.getDateFromString(DateTime,pattern);
	}

	public static String getJuShiOnlineTime() {
		Calendar calendar=Calendar.getInstance();
		calendar.set(2017, 10, 01);
		Date date=calendar.getTime();
		return DateUtils.formatDatetoString(date,DateUtils.TIME_PATTERN);
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 *
	 * @return nowTimeStamp
	 */
	public static String getNowTimeStamp() {
		long time = System.currentTimeMillis();
		String nowTimeStamp = String.valueOf(time / 1000);
		return nowTimeStamp;
	}

	/**
	 * 日期格式转换成时间戳
	 *
	 * @param date 日期
	 * @param format   如：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String Date2TimeStamp(Date date, String format) {
		if(date == null){
			return "";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return (date.getTime() / 1000) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 日期格式字符串转换成时间戳
	 *
	 * @param dateStr 字符串日期
	 * @param format   如：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String Date2TimeStamp(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String Date2TimeStamp(String dateStr) {
		return Date2TimeStamp(dateStr,TIME_PATTERN);
	}

	public static String Date2TimeStamp(Date date) {
		return Date2TimeStamp(date,TIME_PATTERN);
	}

	/**
	 * Java将Unix时间戳转换成指定格式日期字符串
	 * @param timestampString 时间戳 如："1473048265";
	 * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
	 *
	 * @return 返回结果 如："2016-09-05 16:06:42";
	 */
	public static String TimeStamp2Date(String timestampString, String formats) {
		if (TextUtils.isEmpty(formats))
			formats = "yyyy-MM-dd HH:mm:ss";
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
		return date;
	}
}
