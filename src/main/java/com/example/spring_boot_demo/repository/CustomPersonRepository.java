package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.Person;

import java.util.List;

public interface CustomPersonRepository {
    List<Person> findByNameOrAge(String name, int age);
}
