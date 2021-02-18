package net.ivanevla.peopleapp.services;

import net.ivanevla.peopleapp.domain.Gender;
import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(String personalId, String dateString, String firstName, String lastName, String genderString) {
        try {
            Date dateOfBirth = DateFormat.getDateInstance().parse(dateString);
            Gender gender = Gender.valueOf(genderString.toUpperCase());

            Person person = new Person(personalId, dateOfBirth, firstName, lastName, gender);
            Person personFromDb = personRepository.save(person);
            logger.trace(String.format("Person added %s", personFromDb.getPersonalId()), personFromDb.toString());
            return personFromDb;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> getPeopleList(String personalId, Date dateOfBirth) {
        if (personalId == null && dateOfBirth == null) {
            logger.trace("find all people");
            return (List<Person>) personRepository.findAll();
        }

        logger.trace(String.format("find people with personalId=%s, dateOfBirth=%s", personalId, new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth)));
        return (List<Person>) personRepository.findAllByDateOfBirthOrPersonalId(personalId, dateOfBirth);
    }
}
