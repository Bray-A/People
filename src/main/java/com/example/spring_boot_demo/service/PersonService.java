package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.model.Person;
import com.example.spring_boot_demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void addPerson(String name, int age) {
        personRepository.save(new Person(name, age)); // Saves to the database
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll(); // Retrieves all people from the database
    }

    public Map<String, Integer> getNameToAgeMap() {
        return personRepository.findAll().stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
    }

    public List<String> getNamesOfAdults() {
        return personRepository.findAll().stream()
                .filter(person -> person.getAge() >= 18)
                .map(Person::getName)
                .collect(Collectors.toList());
    }
}
