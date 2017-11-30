package com.soapui.automation.framework.services.exception;

import com.soapui.automation.framework.services.exception.meta.ReferenceBaseException;

import javax.ws.rs.core.Response.Status;


/**
 * Indicates if a location is already created
 * @author pszanto
 */
public class DuplicateReferenceException extends ReferenceBaseException {

	private static final long serialVersionUID = -8212991366777389573L;

	public DuplicateReferenceException() {

		super(Status.CONFLICT, "Location is already stored");
		
	}
	
}
