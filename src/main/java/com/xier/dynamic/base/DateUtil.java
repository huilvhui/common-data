package com.xier.dynamic.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 时间工具类
 * </p>
 * @author lvhui5 2017年5月18日 下午5:34:38
 * @version V1.0
 */
public class DateUtil {
	
	// 检索传参时间格式
	private static final String TIME_PATTERN_SS = "yyyy-MM-dd HH:mm:ss";
	//private static final String TIME_PATTERN_SS_NEW = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String TIME_PATTERN_LONG = "yyyyMMddHHmmss";
	private static final String TIME_PATTERN_LONG_MS = "yyyyMMddHHmmssSSS";
	private static final String DATE_PATTERN_LONG = "yyyyMMdd";
	
	/**
	 * 返回yyyyMMddHHmmss String类型的时期
	 * @author lvhui5 2017年5月23日 上午11:59:49
	 * @param time
	 * @throws ParseException
	 */
	public static String getLongTimeString(String time) throws ParseException {
		if (time == null)
			return StringUtils.EMPTY;
		DateFormat format = new SimpleDateFormat(TIME_PATTERN_SS);
		Date date = null;
		date = format.parse(time);
		return String.valueOf(getTimeLong(date));
	}
	
	public static Date getStringTimeDate(String time) throws ParseException {
		if (time == null)
			return new Date();
		DateFormat format = new SimpleDateFormat(TIME_PATTERN_SS);
		return format.parse(time);
	}
	
	public static Long getLongTime(String time) throws ParseException {
		if (time == null){
			return getTimeLong(new Date());
		}
		DateFormat format = new SimpleDateFormat(TIME_PATTERN_SS);
		Date date = null;
		date = format.parse(time);
		return getTimeLong(date);
	}
	
	
	public static String getDateTimeString(Date aDate) {
		SimpleDateFormat df;
		String returnValue = StringUtils.EMPTY;
		if (aDate != null) {
			df = new SimpleDateFormat(TIME_PATTERN_SS);
			returnValue = df.format(aDate);
			return returnValue;
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	public static Date getLongTimeDate(String time) throws ParseException {
		if (time == null)
			return new Date();
		DateFormat format = new SimpleDateFormat(TIME_PATTERN_LONG);
		Date date = null;
		date = format.parse(time);
		return date;
	}
	
	/**
	 * 返回 yyyyMMddHHmmss integer类型的时期
	 * @param @param aDate
	 * @param @return
	 * @return Integer
	 * @throws
	 */
	public static Long getTimeLong(Date aDate) {
		SimpleDateFormat df;
		String returnValue = StringUtils.EMPTY;
		if (aDate != null) {
			df = new SimpleDateFormat(TIME_PATTERN_LONG);
			returnValue = df.format(aDate);
		} else {
			df = new SimpleDateFormat(TIME_PATTERN_LONG);
			returnValue = df.format(new Date());
		}
		
		return (Long.parseLong(returnValue));
	}
	
	/**
	 * 返回 yyyyMMdd integer类型的时期
	 * @param @param aDate
	 * @param @return
	 * @return Integer
	 * @throws
	 */
	public static Long getDateLong(Date aDate) {
		SimpleDateFormat df;
		String returnValue = StringUtils.EMPTY;
		if (aDate != null) {
			df = new SimpleDateFormat(DATE_PATTERN_LONG);
			returnValue = df.format(aDate);
		} else {
			df = new SimpleDateFormat(DATE_PATTERN_LONG);
			returnValue = df.format(new Date());
		}
		
		return (Long.parseLong(returnValue));
	}
	


	/**
	 * 比较两个时间的时分秒的前后
	 * @author lvhui5 2017年7月28日 下午12:16:00
	 * @param first
	 * @param second
	 * @return
	 */
	public static int compareTime(Date first, Date second) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(first);
		c2.setTime(second);
		if (c1.get(Calendar.HOUR_OF_DAY) < c2.get(Calendar.HOUR_OF_DAY))
			return -1;
		if (c1.get(Calendar.HOUR_OF_DAY) > c2.get(Calendar.HOUR_OF_DAY))
			return 1;
		if (c1.get(Calendar.MINUTE) < c2.get(Calendar.MINUTE))
			return -1;
		if (c1.get(Calendar.MINUTE) > c2.get(Calendar.MINUTE))
			return 1;
		if (c1.get(Calendar.SECOND) < c2.get(Calendar.SECOND))
			return -1;
		if (c1.get(Calendar.SECOND) > c2.get(Calendar.SECOND))
			return 1;
		return 0;
	}
	
	/**
	 * 转化到今天的同个时间点
	 * @author lvhui5 2017年7月28日 下午1:55:25
	 * @param first
	 * @return
	 */
	public static Date toTodayTime(Date date,Date destDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date);
		if(destDate != null)
			c2.setTime(destDate);
		c2.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
		c2.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		c2.set(Calendar.SECOND, c1.get(Calendar.SECOND));
		return c2.getTime();
	}
	
