package com.bsr.emlakburada.service;

import com.bsr.emlakburada.controller.AdvertController;
import com.bsr.emlakburada.dto.PersonRequestDTO;
import com.bsr.emlakburada.dto.response.AdvertResponseDTO;
import com.bsr.emlakburada.dto.response.PersonResponseDTO;
import com.bsr.emlakburada.model.Person;
import com.bsr.emlakburada.repository.DbConnectionRepository;
import com.bsr.emlakburada.repository.PersonRepository;
import com.bsr.emlakburada.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return personRepository.findAll()
                .stream()
                .map(Response::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonResponseDTO getPersonById(long id) {
        return Response.convertToResponseDTO(personRepository.findById(id).orElseThrow());
    }

    public void savePerson(PersonRequestDTO personRequestDTO) {
        personRepository.save(Response.convertToPerson(personRequestDTO));
    }

    public List<AdvertResponseDTO> showFavouriteAdverts(long id) {
        Optional<Person> byId = personRepository.findById(id);

        return byId.map(person -> person.getFavouriteAdverts()
                .stream()
                .map(Response::convertToAdvertResponse)
                .collect(Collectors.toList())).orElse(null);
    }

}
