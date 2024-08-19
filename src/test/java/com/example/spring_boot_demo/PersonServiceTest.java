package com.example.spring_boot_demo;

import com.example.spring_boot_demo.model.Person;
import com.example.spring_boot_demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonServiceTest {
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonService();
    }

    @Test
    public void testAddPerson() {
        personService.addPerson("John", 25);
        List<Person> people = personService.getAllPeople();
        assertEquals(1, people.size());
        assertEquals("John", people.get(0).getName());
        assertEquals(25, people.get(0).getAge());
    }

    @Test
    public void testGetAllPeople() {
        personService.addPerson("John", 25);
        personService.addPerson("Jane", 30);

        List<Person> people = personService.getAllPeople();
        assertEquals(2, people.size());
        assertEquals("John", people.get(0).getName());
        assertEquals("Jane", people.get(1).getName());
    }

    @Test
    public void testGetNameToAgeMap() {
        personService.addPerson("John", 25);
        personService.addPerson("Jane", 30);

        Map<String, Integer> nameToAgeMap = personService.getNameToAgeMap();
        assertEquals(2, nameToAgeMap.size());
        assertEquals(25, nameToAgeMap.get("John"));
        assertEquals(30, nameToAgeMap.get("Jane"));
    }

    @Test
    public void testGetNamesOfAdults() {
        personService.addPerson("John", 25);
        personService.addPerson("Jane", 17);

        List<String> namesOfAdults = personService.getNamesOfAdults();
        assertEquals(1, namesOfAdults.size());
        assertTrue(namesOfAdults.contains("John"));
    }
}
