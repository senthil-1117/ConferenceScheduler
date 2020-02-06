package com.thoughtworks.conference.processor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.conference.bo.Event;
import com.thoughtworks.conference.constants.ConferenceConstants;
import com.thoughtworks.conference.exception.ConferenceErrorCode;
import com.thoughtworks.conference.exception.ConferenceException;

// This class is to process the input file and generate input as List of Events.
public class InputProcessor {
	

	public static List<Event> getInputEvents(String fileName) throws ConferenceException{
		
		System.out.println("Received input file as : " + fileName);
		
		List<String> inputLines = readFileInList(fileName);
		List<Event> inputEvents = new ArrayList<Event>();
		int eventTime = 0;
		
		for(String line : inputLines){
			if(line.length() > 0){
				String eventName = line.substring(0,line.lastIndexOf(" ")).trim();
				String eventDuration = line.substring(line.lastIndexOf(" ")).trim();
			
				if(eventDuration.toUpperCase().contains(ConferenceConstants.LIGHTNING)){
					eventTime = 5;
				}
				else{
					try{
					eventTime = Integer.parseInt(eventDuration.replaceAll("\\D+", ""));
					}
					catch(NumberFormatException e){
						System.out.println("Exception while converting String to int for duration string : " + eventDuration);
						throw new ConferenceException(ConferenceErrorCode.CONF_ERR_02);
					}
				}
				
				if(eventTime > 240){
					System.out.println("Event duration > 240 : " + eventDuration);
					throw new ConferenceException(ConferenceErrorCode.CONF_ERR_03);
				}
				
				inputEvents.add(new Event(eventName, eventTime));
				
			}
		}
		
		System.out.println("Taking input events as : " + inputEvents);
		return inputEvents;
	}
	
	
	public static List<String> readFileInList(String fileName) throws ConferenceException{
		
	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
	    } 
	  
	    catch (IOException e) 
	    { 
	    	// will use logger instead of sysout.
	    	System.out.println("Error while reading file. Please check." + e.getMessage());
	    	throw new ConferenceException(ConferenceErrorCode.CONF_ERR_01);
	    } 
	    return lines; 
	  } 
	
}
