package com.company.crm.client;

import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;
import com.company.crm.shared.PersonType;
import com.google.gwt.i18n.client.DateTimeFormat;

public class PersonClient implements PersonCallbackApi {

	private static Logger logger = Logger.getLogger(PersonClient.class.getName());

	@Override
	public void getPersons(Consumer<List<PersonDto>> callback) {
		logger.info("getPersons");

		PersonApiResourceFactory.INSTANCE.getPersons().onSuccess(response -> {
			response.forEach(person -> {
				logger.info("Person: " + person.getName() + " - Date: " + person.getDate()
						+ " - Type: " + person.getPersonType());

				String pattern = CONSTANTS.birthdateStringFormat();
				DateTimeFormat dateFormat = DateTimeFormat.getFormat(pattern);
				person.setFormattedDate(dateFormat.format(person.getDate()));
			});

			callback.accept(response);
		}).onFailed(failedResponse -> {
			logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
		}).send();
	}

	@Override
	public void getPersonsWithError(Consumer<List<ErrorDto>> callback) throws PersonException {
		logger.info("getPersonsWithError");

		PersonApiResourceFactory.INSTANCE.getPersonsWithError().onSuccess(response -> {
			response.forEach(e -> logger.info("Error Code: " + e.getErrorcode()));
			callback.accept(response);
		}).onFailed(failedResponse -> {
			logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
		}).send();
	}

	@Override
	public void createPerson(PersonDto personDto, Consumer<PersonDto> callback) {
		logger.info("createPerson");

		personDto.setPersonType(PersonType.BORING);

		PersonApiResourceFactory.INSTANCE.createPerson(personDto).onSuccess(response -> {
			logger.info("Person created: " + response.toString());
			callback.accept(personDto);
		}).onFailed(failedResponse -> {
			logger.info("Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
		}).send();
	}

}
