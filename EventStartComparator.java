package com.thoughtworks.conference.util;

import java.util.Comparator;

import com.thoughtworks.conference.bo.Event;

/**
 * This comparator helps to sort the event based on its start time.
 *
 */
public class EventStartComparator implements Comparator<Event>{

	@Override
	public int compare(Event e1, Event e2) {
		
		 if (e1.getStartTime().compareTo(e2.getStartTime()) > 0) {
			 return 1;
	        } 
		return -1;
	}
}


