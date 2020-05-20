package root.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import root.model.Person;

import java.util.List;

@FeignClient(value = "person", url = "http://localhost:8080/testMeFull/")
public interface PersonClient {

    @GetMapping("/person")
    List<Person> findAll();

    @GetMapping(value = "/person/{id}")
    Person findById(@PathVariable("id") String id);

    @PutMapping(value = "/person", consumes = "application/json")
    Person create(@RequestBody Person person);

    @DeleteMapping(value = "/person/{id}")
    void deleteById(@PathVariable("id") String id);

}
