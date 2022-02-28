package com.bsr.repository;

import com.bsr.model.Advert;
import com.bsr.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PersonRepository {

    private static List<Person> personList = new ArrayList<>();
    private static long id = 1;

    @Autowired
    private AdvertRepository advertRepository;

    @PostConstruct
    private void loadPersonData() {
        personList.add(createPerson("henry"," cavill", "111111",
                advertRepository.fetchAllAdverts()));
        personList.add(createPerson("leo"," dicaprio", "333333",
                List.of(advertRepository.findAdvertByAdvertId(38164782))));
    }

    public static List<Person> getAllPerson() {
        return personList;
    }

    private static Person createPerson(String fName, String lName, String phone, List<Advert> adverts) {
        Person person = new Person();
        person.setId(id++);
        person.setFirstName(fName);
        person.setLastName(lName);
        person.setMobileNo(phone);
        person.setFavouriteAdverts(adverts);

        return person;
    }

    public static Person savePerson(Person person) {
        personList.add(person);
        log.info("User saved successfully.");
        return person;
    }

    public Person findByPersonId(long id) {
        return  personList.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseGet(Person::new);
    }

    public Person showFavouriteAdverts(long id) {
         return personList.stream()
                .filter(person -> person.getId() == id)
                .findFirst().orElse(null);
    }

}
