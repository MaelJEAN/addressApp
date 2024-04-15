package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driving.PersonUseCase;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PersonService implements PersonUseCase {
    @Override
    public Person save(Person person) {
        if (person.age()<0 && person.age()>120){
            throw new IllegalArgumentException("Town name is required");
        }
        return null;
    }

    @Override
    public Optional<Person> getPersonById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Collection<Person> getList() {
        return List.of();
    }

    @Override
    public Collection<Person> getListByGender(String gender) {
        return List.of();
    }

    @Override
    public Collection<Person> getListByAge(Integer age) {
        return List.of();
    }

    @Override
    public void remove(UUID uuid) {

    }
}
