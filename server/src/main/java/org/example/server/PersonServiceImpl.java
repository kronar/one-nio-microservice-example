package org.example.server;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.example.domain.Person;
import org.example.domain.PersonService;

/**
 */
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = Logger.getLogger(PersonServiceImpl.class.getName());

    private Set<Person> storage = new HashSet<Person>();

    @Inject
    public PersonServiceImpl(@Named("storage")Set<Person> storage) {
        this.storage = storage;
    }


    public Set<Person> getStorage() {
        return storage;
    }

    public void addPerson(Person person) {
        LOGGER.info(person.toString());
        storage.add(person);
    }

    public List<Person> getAll() {
        return Lists.newArrayList(storage);
    }

    public Person remove(Person person) {

        boolean remove = storage.remove(person);
        if (remove) {
            return person;
        } else {
            return null;
        }
    }
}
