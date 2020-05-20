package root.random;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.model.Person;
import root.service.PersonService;
import root.simulator.PersonOperationType;

import java.util.List;
import java.util.Random;

@Service
public class RandomPersonService {

    final Faker faker = new Faker();

    @Autowired
    PersonService personService;

    public Person generateNewRandomPerson() {
        Person person = new Person();
        person.setId(faker.idNumber().valid());
        person.setName(faker.name().fullName());
        return person;
    }

    public Person searchForRandomPerson() {
        List<Person> data = personService.findAll();
        Random random = new Random();
        int i = random.nextInt(data.size());
        return data.get(i);
    }

    public PersonOperationType generateOperationType() {
        Random random = new Random();
        int i = random.nextInt(100);
        if (i < 5) return PersonOperationType.REGISTER;
        if (i > 97) return PersonOperationType.UNREGISTER;
        return PersonOperationType.CONSULT;
    }


}
