package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PersonUseCase {
    Person save(Person person);

    Optional<Optional<Person>> getPersonById(UUID uuid);

    Collection<Person> getList();
    Collection<Person> getListByGender(String gender);
    Collection<Person> getListByAge(Integer age);

    void remove(UUID uuid);
}
