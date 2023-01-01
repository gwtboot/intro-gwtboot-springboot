package com.company.crm.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonApi;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonEndpoint;
import com.company.crm.shared.PersonException;

@RequestFactory
public interface PersonClient extends PersonApi {

	@Override
	@GET
	@Path(PersonEndpoint.PERSON_LIST)
	List<PersonDto> getPersons();

	@Override
	@GET
	@Path(PersonEndpoint.PERSON_WITH_ERROR_LIST)
	List<ErrorDto> getPersonsWithError() throws PersonException;

}