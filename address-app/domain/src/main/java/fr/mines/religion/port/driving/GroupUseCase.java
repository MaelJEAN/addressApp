package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GroupUseCase {
    Group save(Group group);

    Optional<Group> getGroupById(UUID uuid);

    Optional<Group> getGroupByName(String name);

    Collection<Group> getList();

    void remove(UUID uuid);
}
