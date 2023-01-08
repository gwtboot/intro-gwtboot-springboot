package com.company.crm.client;

import java.util.List;
import java.util.function.Consumer;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonException;

public interface PersonCallbackApi {

	void getPersons(Consumer<List<PersonDto>> callback);

	void getPersonsWithError(Consumer<List<ErrorDto>> callback) throws PersonException;

	void createPerson(PersonDto personDto, Consumer<PersonDto> callback);

}
