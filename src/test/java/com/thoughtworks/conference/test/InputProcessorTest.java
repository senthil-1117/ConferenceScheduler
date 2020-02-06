package com.thoughtworks.conference.test;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.conference.exception.ConferenceErrorCode;
import com.thoughtworks.conference.exception.ConferenceException;
import com.thoughtworks.conference.processor.InputProcessor;
import com.thoughtworks.conference.processor.ScheduleProcessor;

/**
 * @author This is test class to test InputProcessor for various possible inputs.
 *
 */
public class InputProcessorTest {

	@Test
	public void testScheduleForInvalidInputFile() {
		try {
			InputProcessor.getInputEvents("src/test/resources/EventInput12345.txt");

		} catch (ConferenceException e) {
			Assert.assertTrue("ConferenceException for invalid file not found",
					e.getMessage().equals(ConferenceErrorCode.CONF_ERR_01.getErrorDesc()));
		} catch (Exception e) {
			Assert.fail("Conference Exception not thrown");
		}
	}

	@Test
	public void testScheduleForInvalidTime() {
		try {
			InputProcessor.getInputEvents("src/test/resources/EventInputWithInvalidTime.txt");

		} catch (ConferenceException e) {
			Assert.assertTrue("ConferenceException for invalid time not found",
					e.getMessage().equals(ConferenceErrorCode.CONF_ERR_02.getErrorDesc()));
		} catch (Exception e) {
			Assert.fail("Conference Exception not thrown");
		}
	}

	@Test
	public void testUnfittableEvent() {
		try {
			InputProcessor.getInputEvents("src/test/resources/UnfittableEvent.txt");
		} catch (ConferenceException e) {
			Assert.assertTrue("ConferenceException for invalid time not found",
					e.getMessage().equals(ConferenceErrorCode.CONF_ERR_03.getErrorDesc()));
		} catch (Exception e) {
			Assert.fail("Conference Exception not thrown");
		}
	}
}
