package com.embl.ebi.person.controller;


import com.embl.ebi.person.PersonApplication;
import com.embl.ebi.person.model.Person;
import com.embl.ebi.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LoadingController implements CommandLineRunner{

     @Autowired
    PersonService personService;

    private static Logger LOG = LoggerFactory.getLogger(PersonApplication.class);

    public void executeScripts() {

        LOG.info("Loading Test data into Person");

        Person p = new Person();
        p.setLast_name("Johnson");
        p.setFirst_name("Saleh");
        p.setAge("34");
        personService.save(p);
        LOG.info("Created First Person");

        Person p2 = new Person();
        p2.setLast_name("Salim");
        p2.setFirst_name("Mohammed");
        p2.setAge("50");
        personService.save(p2);
        LOG.info("Created Second Person");



        LOG.info("User Roles Created Successfully");


    }

    @Override
    public void run(String... args) throws Exception {
        executeScripts();
    }
}
