package com.company.crm.mock;

import static com.company.crm.client.HomeClientBundle.CONSTANTS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonApi;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;
import com.google.gwt.i18n.client.DateTimeFormat;

public class PersonClientMock implements PersonApi {

	private static Logger logger = Logger.getLogger(PersonClientMock.class.getName());

	@Override
	public List<PersonDto> getPersons() {
		logger.info("Mock: getPersons");
		return getPersonDtos();
	}

	@Override
	public List<ErrorDto> getPersonsWithError() throws PersonException {
		logger.info("Mock: getPersonsWithError");
		return getPersonErrorDtos();
	}

	@Override
	public PersonDto createPerson(PersonDto personDto) {
		// TODO Auto-generated method stub
		return null;
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
