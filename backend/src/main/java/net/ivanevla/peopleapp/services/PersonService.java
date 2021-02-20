package net.ivanevla.peopleapp.services;

import net.ivanevla.peopleapp.domain.Gender;
import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.exception.InvalidDateException;
import net.ivanevla.peopleapp.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(String personalId, String dateOfBirthString, String firstName, String lastName, String genderString) {
        Date dateOfBirth = parseDate(dateOfBirthString);
        Gender gender = Gender.valueOf(genderString.toUpperCase());

        Person person = new Person(personalId, dateOfBirth, firstName, lastName, gender);
        Person personFromDb = personRepository.save(person);
        logger.trace(String.format("Person added %s", personFromDb.getPersonalId()), personFromDb.toString());
        return personFromDb;
    }

    public List<Person> getPeopleList(String personalId, String dateOfBirthString) {
        if (isNullOrEmpty(personalId ) && isNullOrEmpty(dateOfBirthString)) {
            logger.trace("find all people");
            return (List<Person>) personRepository.findAll();
        }

        Date date = null;
        if (!isNullOrEmpty(dateOfBirthString)) {
            date = parseDate(dateOfBirthString);
        }

        logger.trace(String.format("find people with personalId=%s, dateOfBirth=%s", personalId, dateOfBirthString));
        return (List<Person>) personRepository.findAllByDateOfBirthOrPersonalId(personalId, date);
    }

    private Date parseDate(String dateOfBirthString) {
        if (dateOfBirthString != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            try {
                return simpleDateFormat.parse(dateOfBirthString);
            } catch (ParseException e) {
                throw new InvalidDateException("Invalid date");
            }
        }
        return null;
    }

    private boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }
}
