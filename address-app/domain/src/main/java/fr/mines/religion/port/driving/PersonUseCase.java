package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.UUID;

public interface PersonUseCase {
    Collection<Person> selectAll();

    Person select(UUID id);

    Person insert(Person person);

    Person update(Person person);

    void delete(UUID uuid);
}
