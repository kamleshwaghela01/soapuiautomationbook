package com.soapui.automation.framework.services.webservice;

import com.soapui.automation.framework.services.data.ReferenceData;
import com.soapui.automation.framework.services.exception.DuplicateReferenceException;
import com.soapui.automation.framework.services.exception.ReferenceNotFoundException;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/location/")
@WebService
public interface ReferenceService {

	@WebMethod
	@GET
	@Path("{location}")
	@Descriptions({
		@Description(value = "returns a location data ", target = DocTarget.METHOD),
		@Description(value = "the location data", target = DocTarget.RETURN)
	})
    public ReferenceData readLocation(@Description(value = "the string representation of the location") @PathParam("location") @NotNull @Size(max = 10, min = 5) String location) throws ReferenceNotFoundException;

	@WebMethod
	@GET
	@Path("*")
	@Descriptions({
		@Description(value = "returns all locations", target = DocTarget.METHOD),
		@Description(value = "the location data", target = DocTarget.RETURN)
	})
    public Collection<ReferenceData> readAllLocations();

	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new location data", target = DocTarget.METHOD),
		@Description(value = "the newly created location data", target = DocTarget.RETURN)
	})
	public ReferenceData createLocation(@Valid ReferenceData locationData) throws DuplicateReferenceException;

	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new location data", target = DocTarget.METHOD),
		@Description(value = "the newly created location data", target = DocTarget.RETURN)
	})
	public ReferenceData updateorCreateLocation(@Valid ReferenceData locationData);

	@WebMethod
	@DELETE
	@Path("{location}")
	@Descriptions({
		@Description(value = "deletes a location data", target = DocTarget.METHOD),
		@Description(value = "the location data", target = DocTarget.RETURN)
	})
    public void deleteLocation(@Description(value = "the string representation of the location") @PathParam("location") @NotNull @Size(max = 10, min = 5) String location) throws ReferenceNotFoundException;

	@WebMethod
	@DELETE
	@Path("*")
	@Descriptions({
		@Description(value = "deletes All location data", target = DocTarget.METHOD)
	})
    public void deleteAllLocation();
	
}
