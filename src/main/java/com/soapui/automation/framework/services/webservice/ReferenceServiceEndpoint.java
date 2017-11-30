package com.soapui.automation.framework.services.webservice;

import com.soapui.automation.framework.services.data.ReferenceData;
import com.soapui.automation.framework.services.exception.DuplicateReferenceException;
import com.soapui.automation.framework.services.exception.ReferenceNotFoundException;
import com.soapui.automation.framework.services.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("locationService")
public class ReferenceServiceEndpoint implements ReferenceService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, ReferenceData> locations = new HashMap<String, ReferenceData>();

	@Autowired
	private Validator validator;

	//security handled by URL mapping in the xml
	//@Secured("ROLE_RESTCLIENT")
	@Override
	public ReferenceData readLocation(String location) throws ReferenceNotFoundException {

		ReferenceData locationData = locations.get(location);

		if (locationData == null) {
			throw new ReferenceNotFoundException();
		}

		return locationData;

	}

	@Override
	public ReferenceData createLocation(ReferenceData locationData) throws DuplicateReferenceException {

		if (locations.get(locationData.getLocation()) != null) {
			throw new DuplicateReferenceException();
		}

		BeanPropertyBindingResult br = new BeanPropertyBindingResult(locationData, "locationData");
		validator.validate(locationData, br);
		if (br.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError e : br.getFieldErrors()) {
				logger.debug(e.getDefaultMessage());
				errors.put(e.getField(), e.getDefaultMessage());
			}

			throw new ValidationException(errors);
		}

		setNewID(locationData);
		storeLocation(locationData);

		return locationData;
	}

	@Override
	public ReferenceData updateorCreateLocation(ReferenceData locationData) {

		if (locations.get(locationData.getLocation()) == null) {
			setNewID(locationData);
		}

		storeLocation(locationData);

		return locationData;

	}

	private void setNewID(ReferenceData locationData) {
		//setting the ID
		String id = UUID.randomUUID().toString();
		locationData.setId(id);

	}

	private void storeLocation(ReferenceData locationData) {

		locations.put(locationData.getLocation(), locationData);

	}

	@Override
	public Collection<ReferenceData> readAllLocations() {
		return locations.values();
	}

	@Override
	public void deleteLocation(String location) throws ReferenceNotFoundException {
		ReferenceData locationData = locations.get(location);

		if (locationData == null) {
			throw new ReferenceNotFoundException();
		}

		locations.remove(location);

	}

	@Override
	public void deleteAllLocation() {

		locations.clear();

	}
    
}