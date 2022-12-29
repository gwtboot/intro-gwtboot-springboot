package com.company.crm.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceIT {

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    PersonService personService;

    void prepareData() {
        Person person1 = new Person();
        person1.setName("Lofi");
        person1.setNickname("Dr. Jawa");
        personRepository.save(person1);
    }

    @Test
    void get_all_persons_result_one_person() {
        prepareData();

        List<Person> persons = personService.getPersons();

        assertEquals(1, persons.size());
    }
    
}
