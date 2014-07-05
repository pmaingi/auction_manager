package com.bryma.auction_manager.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * DateUtils
 * Handles Date functions
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 24 September 2013
 * @since  enter jdk version
 */
public class DateUtils {
	
	private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
	
	private static SimpleDateFormat formatter;
	
	public static final String SQL_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss.S";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 
	 * <p>
	 * Format date from dd/MM/yyyy to yyyy-MM-dd
	 * </p>
	 * @param dateString
	 * @return
	 */
	public static String formatToSQLDateString1(String dateString) {

		
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm)");
		SimpleDateFormat source = new SimpleDateFormat("yyyy-MM-dd HH:mm)");
		
		String date = null;
		try {
			if (StringUtils.paramSet(dateString))
			//	date = formatter.format(source.parse(dateString));
			date = source.format(formatter.parse(dateString));

		} catch (ParseException e) {
		}
		return date;
	}
	
	public static String formatToSQLDateString(String dateString) {

		SimpleDateFormat source = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = null;
		try {
			if (StringUtils.paramSet(dateString))
				date = formatter.format(source.parse(dateString));

		} catch (ParseException e) {
		}
		return date;
	}
	
	public static Date dateEdited(String inputString){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS ");
		Date d = null; 
		try {
			d = df.parse(inputString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date simpleStringToDate(Date enddate) { 

		Date newDate = null;
		//SimpleDateFormat source = new SimpleDateFormat(SQL_DATE_PATTERN);
		formatter = new SimpleDateFormat(DATE_TIME_PATTERN);
		try {
		//	newDate = source.parse(enddate);
			return formatter.parse(formatter.format(newDate));
		} catch (ParseException e) {
			LOGGER.error("Date Parse Error", e);
		}
		return newDate;
	}
	
	/**
	 * 
	 * my changes
	 * <p>
	 * @return
	 */
	public static String editDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = "2008-10-14";
		Date dateStr = null;
		try {
			dateStr = formatter.parse(strDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String formattedDate = formatter.format(dateStr);
		System.out.println("yyyy-MM-dd date is ==>"+formattedDate);
		Date date1 = null;
		try {
			date1 = formatter.parse(formattedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formattedDate = formatter.format(date1);
		System.out.println("dd-MMM-yyyy date is ==>"+formattedDate);
		return formattedDate;
	}
	
	/**
	 * 
	 * <p>
	 * File date format dd_mm_yyyy_hh_mm_ss
	 * </p>
	 * @return
	 */
	public static String fileDateFormat() {

		formatter = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		return formatter.format(new Date());
	}
	
	/**
	 * 
	 * <p>
	 * Get month from date string
	 * </p>
	 * @param dateString
	 * @return
	 */
	public static String getMonth(String dateString){
		SimpleDateFormat source = new SimpleDateFormat("dd-MM-yyyy");
		formatter = new SimpleDateFormat("MMM");
		String date = null;
		try {
			if (StringUtils.paramSet(dateString))
				date = formatter.format(source.parse(dateString));

		} catch (ParseException e) {
		}
		return date;
	}
	
	
	public static String toEuropeTime(String dateString){
		SimpleDateFormat source = new SimpleDateFormat("yyyy-MM-dd");
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = null;
		try {
			if (StringUtils.paramSet(dateString))
				date = formatter.format(source.parse(dateString));

		} catch (ParseException e) {
		}
		return date;
	}
	
	
	public static String currentYear() {

		formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date());
	}
	
	
	public static void main(String [] args){
		//System.out.println(currentYear());
		System.out.println(formatToSQLDateString1("2014-02-25 07:36:25.0"));
		
	}
}