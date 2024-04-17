package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.PersonRepositoryPort;
import fr.mines.religion.port.driving.PersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PersonService implements PersonUseCase {

    @Autowired
    private PersonRepositoryPort personRepositoryPort;

    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }


    @Override
    @Transactional
    public Person save(Person person) {
        if (person.firstName() == null || person.firstName().isEmpty()) {
            throw new IllegalArgumentException("Person firstName is required");
        }
        if (person.lastName() == null || person.lastName().isEmpty()) {
            throw new IllegalArgumentException("Person lastName is required");
        }
        if (person.age() <= 0 || person.age() >= 130 ) {
            throw new IllegalArgumentException("Person age is required");
        }
        if (person.gender() != "male" && person.gender() != "female") {
            throw new IllegalArgumentException("Person gender is required");
        }


        if (personRepositoryPort
                .selectAll()
                .stream()
                .anyMatch(persisted -> persisted.firstName().equalsIgnoreCase(person.firstName()) && persisted.lastName().equalsIgnoreCase(person.lastName()) && persisted.id() != person.id())
        ) {
            throw new IllegalArgumentException("Person name already exists");
        }

        if (person.id() == null) {
            return personRepositoryPort.insert(person);
        } else {
            return personRepositoryPort.update(person);
        }
    }

    @Override
    public Optional<Person> getPersonById(UUID uuid) {
        return personRepositoryPort.select(uuid);
    }

    @Override
    public Collection<Person> getList() {
        return personRepositoryPort.selectAll();
    }

    @Override
    public Collection<Person> getListByGender(String gender) {
        return personRepositoryPort.selectByGender(gender);
    }

    @Override
    public Collection<Person> getListByAge(Integer age) {
        return personRepositoryPort.selectByAge(age);
    }

    @Override
    public void remove(UUID uuid) {
        personRepositoryPort.delete(uuid);
    }
}
