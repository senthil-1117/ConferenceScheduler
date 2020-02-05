package com.thoughtworks.conference.util;

import java.util.Calendar;
/*
 * CalendarUtil class to provide useful methods involving calendar
 */
public class CalendarUtil {
	
	
	/**
	 * @param hours
	 * @param minute
	 * @return Calendar
	 * 
	 * This method returns a Calendar instance set for given hours and minute
	 */
	public static Calendar getEventTimeAs(int hours, int minute){
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minute);
		return calendar;
	}
	
	/**
	 * @param givenTime
	 * @param minutes
	 * @return Calendar
	 * 
	 * This method returns a Calendar instance with minutes added to the given Calendar Time.
	 * Effectively used to get the next start time of the event for scheduling.
	 */
	public static Calendar getNextStartTime(Calendar givenTime, int minutes){
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, givenTime.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, givenTime.get(Calendar.MINUTE));
		calendar.add(Calendar.MINUTE, minutes);
		return calendar;
		
	}

}
