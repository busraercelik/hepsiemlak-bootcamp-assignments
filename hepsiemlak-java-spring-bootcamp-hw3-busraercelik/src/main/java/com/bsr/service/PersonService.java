package com.bsr.service;

import com.bsr.dto.PersonRequestDTO;
import com.bsr.dto.response.PersonResponseDTO;
import com.bsr.model.Advert;
import com.bsr.model.Person;
import com.bsr.repository.DbConnectionRepository;
import com.bsr.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersonService {

    @Autowired
    @Qualifier(value = "mongoConnectionRepository")
    private DbConnectionRepository dbConn;

    @Autowired
    private PersonRepository personRepository;

    public List<PersonResponseDTO> getAllUsers() {
        List<PersonResponseDTO> responseDTOList = new ArrayList<>();

        for (Person person : PersonRepository.getAllPerson()) {
            responseDTOList.add(convertToResponseDTO(person));
        }
        return responseDTOList;
    }

    private PersonResponseDTO convertToResponseDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setFirstName(person.getFirstName());
        personResponseDTO.setLastName(person.getLastName());
        personResponseDTO.setEmail(person.getEmail());
        personResponseDTO.setMobileNo(person.getMobileNo());
        personResponseDTO.setFavouriteAdverts(person.getFavouriteAdverts());

        return personResponseDTO;
    }

    public PersonResponseDTO getPersonById(long id) {
        return convertToResponseDTO(personRepository.findByPersonId(id));
    }

    public PersonResponseDTO savePerson(PersonRequestDTO personRequestDTO) {
        Person person = PersonRepository.savePerson(convertToPerson(personRequestDTO));
        return convertToResponseDTO(person);
    }

    private Person convertToPerson(PersonRequestDTO personRequestDTO) {
        Person person = new Person();
        person.setFirstName(personRequestDTO.getFirstName());
        person.setLastName(personRequestDTO.getLastName());
        person.setEmail(personRequestDTO.getEmail());
        person.setMobileNo(personRequestDTO.getMobileNo());

        return person;
    }

    public PersonResponseDTO showFavouriteAdverts(long id) {
        return convertToResponseDTO(personRepository.showFavouriteAdverts(id));
    }

}
