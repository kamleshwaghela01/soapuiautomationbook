package com.soapui.automation.framework.services.exception.meta;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Map;

public class ReferenceBaseException extends WebApplicationException  {

	private static final long serialVersionUID = 1154886234595592271L;

	private ExceptionData exceptionData;
	
	public ReferenceBaseException(Status status, String message, Map<String, String> data) {
		
		super(Response.status(status).entity(new ExceptionData(message, data)).build());
		
		setExceptionData((ExceptionData)getResponse().getEntity());
		
	}
	public ReferenceBaseException(Status status, String message) {
		this(status, message, null);
	}
	
	@Override
	public String getMessage() {
		return getExceptionData().getMessage();
	}
	
	public ExceptionData getExceptionData() {
		return exceptionData;
	}
	public void setExceptionData(ExceptionData exceptionData) {
		this.exceptionData = exceptionData;
	}
	

}
