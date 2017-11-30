package com.soapui.automation.framework.services.webservice;

import com.soapui.automation.framework.services.data.ReferenceData;
import com.soapui.automation.framework.services.exception.meta.ReferenceResponseExceptionMapper;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class ReferenceClient implements ReferenceService {

	private ReferenceService referenceService;

	public static enum CLIENT_TYPE {REST("REST"), SOAP("SOAP");

		private String value;
		private CLIENT_TYPE(String value) {
			this.value = value;
		}

		public static CLIENT_TYPE fromString(String value) {

			if (REST.value.equalsIgnoreCase(value)) {
				return REST;
			}

			if (SOAP.value.equalsIgnoreCase(value)) {
				return SOAP;
			}

			return null;
		}
	};

	public ReferenceClient(String applicationURI, CLIENT_TYPE clientType) {
		
		if (clientType == CLIENT_TYPE.REST) {
			List<Object> providers = new LinkedList<Object>();
			providers.add(new ReferenceResponseExceptionMapper());
			referenceService = JAXRSClientFactory.create(applicationURI + "/cxf/rest/referencemanagement/", ReferenceService.class, providers, true);
			ClientConfiguration cfgProxy = WebClient.getConfig(referenceService);
			cfgProxy.getHttpConduit().getAuthorization().setPassword("restuser");
			cfgProxy.getHttpConduit().getAuthorization().setUserName("restuser");
		} else {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(ReferenceService.class);
			factory.setAddress(applicationURI + "/cxf/soap/referencemanagement/");
			factory.setUsername("restuser");
			factory.setPassword("restuser");
			referenceService = (ReferenceService) factory.create();
		}
		
		
		
	}
	
	@Override
	public ReferenceData createLocation(ReferenceData locationData)	 {
		return referenceService.createLocation(locationData);
	}

	@Override
	public ReferenceData updateorCreateLocation(ReferenceData locationData) {
		return referenceService.updateorCreateLocation(locationData);
	}

	@Override
	public ReferenceData readLocation(String location) {
		return referenceService.readLocation(location);
	}

	@Override
	public Collection<ReferenceData> readAllLocations() {
		return referenceService.readAllLocations();
	}

	@Override
	public void deleteLocation(String location)
			 {
		
		referenceService.deleteLocation(location);
		
	}

	@Override
	public void deleteAllLocation() {
		referenceService.deleteAllLocation();
	}

}