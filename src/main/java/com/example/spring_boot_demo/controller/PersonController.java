package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.model.Person;
import com.example.spring_boot_demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/add")
    public String addPerson(@RequestBody Person person) {
        personService.addPerson(person.getName(), person.getAge());
        return "Person added successfully";
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/nameToAgeMap")
    public Map<String, Integer> getNameToAgeMap() {
        return personService.getNameToAgeMap();
    }

    @GetMapping("/adults")
    public List<String> getNamesOfAdults() {
        return personService.getNamesOfAdults();
    }
}
