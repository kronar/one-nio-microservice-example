package org.example.domain;

import java.util.List;

/**
 */
public interface PersonService {

    void addPerson(Person person);

    List<Person> getAll();

    Person remove(Person person);
}
