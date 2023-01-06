package com.company.crm.shared;

import java.util.List;

public interface PersonApi {

	List<PersonDto> getPersons();

	List<ErrorDto> getPersonsWithError() throws PersonException;

	PersonDto createPerson(PersonDto personDto);

}
