package net.ivanevla.peopleapp.repositories;


import net.ivanevla.peopleapp.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth OR p.personalId = :personalId")
    List<Person> findAllByDateOfBirthOrPersonalId(
            @Param("personalId") String personalId,
            @Param("dateOfBirth") Date dateOfBirth
    );
}
