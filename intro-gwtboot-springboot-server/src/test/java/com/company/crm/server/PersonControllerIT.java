package com.company.crm.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.Commit;
import org.springframework.web.reactive.function.client.WebClient;

import com.company.crm.shared.PersonEndpoint;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerIT {

	private static final String HOST_TO_TEST = "http://localhost:";

	private static final String URL_TO_TEST = "/intro/";

	private static final String URI_TO_TEST = PersonEndpoint.PERSON_LIST;

	@LocalServerPort
	int port;

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	PersonRepository personRepository;

	void prepareData() {
		Person person1 = new Person();
		person1.setName("Lofi");
		person1.setNickname("Dr. Jawa");
		personRepository.save(person1);
	}

	void removeData() {
		personRepository.deleteAll();
	}

	@Test
	@Commit
	void webClientAufruf() {
		prepareData();

		WebClient webClient = webClientBuilder.baseUrl(HOST_TO_TEST + port + URL_TO_TEST).build();
		String body = webClient.get().uri(URI_TO_TEST).retrieve().bodyToMono(String.class).block();

		System.out.println(body);

		String expectation = "[{\"name\":\"Lofi - Dr. Jawa\",\"date\":\"2022-12-29@12:56:06.443+0000\",\"personType\":\"COOL\"}]";

		assertEquals(expectation.substring(0, 15), body.substring(0, 15));

		removeData();
	}

}
