package net.ivanevla.peopleapp.services;

import net.ivanevla.peopleapp.domain.Gender;
import net.ivanevla.peopleapp.domain.Person;
import net.ivanevla.peopleapp.exception.InvalidDateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    public void getPersonListAllPeopleTest() {
        List<Person> personList = personService.getPersonList(null, null);

        assertEquals(12, personList.size());
    }

    @Test
    public void getPersonListByDateOfBirthThrowInvalidDateExceptionTest() {
        assertThrows(
                InvalidDateException.class,
                () -> personService.getPersonList(null, "1990-25-30")
        );
    }

    @Test
    public void getPersonListByPersonalIdTest() {
        List<Person> personList = personService.getPersonList("39011121234", null);
        assertEquals(1, personList.size());

        Person person = personList.get(0);
        assertEquals("39011121234", person.getPersonalId());
        assertEquals("Nicolas", person.getFirstName());
        assertEquals("Steggals", person.getLastName());
        assertEquals(Gender.MALE, person.getGender());
    }

    @Test
    public void GetPersonListByDateOfBirth() {
        List<Person> personList = personService.getPersonList(null, "1990-06-05");
        assertEquals(2, personList.size());

        Person person1 = personList.get(0);
        assertEquals("39006051234", person1.getPersonalId());
        assertEquals("Filberte", person1.getFirstName());
        assertEquals("Lippingwell", person1.getLastName());
        assertEquals(Gender.MALE, person1.getGender());

        Person person2 = personList.get(1);
        assertEquals("49006051234", person2.getPersonalId());
        assertEquals("Joela", person2.getFirstName());
        assertEquals("Lippingwell", person2.getLastName());
        assertEquals(Gender.FEMALE, person2.getGender());
    }
}