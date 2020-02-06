package com.thoughtworks.conference.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.conference.bo.Event;
import com.thoughtworks.conference.bo.Schedule;
import com.thoughtworks.conference.constants.ConferenceConstants;
import com.thoughtworks.conference.exception.ConferenceException;
import com.thoughtworks.conference.processor.InputProcessor;
import com.thoughtworks.conference.processor.ScheduleProcessor;
import com.thoughtworks.conference.util.CalendarUtil;

public class ScheduleProcessorTest {

	@Test
	public void testSchedule() {
		ScheduleProcessor sp = new ScheduleProcessor();
		try {
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/EventInput.txt"));
			Assert.assertEquals(2, sp.getScheduleList().size());

			for (Schedule sch : sp.getScheduleList()) {
				sch.printSchedule();
			}

		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}

	// running below test with EventDurationComparator sorting line commented in ScheduleProcessor will generate size 2, 
	//means needs two days to complete the same list of events
	@Test
	public void testNeedOfSort() {
		ScheduleProcessor sp = new ScheduleProcessor();
		try {
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/NeedOfSortInput.txt"));
			Assert.assertEquals(1, sp.getScheduleList().size());
		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testScheduleForNullInput() {
		ScheduleProcessor sp = new ScheduleProcessor();
		
			sp.prepareSchedule(null);
		
		}

	@Test
	public void testScheduleContainsLunch() {
		ScheduleProcessor sp = new ScheduleProcessor();
		try {
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/EventInput.txt"));
			Assert.assertEquals(2, sp.getScheduleList().size());

			for (Schedule sch : sp.getScheduleList()) {
				Assert.assertTrue("Lunch Event not found", sch.getEventList().contains(ConferenceConstants.LUNCH_EVENT));
			}

		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testScheduleForNWEvent() {
		ScheduleProcessor sp = new ScheduleProcessor();
		try {
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/EventInput2.txt"));
			Assert.assertEquals(3, sp.getScheduleList().size());

			for (Schedule sch : sp.getScheduleList()) {
				sch.printSchedule();
				Assert.assertTrue("NetworkEvent Event not found", sch.getEventList().contains(new Event(ConferenceConstants.NW_EVENT, 0)));
				
				Event nwEvent = sch.getEventList().get(sch.getEventList().indexOf(new Event(ConferenceConstants.NW_EVENT, 0)));
				
				Assert.assertTrue("NetworkEvent starts before 4 PM", nwEvent.getStartTime().after(CalendarUtil.getEventTimeAs(15, 59)));
				Assert.assertTrue("NetworkEvent starts after 4 PM", nwEvent.getStartTime().before(CalendarUtil.getEventTimeAs(17, 0)));
				
				
			}

		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testAllInputEventsGotScheduled() {
		ScheduleProcessor sp = new ScheduleProcessor();
		try {
			List<Event> inputEvents = InputProcessor.getInputEvents("src/test/resources/EventInput.txt");
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/EventInput.txt"));
			Assert.assertEquals(2, sp.getScheduleList().size());

			for(Event inputEvent : inputEvents){
				boolean isEventPresent = false;
				
				for (Schedule sch : sp.getScheduleList()) {
					isEventPresent = sch.getEventList().contains(inputEvent);
					if(isEventPresent) break;
				}
				
				Assert.assertTrue("Event not present. Please check " + inputEvent.toString(), isEventPresent);
				
			}

		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}
}
