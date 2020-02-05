package com.thoughtworks.conference.test;

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

	// running below test with Sort line commented in ScheduleProcessor will generate size 2, means needs two days to complete events
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
			sp.prepareSchedule(InputProcessor.getInputEvents("src/test/resources/EventInput.txt"));
			Assert.assertEquals(2, sp.getScheduleList().size());

			for (Schedule sch : sp.getScheduleList()) {
				Assert.assertTrue("NetworkEvent Event not found", sch.getEventList().contains(new Event(ConferenceConstants.NW_EVENT, 0)));
				
				Event nwEvent = sch.getEventList().get(sch.getEventList().indexOf(new Event(ConferenceConstants.NW_EVENT, 0)));
				
				Assert.assertTrue("NetworkEvent starts before 4 PM", nwEvent.getStartTime().after(CalendarUtil.getEventTimeAs(16, 0)));
				Assert.assertTrue("NetworkEvent starts after 4 PM", nwEvent.getStartTime().before(CalendarUtil.getEventTimeAs(17, 0)));
				
			}

		} catch (ConferenceException e) {
			Assert.fail(e.getMessage());
		}
	}
}
