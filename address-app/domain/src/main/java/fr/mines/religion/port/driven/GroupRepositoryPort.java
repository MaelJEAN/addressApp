package fr.mines.religion.port.driven;

import fr.mines.religion.domain.model.Group;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface GroupRepositoryPort {
    Collection<Group> selectAll();

    Group select(UUID id);

    Group selectByName(String name);

    Group insert(Group group);

    Group update(Group group);

    void delete(UUID uuid);
}
