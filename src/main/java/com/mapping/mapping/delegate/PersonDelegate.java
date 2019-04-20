package com.mapping.mapping.delegate;

import com.mapping.mapping.constant.Genre;
import com.mapping.mapping.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PersonDelegate {
    private final MovieDelegate movieDelegate;
    private List<Person> people = new ArrayList<>();

    @PostConstruct
    private void setUpPeople() {
        people.add(new Person(1, "Jimmy", "Jon", movieDelegate.getByGenre(Genre.COMEDY)));
        people.add(new Person(2, "Sammy", "Samuelson", movieDelegate.getByGenre(Genre.ACTION)));
        people.add(new Person(3, "Jane", "Doughberry", movieDelegate.getByGenre(Genre.DOCUMENTARY)));
        people.add(new Person(4, "Hamburger", "Helper", Arrays.asList(
            movieDelegate.getById(1).get(),
            movieDelegate.getById(2).get(),
            movieDelegate.getById(3).get(),
            movieDelegate.getById(8).get()
        )));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Optional<Person> getPersonById(Integer id) {
        return getAllPeople()
            .stream()
            .filter(person -> person.getId().equals(id))
            .findAny();
    }

    public Person addNewPerson(Person person) {
        try {
            Integer nextId = getCurrentMaxId() + 1;
            Person p = new Person(
                nextId,
                person.getFirstName(),
                person.getLastName(),
                Objects.isNull(person.getFavoriteMovies()) ?
                    new ArrayList<>() : person.getFavoriteMovies());
            people.add(p);
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getCurrentMaxId() {
        return getAllPeople()
            .stream()
            .map(Person::getId)
            .max(Comparator.naturalOrder())
            .orElse(0);
    }
}
