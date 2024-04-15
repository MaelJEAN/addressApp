package fr.mines.religion.port.driven;

import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.UUID;

public interface PersonRepositoryPort {
    Collection<Person> selectAll();

    Collection<Person> selectByGender(String gender);

    Collection<Person> selectByAge(Integer age);

    Person select(UUID id);

    Person insert(Person person);

    Person update(Person person);

    void delete(UUID uuid);
}
