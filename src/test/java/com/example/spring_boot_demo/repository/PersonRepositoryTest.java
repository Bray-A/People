package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        personRepository.deleteAll();

        Person person1 = new Person();
        person1.setName("Wane");
        person1.setAge(28);
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setName("Bob");
        person2.setAge(30);
        personRepository.save(person2);

        Person person3 = new Person();
        person3.setName("Travis");
        person3.setAge(30);
        personRepository.save(person3);
    }

    @Test
    public void testFindByNameOrAgeWithName() {
        List<Person> result = personRepository.findByNameOrAge("Wane", -1);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Wane");
    }

    @Test
    public void testFindByNameOrAgeWithAge() {
        List<Person> result = personRepository.findByNameOrAge("", 30);
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Person::getName).containsExactlyInAnyOrder("Bob", "Travis");
    }

    @Test
    public void testFindByNameOrAgeWithBothParameters() {
        List<Person> result = personRepository.findByNameOrAge("Wane", 30);
        assertThat(result).hasSize(3);
        assertThat(result).extracting(Person::getName).containsExactlyInAnyOrder("Wane", "Bob", "Travis");
    }

    @Test
    public void testFindByNameOrAgeWithNoResults() {
        List<Person> result = personRepository.findByNameOrAge("Nonexistent", 99);
        assertThat(result).isEmpty();
    }
}
