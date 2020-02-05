package com.thoughtworks.conference.bo;

import java.util.List;

/**
 * This class is to have the events with scheduled start time and the count of track
 * Schedule(1, List<Event>) meaning - schedule 1 got this many list of events.
 *
 */
public class Schedule {
	
	public int count;
	public List<Event> eventList;

	public Schedule(int counter, List<Event> eventList){
		this.count = counter;
		this.eventList = eventList;
	}
	
	public List<Event> getEventList() {
		return eventList;
	}

	public void printSchedule(){
		System.out.println("TRACK : " + count);
		for(Event event : eventList){
			System.out.println(event.toString());
		}
	}
	
}
