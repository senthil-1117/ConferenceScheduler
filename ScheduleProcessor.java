package com.thoughtworks.conference.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.conference.bo.Event;
import com.thoughtworks.conference.bo.Schedule;
import com.thoughtworks.conference.constants.ConferenceConstants;
import com.thoughtworks.conference.util.CalendarUtil;
import com.thoughtworks.conference.util.EventDurationComparator;
import com.thoughtworks.conference.util.EventStartComparator;

/**
 * This class is the process the input list of events and organise them effectively into Schedules.
 *  ScheduledEvent means Event with a start time.
 *  For ex, if given x list of events to prepareSchedule method, scheduleList will have schedules as
 *  Schedule(1, List<Event>) meaning - schedule 1 got this many list of events.
 *  Scdhule(2, List<Event>) meaning  - schedule 2 got this many list of events. 
 *  etc.
 *
 */

public class ScheduleProcessor {

	private List<Schedule> scheduleList = new ArrayList<Schedule>();

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	/**
	 * @param totalEventList
	 * 
	 * Logic used:
	 * 1. Sort the given input list.
	 * 2. Iterate given list in while loop till it got an entry.
	 * 3. Make a for loop with counter and fetch by index using counter. 
	 * (For-Each loop gives concurrentModificationException. Other option to go for iterator. No much difference in timing as tested)
	 * 4. Allocate the event to morning or noon scheduledEvent depending on duration is less than remaining time.
	 * 5. Remove the event from input List once it is added to scheduledEvent
	 * 6. if event cant fit to morning or evening remaining time, add the scheduledEvent list to schedule List and reIterate this method for remanining events. 
	 * 7. Before adding to schedule List, add the constant lunch event and networking event to scheduledEvent.
	 */
	public void prepareSchedule(List<Event> totalEventList) {

		if(totalEventList == null){
			return;
		}
		
		// sort to schedule effectively. Improper ordering of events may result in exteded schedule. 
		// Test with NeedOfSortInput.txt as input and sorting line as commented for understanding.
		Collections.sort(totalEventList, new EventDurationComparator());
		
		// initial values before scheduling for a day
		int morningRemainingTime = ConferenceConstants.MORNING_AVAIL_MINUTES;
		int noonRemaningTime = ConferenceConstants.NOON_AVAIL_MINUTES;;
		Calendar morningStartTime = ConferenceConstants.MORNING_START_TIME;
		Calendar noonStartTime = ConferenceConstants.NOON_START_TIME;
		boolean noRemainingTime = false;
		List<Event> scheduledEventList = new ArrayList<Event>();

		while (totalEventList.size() > 0) {

			for (int i = 0; i < totalEventList.size(); i++) {

				Event event = totalEventList.get(i);

				if (event.getDuration() <= morningRemainingTime) {
					morningRemainingTime = addEventToSchedule(totalEventList, event, scheduledEventList, morningStartTime,
							morningRemainingTime);
					morningStartTime = CalendarUtil.getNextStartTime(morningStartTime, event.getDuration());
				}

				else if (event.getDuration() <= noonRemaningTime) {
					noonRemaningTime = addEventToSchedule(totalEventList, event, scheduledEventList, noonStartTime,
							noonRemaningTime);
					noonStartTime = CalendarUtil.getNextStartTime(noonStartTime, event.getDuration());
				}

				else {
					noRemainingTime = true;
				}

			}

			if (totalEventList.size() == 0 || noRemainingTime) {
				addLunchEvent(scheduledEventList);
				addNWEvent(scheduledEventList, noonStartTime);
				Collections.sort(scheduledEventList, new EventStartComparator());
				Schedule s = new Schedule(scheduleList.size() + 1, scheduledEventList);
				scheduleList.add(s);
				prepareSchedule(totalEventList);
			}

		}

	}

	/**
	 * @param totalEventList
	 * @param event
	 * @param schEvent
	 * @param startTime
	 * @param remainingTime
	 * 
	 * This method adds event to scheduledEvent List and removes it from the total List
	 */
	private int addEventToSchedule(List<Event> totalEventList, Event event, List<Event> scheduledEventList, Calendar startTime,
			int remainingTime) {
		event.setStartTime(startTime);
		remainingTime = remainingTime - event.getDuration();
		scheduledEventList.add(event);
		totalEventList.remove(event);

		return remainingTime;
	}

	/**
	 * @param schEvent
	 * This method adds Lunch Event to scheduledEvent List
	 */
	private void addLunchEvent(List<Event> schEvent) {
		schEvent.add(ConferenceConstants.LUNCH_EVENT);
	}

	/**
	 * @param schEvent
	 * @param noonStartTime
	 * This method adds Networking Event to scheduledEvent List.
	 * Check this event cant start earlier than 4. Since the noon avail time was 240, any event cant start no later than 5.
	 */
	private void addNWEvent(List<Event> schEvent, Calendar noonStartTime) {

		if (noonStartTime.compareTo(CalendarUtil.getEventTimeAs(16, 00)) < 0) {
			noonStartTime = CalendarUtil.getEventTimeAs(16, 00);
		}

		Event nw = new Event(ConferenceConstants.NW_EVENT, 0, noonStartTime);
		schEvent.add(nw);
	}

}
