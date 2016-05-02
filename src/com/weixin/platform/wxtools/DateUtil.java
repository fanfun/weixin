package com.weixin.platform.wxtools;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: liuhm Date: 11-10-31
 */
public class DateUtil {

	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String HOUR_PATTERN = "yyyy-MM-dd-HH";
	public static final String MIN_PATTERN = "yyyy-MM-dd-HH-mm";
	public static final String MSEL_PATTERN = "yyyy-MM-dd-HH-mm-ss.SSS";
	public static final String COMMON_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static long yesterdayBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long yesterdayEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}

	public static Date yesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Date(calendar.getTimeInMillis());
	}

	public static long todayBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long todayEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}
	public static long lastweekTodayBeginTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long lastweekTodayEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}
	
    public static long relativeDayBeginTime(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
    }


    public static long relativeDayEndTime(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
    }


    public static long relativeDayBeginTime(long millis, int days) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
    }
    
    public static long relativeHourEndTime(long millis, int days) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
    }

    public static long relativeDayEndTime(long millis, int days) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
    }


	public static Date relativeTodayByDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Date(calendar.getTimeInMillis());
	}

	public static boolean isSameDay(Long start, Long end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(start);
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTimeInMillis(end);
		return date == calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static long lastHour() {
		return lastHours(1);
	}

	public static long last2Hours() {
		return lastHours(2);
	}

	public static long lastHours(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, (-1) * n);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	public static String dayPattern(long time) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		return format.format(new Date(time));
	}

	public static String hourPattern(long time) {
		SimpleDateFormat format = new SimpleDateFormat(HOUR_PATTERN);
		return format.format(new Date(time));
	}

	public static String minutePattern(long time) {
		SimpleDateFormat format = new SimpleDateFormat(MIN_PATTERN);
		return format.format(new Date(time));
	}

	public static String millisecondPattern(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(MSEL_PATTERN);
		return format.format(date);
	}

	public static Date parseDate(String time, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date time, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(time);
	}

	public static String[] last2HourPattern() {
		return new String[] { hourPattern(System.currentTimeMillis()),
				hourPattern(lastHour()), hourPattern(last2Hours()) };
	}

	public static String[] last1HourPattern() {
		return new String[] { hourPattern(System.currentTimeMillis()),
				hourPattern(lastHour()) };
	}

	public static String parseDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static Date parseDateBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date parseDateEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getDateWithLongType(Long dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateTime);
		return calendar.getTime();
	}

	public static Date addDate(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
	/**
	 * @param date
	 * @return 0，1，2....23
	 */
	public static int getHourOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public static int getMinuteOfHour(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minute = calendar.get(Calendar.MINUTE);
		return minute;
	}
	
	public static int getSecondOfMinute(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minute = calendar.get(Calendar.SECOND);
		return minute;
	}
	
	public static String getHourMinute(Date date){
		StringBuffer hourMinute = new StringBuffer();
		int hour = getHourOfDay(date);
		int minute = getMinuteOfHour(date);
		String result;
		if(minute < 10){
			result = hour+":0"+minute;
		}else{
			result = hour+":"+minute;
		}
		hourMinute.append(result);
		return hourMinute.toString();
	}
	
	public static Long getLastMinutes(Date date,int mount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, mount);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}



}
