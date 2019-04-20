package com.mapping.mapping.controller;

import com.mapping.mapping.delegate.PersonDelegate;
import com.mapping.mapping.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(path = "/person", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class PersonController {
    private final PersonDelegate personDelegate;

    @PostMapping
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        try {
            return new ResponseEntity<>(personDelegate.addNewPerson(person), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating new person from request " + person.toString(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Person>> getAllPeople() {
        return new ResponseEntity<>(personDelegate.getAllPeople(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Person> getPerson(@RequestParam(value = "id") Integer id) {
        Optional<Person> person = personDelegate.getPersonById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
