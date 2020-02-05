package com.thoughtworks.conference.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.thoughtworks.conference.bo.Event;
import com.thoughtworks.conference.util.CalendarUtil;

public class ConferenceConstants {

	public static final SimpleDateFormat EVENT_DATE_FORMAT = new SimpleDateFormat("hh:mm a");

	public static final String LIGHTNING = "LIGHTNING";
	public static final String LUNCH = "LUNCH TIME";
	public static final String NW_EVENT = "NETWORKING EVENT";

	public static final int MORNING_AVAIL_MINUTES = 180;
	public static final int NOON_AVAIL_MINUTES = 240;

	public static final Calendar MORNING_START_TIME = CalendarUtil.getEventTimeAs(9, 00);
	public static final Calendar NOON_START_TIME = CalendarUtil.getEventTimeAs(13, 00);
	
	public static final Event LUNCH_EVENT = new Event(LUNCH, 60, CalendarUtil.getEventTimeAs(12, 00));

}
