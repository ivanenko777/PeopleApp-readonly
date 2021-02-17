package net.ivanevla.peopleapp.services;

import net.ivanevla.peopleapp.domain.Gender;
import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(String personalId, String dateString, String firstName, String lastName, String genderString) {
        try {
            Date dateOfBirth = DateFormat.getDateInstance().parse(dateString);
            Gender gender = Gender.valueOf(genderString.toUpperCase());

            Person person = new Person(personalId, dateOfBirth, firstName, lastName, gender);
            return personRepository.save(person);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Person> getPeopleList(String personalId, Date dateOfBirth) {
        return personRepository.findAllByDateOfBirthOrPersonalId(personalId, dateOfBirth);
    }
}
