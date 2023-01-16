package com.tanirbergen.FirstProjectRestApi.Service;

import com.tanirbergen.FirstProjectRestApi.Model.Person;
import com.tanirbergen.FirstProjectRestApi.Repository.PersonRepository;

import com.tanirbergen.FirstProjectRestApi.Util.PersonResponseNotFoundId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional.orElseThrow(PersonResponseNotFoundId::new);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        personRepository.save(person);
    }


    private void enrichPerson(Person person) {
        person.setCreatedAd(LocalDateTime.now());
        person.setUpdatedAd(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }
}
