package com.company.crm.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.dominokit.rest.shared.request.ServerRequest;
import org.dominokit.rest.shared.request.service.annotations.Request;

import com.company.crm.client.PersonClientFactory;
import com.company.crm.shared.ErrorDto;
import com.company.crm.shared.PersonDto;

public class PersonClientMockFactory extends PersonClientFactory {

  private static Logger logger = Logger.getLogger(PersonClientMockFactory.class.getName());

  List<PersonDto> getPersonDtos() {
    List<PersonDto> personDtos = new ArrayList<>();

    PersonDto person = new PersonDto();
    person.setName("Lofi");
    person.setDate(null);

    personDtos.add(person);
    return personDtos;
  }

  public ServerRequest<Void, List<PersonDto>> getPersons() {
    PersonClient_getPersons instance = new PersonClient_getPersons();
    return instance;
  }

  public ServerRequest<Void, List<ErrorDto>> getPersonsWithError() {
    PersonClient_getPersonsWithError instance = new PersonClient_getPersonsWithError();
    return instance;
  }

  @Request
  public class PersonClient_getPersons extends ServerRequest<Void, List<PersonDto>> {
    PersonClient_getPersons() {
      logger.info("PersonClient_getPersons");
    }
  }

  public class PersonClient_getPersonsWithError extends ServerRequest<Void, List<ErrorDto>> {
    PersonClient_getPersonsWithError() {
      logger.info("PersonClient_getPersonsWithError");
    }
  }

}
