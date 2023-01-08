package com.company.crm.mock;

import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.company.crm.client.PersonCallbackApi;
import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;
import com.google.gwt.i18n.client.DateTimeFormat;

public class PersonClientMock implements PersonCallbackApi {

	private static Logger logger = Logger.getLogger(PersonClientMock.class.getName());

	@Override
	public void getPersons(Consumer<List<PersonDto>> callback) {
		logger.info("Mock: getPersons");
		callback.accept(getPersonDtos());
	}

	@Override
	public void getPersonsWithError(Consumer<List<ErrorDto>> callback) throws PersonException {
		logger.info("Mock: getPersonsWithError");
		callback.accept(getPersonErrorDtos());
	}

	@Override
	public void createPerson(PersonDto personDto, Consumer<PersonDto> callback) {
		logger.info("Mock: createPerson");
		callback.accept(personDto);
	}

	List<ErrorDto> getPersonErrorDtos() {
		List<ErrorDto> personErrorDtos = new ArrayList<>();

		ErrorDto errorDto = new ErrorDto();
		errorDto.setDetail("Something wrong with the person.");
		errorDto.setMessage("We cannot do anything.");

		personErrorDtos.add(errorDto);

		return personErrorDtos;
	}

	List<PersonDto> getPersonDtos() {
		List<PersonDto> personDtos = new ArrayList<>();

		String pattern = CONSTANTS.birthdateStringFormat();
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(pattern);

		PersonDto person1 = new PersonDto();
		person1.setName("Dr. Jawa");
		person1.setDate(new Date());
		person1.setFormattedDate(dateFormat.format(person1.getDate()));

		PersonDto person2 = new PersonDto();
		person2.setName("Scrumtuous");
		person2.setDate(new Date());
		person2.setFormattedDate(dateFormat.format(person2.getDate()));

		personDtos.add(person1);
		personDtos.add(person2);

		return personDtos;
	}

}
