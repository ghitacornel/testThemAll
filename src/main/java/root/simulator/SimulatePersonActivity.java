package root.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.model.Person;
import root.random.RandomPersonService;
import root.service.PersonService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SimulatePersonActivity {

    @Autowired
    PersonService personService;

    @Autowired
    RandomPersonService randomPersonService;

    @PostConstruct
    public void simulate() {
        new Thread(() -> {
            while (true) {
                PersonOperationType personOperationType = randomPersonService.generateOperationType();

                // special case
                if (personService.findAll().size() < 20) personOperationType = PersonOperationType.REGISTER;
                if (personService.findAll().size() > 100) personOperationType = PersonOperationType.UNREGISTER;

                if (personOperationType == PersonOperationType.REGISTER) {
                    Person person = randomPersonService.generateNewRandomPerson();
                    personService.register(person);
                } else if (personOperationType == PersonOperationType.UNREGISTER) {
                    Person person = randomPersonService.searchForRandomPerson();
                    personService.unregister(person);
                } else if (personOperationType == PersonOperationType.CONSULT) {
                    Person person = randomPersonService.searchForRandomPerson();
                    personService.find(person);
                }
            }
        }).start();
    }
}



