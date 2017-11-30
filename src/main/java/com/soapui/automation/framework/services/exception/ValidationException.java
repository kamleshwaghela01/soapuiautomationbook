package com.soapui.automation.framework.services.exception;



import com.soapui.automation.framework.services.exception.meta.ReferenceBaseException;

import javax.ws.rs.core.Response.Status;
import java.util.Map;


public class ValidationException extends ReferenceBaseException {

	private static final long serialVersionUID = -6353144184095941148L;

	public ValidationException(Map<String, String> data) {
		super(Status.BAD_REQUEST, "Validation failed", data);
	}

}
