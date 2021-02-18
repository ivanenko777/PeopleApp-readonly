package net.ivanevla.peopleapp.controllers;

import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> getAllPeople(
            @RequestParam(required = false) String personalId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth
    ) {
        logger.trace("/api/person/list - accessed");
        List<Person> people = personService.getPeopleList(personalId, dateOfBirth);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
}
