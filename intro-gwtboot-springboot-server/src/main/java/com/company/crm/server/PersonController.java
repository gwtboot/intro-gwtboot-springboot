package com.company.crm.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonApi;
import com.company.crm.shared.PersonDto;
import com.company.crm.shared.PersonEndpoint;
import com.company.crm.shared.PersonException;
import com.company.crm.shared.PersonType;

@CrossOrigin
@RestController
public class PersonController implements PersonApi {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = PersonEndpoint.PERSON_LIST)
	public List<PersonDto> getPersons() {
		logger.info("Controller: getPersons");

		List<Person> persons = personService.getPersons();

		List<PersonDto> personsList = new ArrayList<>();

		for (Person person : persons) {
			PersonDto personNew = new PersonDto();
			personNew.setDate(new Date());
			personNew.setName(person.getName());
			personNew.setPersonType(PersonType.COOL);

			personsList.add(personNew);
		}

		logger.info("Amount of persons: " + personsList.size());

		return personsList;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = PersonEndpoint.PERSON_WITH_ERROR_LIST)
	public List<ErrorDto> getPersonsWithError() throws PersonException {
		logger.info("Controller: getPersonsWithError");
		try {
			throw new PersonException("Cannot access the file");
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", exc);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = PersonEndpoint.PERSON_LIST)
	public PersonDto createPerson(@RequestBody PersonDto personDto) {
		logger.info("Controller: createPerson");

		Person personNew = new Person();
		personNew.setName(personDto.getName());
		personNew.setDate(personDto.getDate());

		Person createPerson = personService.createPerson(personNew);

		logger.info("Created person: " + createPerson.toString());

		return personDto;
	}

}
