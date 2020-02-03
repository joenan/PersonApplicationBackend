package com.embl.ebi.person;

import com.embl.ebi.person.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;


@SpringBootTest(classes = PersonApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void contextLoads() {

    }

    @Test
    public void testPostPerson() {
        Person p = new Person();
        p.setLast_name("Philip");
        p.setFirst_name("Jones");
        p.setAge("34");
        p.setHobby(Arrays.asList("Reading", "Writing", "Football"));


        HttpEntity<Person> entity = new HttpEntity<>(p, headers);

        ResponseEntity<Person> response = restTemplate.exchange("/person", HttpMethod.POST, entity, Person.class);
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals("Philip", response.getBody().getLast_name());
        Assertions.assertEquals("Jones", response.getBody().getFirst_name());
        Assertions.assertEquals("34", response.getBody().getAge());
    }

    @Test
    public void testGetAllPersons() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("/person", HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    @Sql("/test.sql")
    public void testGetPersonById() {
        HttpEntity<Person> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Person> response = restTemplate.exchange("/person/1", HttpMethod.GET, entity, Person.class);
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals(1L, response.getBody().getId());

    }

    @Test
    public void testUpdatePerson() {
        HttpEntity<Person> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Person> request = restTemplate.exchange("/person/1", HttpMethod.GET, entity, Person.class);

        request.getBody().setFirst_name("Nandom");
        request.getBody().setLast_name("Gusen");


        //Sending to Post API for updating the retrieved entity
        HttpEntity<Person> updateEntity = new HttpEntity<>(request.getBody(), headers);

        ResponseEntity<Person> response = restTemplate.exchange("/person", HttpMethod.POST, updateEntity, Person.class);
        Assertions.assertNotNull(response.getBody().getId());
        Assertions.assertEquals("Gusen", response.getBody().getLast_name());
        Assertions.assertEquals("Nandom", response.getBody().getFirst_name());
        Assertions.assertEquals("34", response.getBody().getAge());

    }

    @Test
    public void testDeletePerson() {

        HttpEntity<Person> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Person> response = restTemplate.exchange("/person/1", HttpMethod.DELETE, entity, Person.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }


}
