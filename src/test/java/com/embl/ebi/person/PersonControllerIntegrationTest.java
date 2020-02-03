package com.embl.ebi.person;

import com.embl.ebi.person.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;


@SpringBootTest(classes = PersonApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

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

        ResponseEntity<Person> postResponse = restTemplate.postForEntity("/person", p, Person.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetAllPersons() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("/person",
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetPersonById() {

        Person person = restTemplate.getForObject("/person/1", Person.class);
        System.out.println(person.getFirst_name());
        Assertions.assertNotNull(person);
    }

    @Test
    public void testUpdatePerson() {
        int id = 1;
        Person p = restTemplate.getForObject("/person/" + id, Person.class);
        p.setHobby(Arrays.asList("Movies", "Hiking"));
        p.setAge("90");
        p.setFirst_name("Barrack");
        p.setLast_name("Obama");

        restTemplate.put("/person/" + id, p);
        Person updatedPerson = restTemplate.getForObject("/employees/" + id, Person.class);
        Assertions.assertNotNull(updatedPerson);
    }

    @Test
    public void testDeletePerson() {
        int id = 1;
        Person person = restTemplate.getForObject("/person/" + id, Person.class);
        Assertions.assertNotNull(person);
        restTemplate.delete("/person/" + id);
        try {
            person = restTemplate.getForObject("/person/" + id, Person.class);
        } catch (final HttpClientErrorException e) {
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


}
