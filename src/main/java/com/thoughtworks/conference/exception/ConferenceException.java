package com.thoughtworks.conference.exception;

public class ConferenceException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ConferenceException(ConferenceErrorCode errorCode){
		super(errorCode.getErrorDesc());
	}
	
	
	
}
