package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.model.Person;
import com.example.spring_boot_demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPerson() {

        String name = "Wane";
        int age = 30;
        Person person = new Person(name, age);

        personService.addPerson(name, age);

        verify(personRepository).save(person);
    }

    @Test
    public void testGetAllPeople() {

        List<Person> people = Arrays.asList(
                new Person("Wane", 30),
                new Person("Bob", 40)
        );
        when(personRepository.findAll()).thenReturn(people);

        List<Person> result = personService.getAllPeople();

        assertEquals(people, result);
    }

    @Test
    public void testGetNameToAgeMap() {

        List<Person> people = Arrays.asList(
                new Person("Wane", 30),
                new Person("Bob", 40)
        );
        when(personRepository.findAll()).thenReturn(people);

        Map<String, Integer> result = personService.getNameToAgeMap();

        Map<String, Integer> expected = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
        assertEquals(expected, result);
    }

    @Test
    public void testGetNamesOfAdults() {

        List<Person> people = Arrays.asList(
                new Person("Wane", 30),
                new Person("Bob", 15)
        );
        when(personRepository.findAll()).thenReturn(people);


        List<String> result = personService.getNamesOfAdults();

        List<String> expected = people.stream()
                .filter(person -> person.getAge() >= 18)
                .map(Person::getName)
                .collect(Collectors.toList());
        assertEquals(expected, result);
    }

}
