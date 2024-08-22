package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomPersonRepositoryImpl implements CustomPersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> findByNameOrAge(String name, int age) {
        String queryStr = "SELECT p FROM Person p WHERE p.name = :name OR p.age = :age";
        TypedQuery<Person> query = entityManager.createQuery(queryStr, Person.class);
        query.setParameter("name", name);
        query.setParameter("age", age);
        return query.getResultList();
    }
}
