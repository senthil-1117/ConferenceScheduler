package com.thoughtworks.conference.bo;

import java.util.Calendar;

import com.thoughtworks.conference.constants.ConferenceConstants;

/**
 * This class is a base BO of an Event. It contains name , duration and startTime of Event.
 *  Initally initialized from input as events without startTime, considered as input Event.
 *  Once sent to ScheduleProcessor, startTime will get assigned as per the criteria matching.
 *
 */
public class Event {
	
	private String name;
	private int duration;
	private Calendar startTime;
	
	public Event(String name, int duration, Calendar startTime){
		this.name = name;
		this.duration = duration;
		this.startTime = startTime;
	}
	
	public Event(String name, int duration){
		this.name = name;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	@Override
	public String toString(){
		if(this.startTime != null){
			return ConferenceConstants.EVENT_DATE_FORMAT.format(this.startTime.getTime()) + " " + this.name + " " + this.duration;	
		}
		return this.name + " " + this.duration;
	}
	
	// Assuming no two events can be of same name and duration
	@Override
	public boolean equals(Object obj){
		
		if(obj != null){
			return	((Event)obj).getName().equalsIgnoreCase(this.getName()) && ((Event)obj).getDuration() == this.getDuration();
		}
		return false;
	}

	@Override
	public int hashCode(){
		return this.getName().toLowerCase().hashCode() * this.getDuration();
	}

}
