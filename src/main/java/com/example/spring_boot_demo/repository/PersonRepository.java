package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, CustomPersonRepository {


}
