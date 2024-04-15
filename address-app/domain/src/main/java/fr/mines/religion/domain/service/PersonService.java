package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driving.PersonUseCase;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PersonService implements PersonUseCase {
    @Override
    public Collection<Person> selectAll() {
        return List.of();
    }

    @Override
    public Person select(UUID id) {
        return null;
    }

    @Override
    public Person insert(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
