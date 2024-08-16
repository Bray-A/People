package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private List<Person> people = new ArrayList<>();

    public void addPerson(String name, int age) {
        people.add(new Person(name, age));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Map<String, Integer> getNameToAgeMap() {
        return people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
    }

    public List<String> getNamesOfAdults() {
        return people.stream()
                .filter(person -> person.getAge() >= 18)
                .map(Person::getName)
                .collect(Collectors.toList());
    }
}
