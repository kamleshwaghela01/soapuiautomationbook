package com.soapui.automation.framework.services.exception.meta;

import com.soapui.automation.framework.services.exception.DuplicateReferenceException;
import com.soapui.automation.framework.services.exception.ReferenceNotFoundException;
import com.soapui.automation.framework.services.exception.ValidationException;
import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


public class ReferenceResponseExceptionMapper implements ResponseExceptionMapper<ReferenceBaseException> {

	@Override
	public ReferenceBaseException fromResponse(Response r) {
		
		if (r.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			return new ReferenceNotFoundException();
		} 

		if (r.getStatus() == Status.CONFLICT.getStatusCode()) {
			return new DuplicateReferenceException();
		}

		if (r.getStatus() == Status.BAD_REQUEST.getStatusCode()) {

			JAXBContext context;
			try {
				context = JAXBContext.newInstance(ExceptionData.class);
				Unmarshaller um = context.createUnmarshaller();
				ExceptionData ed = (ExceptionData) um.unmarshal((InputStream)r.getEntity());
				return new ValidationException(ed.getData());
			} catch (JAXBException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
		}
		
		return null;
	}
	
}
