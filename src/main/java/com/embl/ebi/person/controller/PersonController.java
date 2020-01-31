package com.embl.ebi.person.controller;

import com.embl.ebi.person.exception.PersonNotFoundException;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonService service;

    @ApiOperation(value = "Create Person")
    @PostMapping("/person")
    public ResponseEntity<?> savePerson(@RequestBody @Valid Person request) {

        if(request !=null) {
            Person savedObject = service.getPersonRepository().save(request);
            return ResponseEntity.ok(savedObject);
        }

        return ResponseEntity.badRequest().body("Request is empty");
    }

    @ApiOperation(value = "Get List of Persons")
    @GetMapping("/person")
    public ResponseEntity<?> findAllPersons() {
        List<Person> list = service.getPersonRepository().findAll();
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Get Person by Id")
    @GetMapping("/person/{id}")
    public ResponseEntity<?> findPersonsById(@PathVariable long id) {
        return service.getPersonRepository().findById(id).map(record -> ResponseEntity.ok().body(record))
            .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @ApiOperation(value = "Delete Person by Id")
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePersonCode(@PathVariable("id") long id) {
        return service.getPersonRepository().findById(id)
                .map(record -> {
                    service.getPersonRepository().deleteById(id);
                    return ResponseEntity.ok().build();
                }) .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @ApiOperation(value = "Edit Person by ID")
    @PutMapping("/person/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person data) {
        return service.getPersonRepository().findById(id)
                .map(record -> {
                    record.setAge(data.getAge());
                    record.setFirst_name(data.getFirst_name());
                    record.setHobby(data.getHobby());
                    record.setLast_name(data.getLast_name());
                    Person updated = service.getPersonRepository().save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
