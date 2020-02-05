package com.thoughtworks.conference.util;

import java.util.Comparator;

import com.thoughtworks.conference.bo.Event;

/**
 * This comparator helps to sort the event based on its duration
 *
 */
public class EventDurationComparator implements Comparator<Event>{

	@Override
	public int compare(Event e1, Event e2) {
		
		 if (e1.getDuration() < e2.getDuration()) {
			 return 1;
	        } 
		return -1;
	}
}


