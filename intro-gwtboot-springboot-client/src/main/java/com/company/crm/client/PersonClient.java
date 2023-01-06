package com.company.crm.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonApi;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;

public class PersonClient implements PersonApi {

	private static Logger logger = Logger.getLogger(PersonClient.class.getName());

	@Override
	public List<PersonDto> getPersons() {
		List<PersonDto> resultResponse = new ArrayList<>();

		PersonApiResourceFactory.INSTANCE.getPersons().onSuccess(response -> {
			resultResponse.forEach(person -> logger.info("Person: " + person.getName() + " - Date: " + person.getDate()
					+ " - Type: " + person.getPersonType()));
		}).onFailed(failedResponse -> {
			logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
		}).send();

		return resultResponse;
	}

	@Override
	public List<ErrorDto> getPersonsWithError() throws PersonException {
		List<ErrorDto> resultResponse = new ArrayList<>();

		PersonApiResourceFactory.INSTANCE.getPersonsWithError().onSuccess(response -> {
			response.forEach(e -> logger.info("Error Code: " + e.getErrorcode()));
		}).onFailed(failedResponse -> {
			logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
		}).send();

		return resultResponse;
	}

}
