package net.ivanevla.peopleapp.controllers;

import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> getAllPeople(
            @RequestParam(value = "personalId", required = false) String personalId,
            @RequestParam(value = "dateOfBirth", required = false) String dateOfBirthString
    ) {
        logger.trace("/api/person/list - accessed");
        List<Person> people = personService.getPersonList(personalId, dateOfBirthString);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
}
