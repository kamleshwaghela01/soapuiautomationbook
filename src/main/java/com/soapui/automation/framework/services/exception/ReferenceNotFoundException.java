package com.soapui.automation.framework.services.exception;

import com.soapui.automation.framework.services.exception.meta.ReferenceBaseException;

import javax.ws.rs.core.Response.Status;


public class ReferenceNotFoundException extends ReferenceBaseException {

	private static final long serialVersionUID = 1154886234595592271L;
	
	public ReferenceNotFoundException() {
		
		super(Status.NOT_FOUND, "Location not found");
		
	}
	

}
