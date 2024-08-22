package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.model.Person;
import com.example.spring_boot_demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testAddPerson() throws Exception {
        Person person = new Person("Bray", 23);
        String personJson = "{\"name\":\"Bray\",\"age\":23}";

        mockMvc.perform(post("/person/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Person added successfully"));

        verify(personService).addPerson("Bray", 23);
    }

    @Test
    public void testGetAllPeople() throws Exception {
        List<Person> people = Arrays.asList(new Person("Bray", 23), new Person("Jewel", 30));
        when(personService.getAllPeople()).thenReturn(people);

        mockMvc.perform(get("/person/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Bray\",\"age\":23},{\"name\":\"Jewel\",\"age\":30}]"));
    }

    @Test
    public void testGetNameToAgeMap() throws Exception {
        Map<String, Integer> nameToAgeMap = Map.of("Bray", 23, "Jewel", 30);
        when(personService.getNameToAgeMap()).thenReturn(nameToAgeMap);

        mockMvc.perform(get("/person/nameToAgeMap"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Bray\":23,\"Jewel\":30}"));
    }

    @Test
    public void testGetNamesOfAdults() throws Exception {
        List<String> namesOfAdults = List.of("Bray");
        when(personService.getNamesOfAdults()).thenReturn(namesOfAdults);

        mockMvc.perform(get("/person/adults"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Bray\"]"));
    }
}
