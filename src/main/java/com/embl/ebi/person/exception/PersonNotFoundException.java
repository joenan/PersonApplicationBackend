package com.embl.ebi.person.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Person id not found : " + id);
    }
}
