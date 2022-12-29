package com.company.crm.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	private PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Transactional(readOnly = true)
	public List<Person> getPersons() {
		logger.info("Service: getPersons");

		List<Person> persons = personRepository.findAll();

		return persons;
	}
}
