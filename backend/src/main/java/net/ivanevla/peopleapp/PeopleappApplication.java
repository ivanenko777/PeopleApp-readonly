package net.ivanevla.peopleapp;

import net.ivanevla.peopleapp.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PeopleappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeopleappApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(PersonService personService) {
        return args -> {
            List<String[]> peopleStringList = Arrays.asList(
                    new String[]{"39011121234", "1990-11-12", "Nicolas", "Steggals", "MALE"},
                    new String[]{"37603311234", "1976-03-31", "Hall", "Thal", "MALE"},
                    new String[]{"39307161234", "1993-07-16", "Armand", "Pithcock", "MALE"},
                    new String[]{"36907161234", "1969-07-16", "Eddy", "Bilham", "MALE"},
                    new String[]{"37007201234", "1970-07-02", "Ryley", "Haresnaip", "MALE"},
                    new String[]{"39006051234", "1990-06-05", "Filberte", "Lippingwell", "MALE"},
                    new String[]{"47310171234", "1973-10-17", "Jorrie", "Kipling", "FEMALE"},
                    new String[]{"47008311234", "1970-08-31", "Nissa", "Normaville", "FEMALE"},
                    new String[]{"47310121234", "1973-10-12", "Lynn", "Wickenden", "FEMALE"},
                    new String[]{"48002081234", "1980-02-08", "Candra", "Bladesmith", "FEMALE"},
                    new String[]{"46607171234", "1966-07-17", "Melinda", "Welsby", "FEMALE"},
                    new String[]{"49006051234", "1990-06-05", "Joela", "Lippingwell", "FEMALE"}
            );

            peopleStringList.forEach(person -> {
                String personalId = person[0];
                String date = person[1];
                String firstName = person[2];
                String lastName = person[3];
                String male = person[4];
                personService.addPerson(personalId, date, firstName, lastName, male);
            });
        };
    }
}
