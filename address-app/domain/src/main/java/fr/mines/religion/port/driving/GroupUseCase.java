package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Group;

import java.util.Collection;
import java.util.UUID;

public interface GroupUseCase {
    Collection<Group> selectAll();

    Group select(UUID id);

    Group insert(Group group);

    Group update(Group group);

    void delete(UUID uuid);
}