	/**
	 * 年、月、日
	 * @author lvhui5 2017年7月21日 上午11:47:27
	 * @param date
	 * @return 返回数组{年份、月、星期几,日期（YYMMDD）,每月日期、小时、分钟}
	 * @throws ParseException
	 */
	public static int[] getYearMouthDay(Long dateTime) {
		DateFormat format = new SimpleDateFormat(TIME_PATTERN_LONG);
		Date date = null;
		try {
			date = format.parse(String.valueOf(dateTime));
		} catch (ParseException e) {
			return null;
		}	
		if(date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int[] result = {cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_WEEK) - 1,
		        Integer.parseInt(getShortDateLongString(date)), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY),
		        cal.get(Calendar.MINUTE)};
		return result;
	}
	
	public static String getDateLongString(Date aDate) {
		SimpleDateFormat df;
		String returnValue = StringUtils.EMPTY;
		if (aDate != null) {
			df = new SimpleDateFormat(TIME_PATTERN_LONG);
			returnValue = df.format(aDate);
			return returnValue;
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	public static String getShortDateLongString(Date aDate) {
		SimpleDateFormat df;
		String returnValue = StringUtils.EMPTY;
		if (aDate != null) {
			df = new SimpleDateFormat(DATE_PATTERN_LONG);
			returnValue = df.format(aDate);
			return returnValue;
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	// ===========================================================================================
	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	public DateUtil() {
	}
	
	/**
	 * 传入时间类型转换String格式
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		String strdate = StringUtils.EMPTY;
		SimpleDateFormat df = new SimpleDateFormat(TIME_PATTERN_LONG_MS);
		strdate = df.format(date);
		return strdate;
	}
	
	/**
	 * 取得从startDate开始的前(正)/后(负)day天
	 * @param startDate
	 * @param day
	 * @return
	 */
	public static Date getRelativeDate(Date startDate, int day) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return calendar.getTime();
		} catch (Exception e) {
			return startDate;
		}
	}
	
	/**
	 * 取得从startDate开始的前(正)/后(负)小时
	 * @param startDate
	 * @param hour
	 * @return
	 */
	public static Date getRelativeHour(Date startDate, int hour) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(startDate);
			calendar.add(Calendar.HOUR, hour);
			return calendar.getTime();
		} catch (Exception e) {
			return startDate;
		}
	}
	
	/**
	 * 取得从startDate开始的前(正)/后(负)分钟
	 * @param startDate
	 * @param minute
	 * @return
	 */
	public static Date getRelativeMinute(Date startDate, int minute) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(startDate);
			calendar.add(Calendar.MINUTE, minute);
			return calendar.getTime();
		} catch (Exception e) {
			return startDate;
		}
	}
	
	
	/**
	 * 转化到下一个整点小时
	 * @author lvhui5 2017年7月28日 下午1:55:25
	 * @return  yyyy-MM-dd HH:mm:ss
	 */
	public static Date toNextHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(getRelativeHour(date,1));
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 转化到下一个整点分钟
	 * @author lvhui5 2017年7月28日 下午1:55:25
	 * @return  yyyy-MM-dd HH:mm:ss
	 */
	public static Date toNextMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(getRelativeMinute(date,1));
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 转化到下一天 的1点
	 * @author lvhui5 2017年7月28日 下午1:55:25
	 * @return  yyyy-MM-dd HH:mm:ss
	 */
	public static Date toNextDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(getRelativeDate(date,1));
		c.set(Calendar.HOUR_OF_DAY, 01);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	

	public static Date getCurrentDay(){
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
		
	}
	
}
