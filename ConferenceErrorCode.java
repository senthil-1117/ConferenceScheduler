package com.thoughtworks.conference.exception;

public enum ConferenceErrorCode {

	CONF_ERR_01("Invalid input file"),
	CONF_ERR_02("Invalid duration in input file"),
	CONF_ERR_03("Unfittable event duration");
	
	private String errorDesc;
	
	ConferenceErrorCode(String errorDesc){
		this.errorDesc = errorDesc;
	}
	
	public String getErrorDesc(){
		return errorDesc;
	}
	
	
}
