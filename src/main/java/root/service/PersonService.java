package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import root.client.PersonClient;
import root.model.Person;

import java.util.*;

@Service
public class PersonService {

    @Autowired
    PersonClient personClient;

    @CacheEvict(value = "persons", allEntries = true)
    public void register(Person person) {
        System.out.println("register " + person);
        personClient.create(person);
    }

    @CacheEvict(value = "persons", allEntries = true)
    public void unregister(Person person) {
        System.out.println("unregister " + person);
        personClient.deleteById(person.getId());
    }

    public Person find(Person person) {
        System.out.println("get details for " + person);
        return personClient.findById(person.getId());
    }

    @Cacheable("persons")
    public List<Person> findAll() {
        return personClient.findAll();
    }

}
